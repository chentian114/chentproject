package com.chen.tian.papermgr.repository;

import com.chen.tian.papermgr.entity.TOrderProdEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderProdRepository extends BasicRepository<TOrderProdEntity, Long> {
    public List<TOrderProdEntity> findByOrderNumber(String orderNumber);

}
