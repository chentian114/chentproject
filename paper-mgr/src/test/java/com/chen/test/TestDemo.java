package com.chen.test;

import com.alibaba.fastjson.JSON;
import com.chen.tian.papermgr.dto.CustomerDto;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.dto.ResultInfo;
import com.chen.tian.papermgr.entity.SysResource;
import com.chen.tian.papermgr.entity.SysUser;
import com.chen.tian.papermgr.entity.TCustomerEntity;
import com.chen.tian.papermgr.entity.TSalesmanEntity;
import com.chen.tian.papermgr.repository.CustomerRepository;
import com.chen.tian.papermgr.repository.SalesmanRepository;
import com.chen.tian.papermgr.repository.SysResourceRepository;
import com.chen.tian.papermgr.repository.SysUserRepository;
import com.chen.tian.papermgr.service.AdminService;
import com.chen.tian.papermgr.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenTian on 2018/5/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class TestDemo {
    @Qualifier("sysUserRepository")
    @Autowired
    private SysUserRepository sysUserRepository;
    @Autowired
    private SysResourceRepository sysResourceRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void testSeq(){
        String sql = "select sequence_nextval('ORDER_NUMBER_SEQ') as seqNo ";
        final List<Long> result = new ArrayList();
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                result.add(rs.getLong("seqNo"));
            }});


        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void test1(){
        String account="admin0";
        System.out.println("hello");
        SysUser user = sysUserRepository.findByAccount(account);
        System.out.println(user);
        System.out.println(user.getName());
        System.out.println(user.getRole());
        System.out.println(user.getRole().getName());
        System.out.println(user.getRole().getResources());
        System.out.println(JSON.toJSONString(user));


        System.out.println(user.getRole().getResources().size());
    }

    @Test
    public void test2(){
        SysResource sys = sysResourceRepository.findOne(5L);
        System.out.println(sys.getName());
      //  System.out.println(sys.getParent());

    }

    @Test
    public void test3(){
        SysResource sys = sysResourceRepository.findOne(1L);
        System.out.println(sys.getName());
      //  System.out.println(sys.getParent());
     //   System.out.println(sys.getChildren().size());
    }

    @Test
    public void test4(){
        ResultInfo resultInfo = adminService.login("admin0", "E10ADC3949BA59ABBE56E057F20F883E");
        System.out.println(resultInfo.getResultData());
        System.out.println(JSON.toJSONString(resultInfo));
    }


    @Autowired
    private OrderService orderService;
    @Test
    public void test5(){
        CustomerDto customerDto = new CustomerDto();
        Integer pageNumber=1;
        Integer pageSize =10;
        QueryResult<TCustomerEntity> result= orderService.findCustomerByPageAndParams(customerDto, pageNumber, pageSize);
        System.out.println(JSON.toJSONString(result));
    }
    @Test
    public void test5_2(){
        CustomerDto customerDto = new CustomerDto();
        //customerDto.setAddress("州");
        //customerDto.setPhone("181");
        customerDto.setSalesName("晰");
        Integer pageNumber=1;
        Integer pageSize =10;
        QueryResult<TCustomerEntity> result= orderService.findCustomerByPageAndParams(customerDto, pageNumber, pageSize);
        System.out.println(JSON.toJSONString(result));
    }

    @Autowired
    private SalesmanRepository salesmanRepository;
    @Test
    public void test6(){
        String salesName = "%天%";
        List<TSalesmanEntity> list = salesmanRepository.findBySalesNameLike(salesName);
        System.out.println(JSON.toJSONString(list));
        StringBuilder salesIds = new StringBuilder();
        for (TSalesmanEntity sales : list){
            salesIds.append(sales.getId()).append(",");
        }
        System.out.println(salesIds.toString());
        String res = salesIds.delete(salesIds.length()-1,salesIds.length()).toString();
        System.out.println(res);
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Test
    public void test7(){
        TCustomerEntity customerEntity = customerRepository.findOne(1L);
        System.out.println(JSON.toJSONString(customerEntity));
    }
}
