package com.chen.tian.papermgr.repository;

import com.chen.tian.papermgr.entity.SysResource;

/**
 * Created by ChenTian on 2018/5/10.
 */
public interface SysResourceRepository extends BasicRepository<SysResource, Long> {
    public SysResource findById(Long id);
}
