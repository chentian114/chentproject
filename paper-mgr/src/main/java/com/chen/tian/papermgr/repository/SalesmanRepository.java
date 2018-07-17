package com.chen.tian.papermgr.repository;

import com.chen.tian.papermgr.entity.TSalesmanEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface SalesmanRepository extends BasicRepository<TSalesmanEntity, Long> {

    @Modifying
    @Query("update TSalesmanEntity t set t.state = ?1, t.updateTime = ?2 where t.id = ?3")
    public void updateStateById(Integer state, Date updateTime, Long id);

    public List<TSalesmanEntity> findBySalesNameLike(String salesName);
}
