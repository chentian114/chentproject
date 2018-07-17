package com.chen.tian.papermgr.repository.specification;

import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.dto.CustomerDto;
import com.chen.tian.papermgr.dto.OrderQueryDto;
import com.chen.tian.papermgr.entity.*;
import com.chen.tian.papermgr.util.Utils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 条件查询specification对象工厂
 * Created by ChenTian on 2018/5/11.
 */
public class SpecFactory {

    /**
     * 客户列表查询条件
     * @param customerDto
     * @return
     */
    public static Specification<TCustomerEntity> buildFindExclusiveCustomerSpecification(final CustomerDto customerDto) {
        return new Specification<TCustomerEntity>() {
            @Override
            public Predicate toPredicate(Root<TCustomerEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicate = new ArrayList<Predicate>();
                if(Utils.isNull(customerDto)){
                    return cb.and(listPredicate.toArray(new Predicate[0]));
                }

                if (!Utils.isEmpty(customerDto.getCustName())) {
                    listPredicate.add(cb.like(root.get("custName").as(String.class), "%" + customerDto.getCustName() + "%"));
                }
                if (!Utils.isEmpty(customerDto.getAddress())) {
                    listPredicate.add(cb.like(root.get("address").as(String.class), "%" + customerDto.getAddress() + "%"));
                }
                if (!Utils.isEmpty(customerDto.getPhone())) {
                    listPredicate.add(cb.like(root.get("phone").as(String.class), "%" + customerDto.getPhone() + "%"));
                }
                if (!Utils.isEmpty(customerDto.getSalesName())) {
                    if(Utils.isEmpty(customerDto.getSalesIds())){
                        listPredicate.add(root.get("salesId").isNull());
                    }else {
                        listPredicate.add(root.get("salesId").in(customerDto.getSalesIds()));
                    }
                }

                //已删除的客户不显示
                listPredicate.add(cb.notEqual(root.get("state").as(Integer.class), Consts.STATE_DELETED));

                return cb.and(listPredicate.toArray(new Predicate[0]));
            }
        };
    }

    public static Specification<TSalesmanEntity> buildFindExclusiveSalesmanSpecification(final String salesName,final String phone) {
        return new Specification<TSalesmanEntity>() {
            @Override
            public Predicate toPredicate(Root<TSalesmanEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicate = new ArrayList<Predicate>();

                if (!Utils.isEmpty(salesName)) {
                    listPredicate.add(cb.like(root.get("salesName").as(String.class), "%" + salesName + "%"));
                }
                if (!Utils.isEmpty(phone)) {
                    listPredicate.add(cb.like(root.get("phone").as(String.class), "%" + phone + "%"));
                }

                //已删除的客户不显示
                listPredicate.add(cb.notEqual(root.get("state").as(Integer.class), Consts.STATE_DELETED));

                return cb.and(listPredicate.toArray(new Predicate[0]));
            }
        };
    }


    public static Specification<TProductEntity> buildFindExclusiveProductSpecification(final String prodName) {
        return new Specification<TProductEntity>() {
            @Override
            public Predicate toPredicate(Root<TProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicate = new ArrayList<Predicate>();

                if (!Utils.isEmpty(prodName)) {
                    listPredicate.add(cb.like(root.get("prodName").as(String.class), "%" + prodName + "%"));
                }


                //已删除的产品不显示
                listPredicate.add(cb.notEqual(root.get("state").as(Integer.class), Consts.STATE_DELETED));

                return cb.and(listPredicate.toArray(new Predicate[0]));
            }
        };
    }

    public static Specification<TOrderEntity> buildFindExclusiveOrderSpecification(final OrderQueryDto orderQueryDto) {
        return new Specification<TOrderEntity>() {
            @Override
            public Predicate toPredicate(Root<TOrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicate = new ArrayList<Predicate>();
                if(Utils.isNull(orderQueryDto)){
                    return cb.and(listPredicate.toArray(new Predicate[0]));
                }

                if (!Utils.isEmpty(orderQueryDto.getCustomerPhone())) {
                    listPredicate.add(cb.like(root.get("customerPhone").as(String.class), "%" + orderQueryDto.getCustomerPhone() + "%"));
                }
                if (!Utils.isEmpty(orderQueryDto.getOrderNumber())) {
                    listPredicate.add(cb.like(root.get("orderNumber").as(String.class), "%" + orderQueryDto.getOrderNumber() + "%"));
                }

                if (!Utils.isEmpty(orderQueryDto.getSalesName())) {
                    if(Utils.isEmpty(orderQueryDto.getSalesIds())){
                        listPredicate.add(root.get("salesId").isNull());
                    }else {
                        listPredicate.add(root.get("salesId").in(orderQueryDto.getSalesIds()));
                    }
                }

                if (!Utils.isEmpty(orderQueryDto.getCustomerName())) {
                    if(Utils.isEmpty(orderQueryDto.getCustomerIds())){
                        listPredicate.add(root.get("customerId").isNull());
                    }else {
                        listPredicate.add(root.get("customerId").in(orderQueryDto.getCustomerIds()));
                    }
                }

                //已删除的不显示
                listPredicate.add(cb.notEqual(root.get("state").as(Integer.class), Consts.STATE_DELETED));

                return cb.and(listPredicate.toArray(new Predicate[0]));
            }
        };
    }

    public static Specification<SysUser> buildFindExclusiveUserSpecification(final String userName, final String account) {
        return new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> listPredicate = new ArrayList<Predicate>();

                if (!Utils.isEmpty(userName)) {
                    listPredicate.add(cb.like(root.get("name").as(String.class), "%" + userName + "%"));
                }

                if (!Utils.isEmpty(account)) {
                    listPredicate.add(cb.like(root.get("account").as(String.class), "%" + account + "%"));
                }
                //已删除的产品不显示
                listPredicate.add(cb.notEqual(root.get("state").as(Integer.class), Consts.STATE_DELETED));

                return cb.and(listPredicate.toArray(new Predicate[0]));
            }
        };
    }
}
