package com.chen.tian.papermgr.service;

import com.chen.tian.papermgr.constant.Consts;
import com.chen.tian.papermgr.dto.QueryResult;
import com.chen.tian.papermgr.entity.TProductAttrEntity;
import com.chen.tian.papermgr.entity.TProductEntity;
import com.chen.tian.papermgr.repository.ProductAttrRepository;
import com.chen.tian.papermgr.repository.ProductRepository;
import com.chen.tian.papermgr.repository.specification.SpecFactory;
import com.chen.tian.papermgr.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 产品管理模块服务层
 * Created by ChenTian on 2018/5/11.
 */
@Service
public class ProductService {
    @Qualifier("productRepository")
    @Autowired
    private ProductRepository productRepository;
    @Qualifier("productAttrRepository")
    @Autowired
    private ProductAttrRepository productAttrRepository;


    public QueryResult<TProductEntity> findProductByPageAndParams(
            String prodName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = new PageRequest(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "createTime")); // 分页信息

        Specification<TProductEntity> spec = SpecFactory.buildFindExclusiveProductSpecification(prodName);

        Page<TProductEntity> page = productRepository.findAll(spec, pageable);

        return buildProductQueryResult(page, pageNumber, pageSize);
    }

    public List<TProductEntity> findAllProduct(){
        List<TProductEntity> list = productRepository.findAll();
        for(TProductEntity product : list){
            fillProductAttr(product);
        }
        return  list;
    }

    private QueryResult<TProductEntity> buildProductQueryResult(Page<TProductEntity> page,Integer pageNumber,Integer pageSize) {
        long totalElements = 0;
        int totalPages = 0;
        if(!Utils.isNull(page) && !Utils.isEmpty(page.getContent())){
            totalElements = page.getTotalElements();
            totalPages = page.getTotalPages();
            for(TProductEntity product : page.getContent()){
                fillProductAttr(product);
            }
        }
        return new QueryResult<>(totalPages, totalElements, pageSize, pageNumber, page.getContent());
    }

    public TProductEntity productDetail(Long productId) {
        if (Utils.isNull(productId)) {
            return null;
        }
        TProductEntity productEntity = productRepository.findOne(productId);
        fillProductAttr(productEntity);
        return productEntity;
    }

    private void fillProductAttr(TProductEntity productEntity) {
        List<TProductAttrEntity> attrs = productAttrRepository.findAll();

        for(TProductAttrEntity attr : attrs){
            String valueList = attr.getValueList();
            String valueDescription = attr.getValueDescription();
            if(!Utils.isEmpty(valueList)){
                String[] valueArrays = valueList.split(",");
                attr.setValuesList(Arrays.asList(valueArrays));
            }
            if(!Utils.isEmpty(valueDescription)){
                String[] valueDesArrays = valueDescription.split(",");
                attr.setValueDescriptionList(Arrays.asList(valueDesArrays));
            }
            fillSpecialAttrSpec(attr, valueList, valueDescription);
        }

        productEntity.setProdAttrs(attrs);
    }

    private void fillSpecialAttrSpec(TProductAttrEntity attr, String valueList, String valueDescription) {
        if(attr.getId() == 2){ //规格特殊处理
            String[] valueArrays = valueList.split(",");
            String[] valueDesArrays = valueDescription.split(",");
            List<String> specAreaList = new ArrayList<>();
            List<String> specWideList = new ArrayList<>();
            List<String> specAreaDescriptionList = new ArrayList<>();
            List<String> specWideDescriptionList = new ArrayList<>();

            int splitIndex = 0 ;
            for(int i=0 ; i< valueArrays.length ; i++){
                specAreaList.add(valueArrays[i]);
                specAreaDescriptionList.add(valueDesArrays[i]);
                if(valueArrays[i].equals("-1")){
                    splitIndex = i ;
                    break;
                }
            }
            for(int i=(splitIndex+1) ; i< valueArrays.length ; i++){
                specWideList.add(valueArrays[i]);
                specWideDescriptionList.add(valueDesArrays[i]);
            }
            specWideList.add(valueArrays[splitIndex]);
            specWideDescriptionList.add(valueDesArrays[splitIndex]);

            attr.setSpecAreaList(specAreaList);
            attr.setSpecWideList(specWideList);
            attr.setSpecAreaDescriptionList(specAreaDescriptionList);
            attr.setSpecWideDescriptionList(specWideDescriptionList);
        }
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteProduct(Long productId) {
        if (Utils.isNull(productId)) {
            return;
        }
        productRepository.updateStateById(Consts.STATE_DELETED,new Date(),productId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public TProductEntity productAddOrUpdate(TProductEntity productEntity) throws Exception {
        if (Utils.isNull(productEntity)) {
            throw new Exception("业务员信息不能为空");
        }
        if(Utils.isNull(productEntity.getId())){
            productEntity.setCreateTime(new Date());
            productEntity.setUpdateTime(new Date());
            productEntity.setState(Consts.STATE_VALID);

            return productRepository.save(productEntity);
        } else {
            TProductEntity oldProduct = productRepository.findOne(productEntity.getId());
            if(Utils.isNull(oldProduct)){
                throw new Exception("未找到修改的业务员信息");
            }

            productEntity.setCreateTime(oldProduct.getCreateTime());
            productEntity.setUpdateTime(new Date());
            if(Utils.isNull(productEntity.getState())) {
                productEntity.setState(oldProduct.getState());
            }
            return productRepository.save(productEntity);
        }
    }
}

