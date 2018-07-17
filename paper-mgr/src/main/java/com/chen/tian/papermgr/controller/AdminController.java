package com.chen.tian.papermgr.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.controller.base.BaseController;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ResultInfo;
import com.chen.tian.papermgr.dto.ReturnDto;
import com.chen.tian.papermgr.entity.SysUser;
import com.chen.tian.papermgr.service.AdminService;
import com.chen.tian.papermgr.util.ResultInfoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限管理模块控制层
 * Created by ChenTian on 2018/5/9.
 */

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public String login(@RequestParam(value = "account", required = true) String account,
            @RequestParam(value = "password") String password,
            HttpServletRequest request) {
        ReturnDto returnDto = new ReturnDto(true);
        try {
            ResultInfo resultInfo = adminService.login(account,password);

            if (ResultInfoUtils.isSucess(resultInfo)) {
                logger.info("account:{} login sucess!", account);
                returnDto.setData(resultInfo.getResultData());
                saveUserSession(request, resultInfo);
            } else {
                returnDto.setStatus(false);
                returnDto.setDescription("登录失败，账号或密码错误！");
            }
        } catch (Exception e) {
            logger.error("accout:{}",account, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    private void saveUserSession(HttpServletRequest request, ResultInfo resultInfo) {
        JSONObject data = JSON.parseObject(resultInfo.getResultData());
        SysUser user = JSON.parseObject(data.getString("user"),SysUser.class);
        // 存放在session中
        request.getSession().setAttribute(Consts.SESSION_KEY_LOGIN_USER_INFO, user);
        request.getSession().setAttribute(Consts.SESSION_KEY_USERID,user.getId());
    }

    @RequestMapping(value = "/logout.do")
    @ResponseBody
    public String logout(HttpServletRequest request) {
        ReturnDto<SysUser> returnDto = new ReturnDto<SysUser>(true);
        try {
            request.getSession().removeAttribute(Consts.SESSION_KEY_LOGIN_USER_INFO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }



    @RequestMapping(value = "/userList.do")
    @ResponseBody
    public String userList(@RequestParam(value="name", required = false) String userName,
                           @RequestParam(value="account", required = false) String account,
                           @RequestParam(value="page_num", required = true) Integer pageNumber,
                           @RequestParam(value="page_size", required = true) Integer pageSize){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            QueryResult<SysUser> result = adminService.finduserByPageAndParams(userName, account, pageNumber, pageSize);
            returnDto.setData(result);
        }catch (Exception e) {
            logger.error("userList userName:{} account:{} error!",userName, account,e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/userDetail.do")
    @ResponseBody
    public String userDetail(@RequestParam(value="id", required = true) Long userId){
        ReturnDto returnDto = new ReturnDto(true);
        try {
            SysUser user = adminService.userDetail(userId);
            returnDto.setData(user);
        }catch (Exception e) {
            logger.error("userDetail userId:{} error!",userId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }

    @RequestMapping(value = "/deleteUser.do")
    @ResponseBody
    public String deleteUser(@RequestParam(value="id", required = true) Long userId){
        logger.info("salesId:{}",userId);
        ReturnDto returnDto = new ReturnDto(true);
        try {
            adminService.deleteUser(userId);
        }catch (Exception e) {
            logger.error("deleteuser userId:{} error!",userId, e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }


    @RequestMapping(value="/userAddOrUpdate.do" , method = RequestMethod.POST)
    @ResponseBody
    public String userAddOrUpdate(SysUser userEntity) {
        ReturnDto<SysUser> returnDto = new ReturnDto<>(true);
        try {
            userEntity = adminService.userAddOrUpdate(userEntity);
            returnDto.setData(userEntity);
        } catch (Exception e) {
            logger.error("userAddOrUpdate error user:{}",JSON.toJSONString(userEntity), e);
            returnDto.setStatus(false);
            returnDto.setDescription("系统繁忙，请稍后再试！");
        }
        return JSON.toJSONString(returnDto);
    }
}
