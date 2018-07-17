package com.chen.tian.papermgr.repository;

import com.chen.tian.papermgr.entity.TOrderEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends BasicRepository<TOrderEntity, Long> {

}
