package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.dao.GameDao;
import com.lana.cc.backend.pojo.po.GarbageSearchPO;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.RedeemPointsReq;
import com.lana.cc.backend.pojo.vo.rsp.GarbageQuestionRsp;
import com.lana.cc.backend.service.GameService;
import com.lana.cc.backend.utils.HttpUtil;
import com.lana.cc.backend.utils.Md5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 10:29
 */

@Service
public class GameServiceImpl implements GameService {
    @Resource
    GameDao gameDao;


    @Override
    public ServiceResponseMessage fetchRandomGameProblem() {
        List<GarbageSearchPO> garbageSearchRandomList = gameDao.selectRandomGameProblem(20);
        GarbageQuestionRsp garbageQuestionRsp = new GarbageQuestionRsp();
        if(null != garbageSearchRandomList && !garbageSearchRandomList.isEmpty()){
            List<GarbageQuestionRsp.Question> questions = new ArrayList<>(garbageSearchRandomList.size());
            for (GarbageSearchPO garbageSearchResult : garbageSearchRandomList) {
                GarbageQuestionRsp.Question question = new GarbageQuestionRsp.Question();
                question.setName(String.format("『 %s 』是什么垃圾 ?",garbageSearchResult.getName()));
                question.setSortId(garbageSearchResult.getSortId());
                questions.add(question);
            }
            garbageQuestionRsp.setQuestionList(questions);
        }
        return ServiceResponseMessage.createBySuccessCodeMessage(garbageQuestionRsp);
    }

    @Override
    public ServiceResponseMessage redeemPoints(RedeemPointsReq redeemPointsReq) {
        if(null != redeemPointsReq && redeemPointsReq.getCheckKey().equals(
                Md5Util.encodeByMd5(
                        redeemPointsReq.getLanaId(),
                        String.format("%s%s",redeemPointsReq.getPoint(),redeemPointsReq.getSource()),HttpUtil.getUserUid()))
        ){
            // 增加兑换记录
        }
        return null;
    }
}
