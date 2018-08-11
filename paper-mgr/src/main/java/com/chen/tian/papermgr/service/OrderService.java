package com.chen.tian.papermgr.service;

import com.alibaba.fastjson.JSON;
import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.dto.CustomerDto;
import com.chen.tian.papermgr.dto.OrderDto;
import com.chen.tian.papermgr.dto.OrderQueryDto;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.entity.*;
import com.chen.tian.papermgr.repository.*;
import com.chen.tian.papermgr.repository.specification.SpecFactory;
import com.chen.tian.papermgr.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 货单管理模块服务层
 * Created by ChenTian on 2018/5/11.
 */
@Service
public class OrderService {

    @Qualifier("customerRepository")
    @Autowired
    private CustomerRepository customerRepository;
    @Qualifier("salesmanRepository")
    @Autowired
    private SalesmanRepository salesmanRepository;
    @Qualifier("orderRepository")
    @Autowired
    private OrderRepository orderRepository;
    @Qualifier("orderProdRepository")
    @Autowired
    private OrderProdRepository orderProdRepository;
    @Qualifier("sysUserRepository")
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    /**
     * 分页查询客户信息
     */
    public QueryResult<TCustomerEntity> findCustomerByPageAndParams(CustomerDto customerDto, Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "createTime")); // 分页信息
        if(!Utils.isEmpty(customerDto.getSalesName())) {
            fillSalesmanToCustomerDto(customerDto);
        }
        Specification<TCustomerEntity> spec = SpecFactory.buildFindExclusiveCustomerSpecification(customerDto);

        Page<TCustomerEntity> page = customerRepository.findAll(spec, pageable);

        return buildCustomerQueryResult(page, pageNumber, pageSize);
    }

    private void fillSalesmanToCustomerDto(CustomerDto customerDto) {
        Set<Long> salesIds = getSalesIdsByLikeSalesName(customerDto.getSalesName());
        customerDto.setSalesIds(salesIds);
    }
    private Set<Long> getSalesIdsByLikeSalesName(String salesName) {
        String saName = "%"+salesName+"%";
        List<TSalesmanEntity> salesList = salesmanRepository.findBySalesNameLike(saName);
        if(Utils.isEmpty(salesList)){
            return null;
        }
        Set<Long> salesIds = new HashSet<>();
        for (TSalesmanEntity sales : salesList){
            salesIds.add(sales.getId());
        }
        return salesIds;
    }

    private QueryResult<TCustomerEntity> buildCustomerQueryResult(Page<TCustomerEntity> page,Integer pageNumber,Integer pageSize) {
        long totalElements = 0;
        int totalPages = 0;
        List<TCustomerEntity> list = new ArrayList<>(0);
        if(!Utils.isNull(page) && !Utils.isEmpty(page.getContent())){
            totalElements = page.getTotalElements();
            totalPages = page.getTotalPages();
            //deap copy list
            list = new ArrayList<>(page.getContent());
            for(TCustomerEntity customerEntity:list){
                fillSalesmanToCustomer(customerEntity);
                TSalesmanEntity copyEntity = JSON.parseObject(JSON.toJSONString(customerEntity.getSalesmanEntity()),
                        TSalesmanEntity.class);
                customerEntity.setSalesmanEntity(copyEntity);
            }
        }
        return new QueryResult<>(totalPages, totalElements, pageSize, pageNumber, list);
    }

    public TCustomerEntity customerDetail(Long customerId) {
        if (Utils.isNull(customerId)) {
            return null;
        }
        TCustomerEntity customerEntity = customerRepository.findOne(customerId);
        fillSalesmanToCustomer(customerEntity);
        return customerEntity;
    }

    private void fillSalesmanToCustomer(TCustomerEntity customerEntity) {
        if(Utils.isNull(customerEntity.getSalesmanEntity())){
            TSalesmanEntity salesmanEntity =salesmanRepository.findOne(Long.valueOf(customerEntity.getSalesId()));
            customerEntity.setSalesmanEntity(salesmanEntity);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delCustomer(Long customerId)  {
        if (Utils.isNull(customerId)) {
            return;
        }
        customerRepository.updateStateById(Consts.STATE_DELETED, new Date(), customerId);
    }

    @Transactional
    public TCustomerEntity customerAddOrUpdate(TCustomerEntity customer) throws Exception {
        if (Utils.isNull(customer)) {
            throw new Exception("客户信息不能为空");
        }
        if(Utils.isNull(customer.getId())){
            customer.setCreateTime(new Date());
            customer.setUpdateTime(new Date());
            customer.setState(Consts.STATE_VALID);

            return customerRepository.save(customer);
        } else {
            TCustomerEntity oldCustomer = customerRepository.findOne(customer.getId());
            if(Utils.isNull(oldCustomer)){
                throw new Exception("未找到修改的客户信息");
            }

            customer.setCreateTime(oldCustomer.getCreateTime());
            customer.setUpdateTime(new Date());
            if(Utils.isNull(customer.getState())) {
                customer.setState(oldCustomer.getState());
            }
            return customerRepository.save(customer);
        }
    }

    @Transactional
    private void updateCustomerDeliveryAddress(Long customerId,String deliveryAddress)throws Exception {
        TCustomerEntity oldCustomer = customerRepository.findOne(customerId);
        if(Utils.isNull(oldCustomer)){
            throw new Exception("未找到修改的客户信息");
        }
        if(oldCustomer.getDeliveryAddress().equals(deliveryAddress)){
            return;
        }

        oldCustomer.setDeliveryAddress(deliveryAddress);
        oldCustomer.setUpdateTime(new Date());
        customerRepository.save(oldCustomer);
    }

    public QueryResult<TSalesmanEntity> findSalesmanByPageAndParams(String salesName, String phone, Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "createTime")); // 分页信息

        Specification<TSalesmanEntity> spec = SpecFactory.buildFindExclusiveSalesmanSpecification(salesName,phone);

        Page<TSalesmanEntity> page = salesmanRepository.findAll(spec, pageable);

        return buildSalesmanQueryResult(page, pageNumber, pageSize);
    }

    private QueryResult<TSalesmanEntity> buildSalesmanQueryResult(Page<TSalesmanEntity> page,Integer pageNumber,Integer pageSize) {
        long totalElements = 0;
        int totalPages = 0;
        if(!Utils.isNull(page) && !Utils.isEmpty(page.getContent())){
            totalElements = page.getTotalElements();
            totalPages = page.getTotalPages();
        }
        return new QueryResult<>(totalPages, totalElements, pageSize, pageNumber, page.getContent());
    }

    public TSalesmanEntity salesmanDetail(Long salesmanId) {
        if (Utils.isNull(salesmanId)) {
            return null;
        }
        return salesmanRepository.findOne(salesmanId);
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteSalesman(Long salesmanId) {
        if (Utils.isNull(salesmanId)) {
            return;
        }
        salesmanRepository.updateStateById(Consts.STATE_DELETED,new Date(),salesmanId);
    }

    @Transactional
    public TSalesmanEntity salesmanAddOrUpdate(TSalesmanEntity salesmanEntity) throws Exception{
        if (Utils.isNull(salesmanEntity)) {
            throw new Exception("业务员信息不能为空");
        }
        if(Utils.isNull(salesmanEntity.getId())){
            salesmanEntity.setCreateTime(new Date());
            salesmanEntity.setUpdateTime(new Date());
            salesmanEntity.setState(Consts.STATE_VALID);

            return salesmanRepository.save(salesmanEntity);
        } else {
            TSalesmanEntity oldSalesman = salesmanRepository.findOne(salesmanEntity.getId());
            if(Utils.isNull(oldSalesman)){
                throw new Exception("未找到修改的业务员信息");
            }

            salesmanEntity.setCreateTime(oldSalesman.getCreateTime());
            salesmanEntity.setUpdateTime(new Date());
            if(Utils.isNull(salesmanEntity.getState())) {
                salesmanEntity.setState(oldSalesman.getState());
            }
            return salesmanRepository.save(salesmanEntity);
        }
    }

    public TOrderEntity converOrderByOrderDto(OrderDto orderDto) throws ParseException {
        TOrderEntity order = JSON.parseObject(JSON.toJSONString(orderDto),TOrderEntity.class);

        List<TOrderProdEntity> prodParamList = JSON.parseArray(orderDto.getProdParamList(), TOrderProdEntity.class);

        order.setOrderProductList(prodParamList);
        return order;
    }

    /**
     * 校验数据是否正常有无被改动如金额，总价
     * var money = 0;
     if(specType == SPEC_TYPE_AREA){ //长*宽
     gweight = common.formatFloatDigit(gweight,MONEY_DIGIT_2);
     gweight = gweight/1000;
     var specArrays = spec.split("*");
     var specNum1 = specArrays[0];
     var specNum2 = specArrays[1];
     var specCount = specNum1 * specNum2 ;
     specCount = specCount/10000;
     unitPrice = unitPrice/1000;
     money = gweight * specCount * amount * unitPrice;
     }else if(specType == SPEC_TYPE_WIDE){//宽幅
     amount = amount/1000;
     money = amount * unitPrice;
     }
     money = common.formatFloatDigit(money,MONEY_DIGIT_2);
     * @param orderEntity
     * @return
     */
    public boolean checkOrderPrice(TOrderEntity orderEntity) {
        if(Utils.isEmpty(orderEntity.getOrderProductList())){
            return false;
        }
            int moneyCount = 0;
        for(TOrderProdEntity prod : orderEntity.getOrderProductList()){
            Float money = 0.0f ;
            if(Consts.SPEC_TYPE_AREA.equals(prod.getSpecType())){

            }else if(Consts.SPEC_TYPE_WIDE.equals(prod.getSpecType())){

            }

        }

        return true;
    }

    @Transactional
    public TOrderEntity saveOrder(TOrderEntity orderEntity) throws Exception {
        String orderNumber = buildUniqeOrderNumber();
        fillOrderNumberAndCurrentTime(orderNumber, orderEntity);
        updateCustomerDeliveryAddress(orderEntity.getCustomerId(), orderEntity.getDeliveryAddress());
        orderEntity = saveOrderAndOrderProds(orderEntity);
        fillDeepCopySalesmanToOrder(orderEntity);
        fillDeepCopyCustomerToOrder(orderEntity);
        fillDeepCopyUserToOrder(orderEntity);
        return orderEntity;
    }

    /**
     * 生成惟一的工单编码   ZL180512000001(16)
     */
    private String buildUniqeOrderNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat(Consts.ORDER_NUMBER_DATE_YYMMDD);
        DecimalFormat df = new DecimalFormat(Consts.ORDER_NUMBER_SEQ_FORMAT);
        StringBuilder sbr = new StringBuilder(Consts.ORDER_NUMBER_PREFIX);
        String date = sdf.format(new Date());
        sbr.append(date);
        Long seq = saveOrderNumberSeq();
        sbr.append(df.format(seq));
        return sbr.toString();
    }
    private Long saveOrderNumberSeq(){
        final List<Long> result = new ArrayList<>();
        jdbcTemplate.query(Consts.ORDER_NUMBER_SEQ_SQL, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.add(rs.getLong("seqNo"));
            }
        });
        return  result.get(0);
    }

    private void fillOrderNumberAndCurrentTime(String orderNumber, TOrderEntity orderEntity) {
        orderEntity.setOrderNumber(orderNumber);
        orderEntity.setCreateTime(new Date());
        orderEntity.setUpdateTime(new Date());
        orderEntity.setState(Consts.STATE_VALID);

        List<TOrderProdEntity> prods = orderEntity.getOrderProductList();
        for(TOrderProdEntity prod : prods){
            prod.setOrderNumber(orderNumber);
            prod.setCreateTime(new Date());
            prod.setUpdateTime(new Date());
            prod.setState(Consts.STATE_VALID);
        }
    }

    private TOrderEntity saveOrderAndOrderProds(TOrderEntity orderEntity){
        List<TOrderProdEntity> prods = orderEntity.getOrderProductList();
        for(TOrderProdEntity prod : prods) {
           orderProdRepository.save(prod);
        }
        return orderRepository.save(orderEntity);
    }

    public QueryResult<TOrderEntity> findOrderByPageAndParams(OrderQueryDto orderQueryDto, Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, new Sort(Direction.DESC, "createTime")); // 分页信息
        if(!Utils.isEmpty(orderQueryDto.getSalesName())) {
            Set<Long> salesIds = getSalesIdsByLikeSalesName(orderQueryDto.getSalesName());
            orderQueryDto.setSalesIds(salesIds);
        }
        if(!Utils.isEmpty(orderQueryDto.getCustomerName())){
            Set<Long> customerIds = geCustomerIdsByLikeCustomerName(orderQueryDto.getCustomerName());
            orderQueryDto.setCustomerIds(customerIds);
        }

        Specification<TOrderEntity> spec = SpecFactory.buildFindExclusiveOrderSpecification(orderQueryDto);

        Page<TOrderEntity> page = orderRepository.findAll(spec, pageable);

        return buildOrderQueryResult(page, pageNumber, pageSize);
    }
    private Set<Long> geCustomerIdsByLikeCustomerName(String customersName) {
        String custName = "%"+customersName+"%";
        List<TCustomerEntity> customerList = customerRepository.findByCustNameLike(custName);
        if(Utils.isEmpty(customerList)){
            return null;
        }
        Set<Long> customerIds = new HashSet<>();
        for (TCustomerEntity sales : customerList){
            customerIds.add(sales.getId());
        }
        return customerIds;
    }
    private QueryResult<TOrderEntity> buildOrderQueryResult(Page<TOrderEntity> page, Integer pageNumber, Integer pageSize) {
        long totalElements = 0;
        int totalPages = 0;
        List<TOrderEntity> list = new ArrayList<>(0);
        if(!Utils.isNull(page) && !Utils.isEmpty(page.getContent())){
            totalElements = page.getTotalElements();
            totalPages = page.getTotalPages();
            //deap copy list
            list = new ArrayList<>(page.getContent());
            for(TOrderEntity orderEntity:list){
                fillDeepCopySalesmanToOrder(orderEntity);
                fillDeepCopyCustomerToOrder(orderEntity);
                fillDeepCopyUserToOrder(orderEntity);
            }
        }
        return new QueryResult<>(totalPages, totalElements, pageSize, pageNumber, list);
    }

    private void fillDeepCopyUserToOrder(TOrderEntity orderEntity) {
        if(Utils.isNull(orderEntity.getUser())){
            SysUser user = sysUserRepository.findOne(orderEntity.getUserId());
            SysUser copyEntity = JSON.parseObject(JSON.toJSONString(user),SysUser.class);
            orderEntity.setUser(copyEntity);
        }
    }

    private void fillDeepCopySalesmanToOrder(TOrderEntity orderEntity) {
        if(Utils.isNull(orderEntity.getSalesmanEntity())){
            TSalesmanEntity salesmanEntity =salesmanRepository.findOne(orderEntity.getSalesId());
            TSalesmanEntity copyEntity = JSON.parseObject(JSON.toJSONString(salesmanEntity), TSalesmanEntity.class);
            orderEntity.setSalesmanEntity(copyEntity);
        }
    }
    private void fillDeepCopyCustomerToOrder(TOrderEntity orderEntity) {
        if(Utils.isNull(orderEntity.getCustomerEntity())){
            TCustomerEntity customerEntity =customerRepository.findOne(orderEntity.getCustomerId());
            TCustomerEntity copyEntity = JSON.parseObject(JSON.toJSONString(customerEntity), TCustomerEntity.class);
            orderEntity.setCustomerEntity(copyEntity);
        }
    }


    public TOrderEntity findOrderById(Long orderId) {
        if(orderId == null){
            return null;
        }
        TOrderEntity orderEntity = orderRepository.findOne(orderId);
        if(orderEntity == null){
            return null;
        }
        fillDeepCopyCustomerToOrder(orderEntity);
        fillDeepCopySalesmanToOrder(orderEntity);
        fillDeepCopyUserToOrder(orderEntity);
        fillDeepCopyOrderProdsToOrder(orderEntity);

        return orderEntity;
    }

    private void fillDeepCopyOrderProdsToOrder(TOrderEntity orderEntity) {
        List<TOrderProdEntity> orderProds = orderProdRepository.findByOrderNumber(orderEntity.getOrderNumber());
        List<TOrderProdEntity> copyEntitys = new ArrayList<>();
        for(TOrderProdEntity prod : orderProds){
            copyEntitys.add(JSON.parseObject(JSON.toJSONString(prod),TOrderProdEntity.class));
        }
        orderEntity.setOrderProductList(copyEntitys);
    }

}
