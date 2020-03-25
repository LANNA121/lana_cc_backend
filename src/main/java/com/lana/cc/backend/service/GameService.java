package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/3/15 10:24
 */
public interface GameService {
    /**
     * 随机获取20个垃圾分类问题
     *
     * @return 垃圾分类问题的服务器返回值
     */
    ServiceResponseMessage fetchRandomGameProblem();
}
