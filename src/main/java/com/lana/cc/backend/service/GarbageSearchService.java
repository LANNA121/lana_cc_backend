package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.CategoriesReq;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 12:05
 */
public interface GarbageSearchService {
    /**
     * 查询指定Key的垃圾分类信息
     *
     * @param searchKey 查询的关键Key
     * @return 查询到的统一消息返回结果
     */
    ServiceResponseMessage searchGarbageClassByKey(String searchKey);


    /**
     * 查询指定类别垃圾的详细信息
     *
     * @param classNum 垃圾类别
     * @return 详细的垃圾分类信息
     */
    ServiceResponseMessage searchGarbageCategoriesByNum(Integer classNum);

    /**
     * 修改自动分类的分类信息
     *
     * @param classKey 分类的商品Key
     * @param classNum 分类需要
     * @return 修改的商品分类信息
     */
    ServiceResponseMessage modifyClassCategories(String classKey, Integer classNum);

    /**
     * 创建增加新的分类属性信息
     *
     * @param categoriesReq 分类相关信息
     * @return 增加后的处理结果
     */
    ServiceResponseMessage postClassCategories(CategoriesReq categoriesReq);

    /**
     * 删除垃圾分类属性结构
     *
     * @param classKey 需要删除的Key
     * @return 删除后的处理结果
     */
    ServiceResponseMessage deleteClassCategories(String classKey);
}
