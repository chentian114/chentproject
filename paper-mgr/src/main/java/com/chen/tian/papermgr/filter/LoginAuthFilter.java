package com.chen.tian.papermgr.filter;

import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.entity.SysUser;
import com.chen.tian.papermgr.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
 
/**
 * 登录授权 如果是未登录且是需登录的页面则过滤到登录页
 **/
public class LoginAuthFilter  implements Filter{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private String loginPagePath;
    private String indexPagePath;
	private HashSet<String> excludePath = new HashSet<>();
	private HashSet<String> loginedValidPath = new HashSet<>();
 
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        loginPagePath = filterConfig.getInitParameter("loginPagePath");
        indexPagePath = filterConfig.getInitParameter("indexPagePath");
        String loginedValidPathStr = filterConfig.getInitParameter("loginedValidPath");
        if (!Utils.isEmpty(loginedValidPathStr)) {
            String[] loginedValidPathArr = loginedValidPathStr.split(",");
            Collections.addAll(loginedValidPath, loginedValidPathArr);
        } 
		initExcludePath(filterConfig);
	}

	private void initExcludePath(FilterConfig filterConfig) {
		String excludePathStr = filterConfig.getInitParameter("excludePath");		
		if (!Utils.isEmpty(excludePathStr)) {
			String[] excludePathArr = excludePathStr.split(",");
            Collections.addAll(excludePath, excludePathArr);
		}	
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();	
		String ctx = request.getContextPath();
		String requestPath = request.getRequestURI().substring(ctx.length());
		SysUser loginAdmin = (SysUser) session.getAttribute(Consts.SESSION_KEY_LOGIN_USER_INFO);

        if(requestPath.equals(loginPagePath)
				|| loginAdmin != null
				|| isExcludePath(requestPath)){
			//已登录状态或无需过滤请求
			logger.info("continue to load :{}" , requestPath);
			if(isLogined(loginAdmin, requestPath)){//已经登录，进入首页
                String basePath = getBasePath(request);
                response.sendRedirect(basePath + indexPagePath);
            } else {
                chain.doFilter(req, resp);
            }
		} else if (request.getHeader("x-requested-with") != null   
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // 判断是否为ajax请求   
			response.setStatus(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED);
            response.addHeader("sessionstatus", "timeOut"); //返回超时标识  
        } else{
        	String basePath = getBasePath(request);
            response.sendRedirect(basePath + loginPagePath);
		}
		
	}
 
	@Override
	public void destroy() {
		
	}
	
	/**
     * 获取基础路径
     * @param request
     * @return
     */
    private String getBasePath(HttpServletRequest request) {
        String basePath = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            basePath = basePath + ":" + request.getServerPort();
        }
        basePath = basePath + request.getContextPath();
        return basePath;
    }
    
    /**
     * 判断是否已登录
     * @param loginAdmin
     * @param requestPath
     * @return
     */
    private boolean isLogined(SysUser loginAdmin, String requestPath){
        if(Utils.isNull(loginAdmin)){
            return false;
        }
        Iterator<String> it = loginedValidPath.iterator();
        boolean loginedFlag=false;
        while(it.hasNext()){
            loginedFlag = Pattern.matches(it.next(),requestPath);
            if(loginedFlag){
                break;
            }
        }
        return loginedFlag;
    }

    /**
     * 判断是否排除登录控制
     * @param requestPath
     * @return
     */
    private boolean isExcludePath(String requestPath){
        Iterator<String> it = excludePath.iterator();
        boolean excludeFlag=false;
        while(it.hasNext()){
            excludeFlag = Pattern.matches(it.next(),requestPath);
            if(excludeFlag){
                return excludeFlag;
            }
        }
        return excludeFlag;
    }
}
