package com.chen.tian.papermgr.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.dto.MenuDto;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ResultInfo;
import com.chen.tian.papermgr.entity.SysResource;
import com.chen.tian.papermgr.entity.SysUser;
import com.chen.tian.papermgr.repository.SysUserRepository;
import com.chen.tian.papermgr.repository.specification.SpecFactory;
import com.chen.tian.papermgr.util.JSONUtil;
import com.chen.tian.papermgr.util.ResultInfoUtils;
import com.chen.tian.papermgr.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 权限管理模块服务层
 * Created by ChenTian on 2018/5/9.
 */
@Service
public class AdminService {
    public static final boolean SHOW_MENU = true;
    @Qualifier("sysUserRepository")
    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 登录
     */
    public ResultInfo login(String account,String password) {
        ResultInfo resultInfo = ResultInfoUtils.success();

        SysUser findAdmin = sysUserRepository.findByAccount(account);
        if (Utils.isNull(findAdmin)) {
            return ResultInfoUtils.error("账号不存在!");
        }

        if (findAdmin.getPassword().equals(password)) {
            List<MenuDto> mainMenu = buildMainMenu(findAdmin);
            emptyUserPassword(findAdmin);
            JSONObject data = new JSONObject();
            data.put("user", JSONUtil.toJSONStringFromExcludes(findAdmin, new String[]{"role"}));
            data.put("mainMenu", JSON.toJSONString(mainMenu));

            resultInfo.setResultData(data.toJSONString());
        }else {
            return ResultInfoUtils.error("密码不正确!");
        }
        return resultInfo;
    }

    private List<MenuDto> buildMainMenu(SysUser findAdmin) {
        List<MenuDto> menuList = new ArrayList<>(0);

        Set<SysResource> resources  = findAdmin.getRole().getResources();
        List<SysResource> sysResourceList = sortSysResource(resources);

        for(SysResource resource : sysResourceList){
            if(Utils.isNull(resource.getParent())) {
                MenuDto menuDto = new MenuDto(resource.getId(), resource.getName(),resource.getUrl(), SHOW_MENU);
                List<MenuDto> subMenu = buildSubMenu(resource.getChildren());
                menuDto.setSubMenu(subMenu);
                menuList.add(menuDto);
            }
        }

        return menuList;
    }
    private List<MenuDto> buildSubMenu(Set<SysResource> childrens){
        List<MenuDto> subMenu = new ArrayList<>(0);
        if(Utils.isEmpty(childrens)){
            return subMenu;
        }

        List<SysResource> sortResources = sortSysResource(childrens);
        for(SysResource resource : sortResources){
            MenuDto menuDto = new MenuDto(resource.getId(), resource.getName(),resource.getUrl(), SHOW_MENU);
            List<MenuDto> childMenu = buildSubMenu(resource.getChildren());
            menuDto.setSubMenu(childMenu);
            subMenu.add(menuDto);
        }

        return subMenu;
    }

    private void emptyUserPassword(SysUser findAdmin) {
        findAdmin.setPassword("");
    }

    private List<SysResource> sortSysResource(Set<SysResource> resources) {
        List<SysResource> sysResourceList = new ArrayList<>(resources);
        // 排序
        Collections.sort(sysResourceList, new Comparator<SysResource>() {
            @Override
            public int compare(SysResource o1, SysResource o2) {
                if (o1.getSeq() == null) {
                    o1.setSeq(1000);
                }
                if (o2.getSeq() == null) {
                    o2.setSeq(1000);
                }
                return o1.getSeq().compareTo(o2.getSeq());
            }
        });
        return sysResourceList;
    }

    public QueryResult<SysUser> finduserByPageAndParams(String userName, String account, Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "createTime")); // 分页信息

        Specification<SysUser> spec = SpecFactory.buildFindExclusiveUserSpecification(userName, account);

        Page<SysUser> page = sysUserRepository.findAll(spec, pageable);

        return buildUserQueryResult(page, pageNumber, pageSize);
    }

    private QueryResult<SysUser> buildUserQueryResult(Page<SysUser> page, Integer pageNumber, Integer pageSize) {
        long totalElements = 0;
        int totalPages = 0;
        if(!Utils.isNull(page) && !Utils.isEmpty(page.getContent())){
            totalElements = page.getTotalElements();
            totalPages = page.getTotalPages();
        }
        return new QueryResult<>(totalPages, totalElements, pageSize, pageNumber, page.getContent());
    }

    public SysUser userDetail(Long userId) {
        return sysUserRepository.findOne(userId);
    }

    @Transactional
    public void deleteUser(Long userId) {
        if (Utils.isNull(userId)) {
            return;
        }
        sysUserRepository.updateStateById(Consts.STATE_DELETED,new Date(),userId);
    }

    @Transactional
    public SysUser userAddOrUpdate(SysUser userEntity) throws Exception{
        if (Utils.isNull(userEntity)) {
            throw new Exception("用户信息不能为空");
        }
        if(Utils.isNull(userEntity.getId())){
            userEntity.setCreateTime(new Date());
            userEntity.setUpdateTime(new Date());
            userEntity.setState(Consts.STATE_VALID);

            return sysUserRepository.save(userEntity);
        } else {
            SysUser oldUser = sysUserRepository.findOne(userEntity.getId());
            if(Utils.isNull(oldUser)){
                throw new Exception("未找到修改的用户信息");
            }

            userEntity.setCreateTime(oldUser.getCreateTime());
            userEntity.setUpdateTime(new Date());
            if(Utils.isNull(userEntity.getState())) {
                userEntity.setState(oldUser.getState());
            }
            return sysUserRepository.save(userEntity);
        }
    }
}
