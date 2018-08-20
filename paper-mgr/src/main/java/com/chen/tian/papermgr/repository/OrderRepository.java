package com.chen.tian.papermgr.repository;

import com.chen.tian.papermgr.entity.TOrderEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;


@Repository
public interface OrderRepository extends BasicRepository<TOrderEntity, Long> {
    @Modifying
    @Query("update TOrderEntity t set t.state = ?1, t.updateTime = ?2 where t.id = ?3")
    public void updateStateById(Integer state, Date updateTime, Long id);
}
