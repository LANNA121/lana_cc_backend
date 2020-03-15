package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.GarbageClassSearchDao;
import com.lana.cc.backend.pojo.po.GarbageCategoriesPO;
import com.lana.cc.backend.pojo.po.GarbageClassSearchPO;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.rsp.GarbageClassResp;
import com.lana.cc.backend.service.GarbageSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 12:06
 */
@Service
public class GarbageSearchServiceImpl implements GarbageSearchService {
    @Resource
    GarbageClassSearchDao garbageClassSearchDao;

    @Override
    public ServiceResponseMessage searchGarbageClassByKey(String searchKey) {
        List<GarbageClassSearchPO> garbageClassList = garbageClassSearchDao.selectGarbageClassByLikeSearchKey(searchKey);
        if(garbageClassList == null || garbageClassList.isEmpty()){
            return ServiceResponseMessage.createBySuccessCodeMessage(Collections.emptyList());
        }else {
            List<GarbageClassResp> garbageClassRespList = new ArrayList<>();
            for (GarbageClassSearchPO searchResult: garbageClassList) {
                GarbageClassResp garbageClassResp = new GarbageClassResp();
                garbageClassResp.setName(searchResult.getName());
                garbageClassResp.setSortId(searchResult.getSortId());
                garbageClassRespList.add(garbageClassResp);
            }
            return ServiceResponseMessage.createBySuccessCodeMessage(garbageClassRespList);
        }
    }

    @Override
    public ServiceResponseMessage searchGarbageCategoriesByNum(Integer classNum) {
        GarbageCategoriesPO garbageCategories = garbageClassSearchDao.selectGarbageCategoriesByNum(classNum);
        if(garbageCategories != null){
            return ServiceResponseMessage.createBySuccessCodeMessage(garbageCategories);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage();
    }
}
