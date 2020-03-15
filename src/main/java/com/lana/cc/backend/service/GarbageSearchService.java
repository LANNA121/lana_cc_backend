package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;

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
}
