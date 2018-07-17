package com.chen.tian.papermgr.repository;

import com.chen.tian.papermgr.entity.TProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface ProductRepository extends BasicRepository<TProductEntity, Long> {

    @Modifying
    @Query("update TProductEntity t set t.state = ?1, t.updateTime = ?2 where t.id = ?3")
    void updateStateById(Integer state, Date updateTime, Long id);
}
