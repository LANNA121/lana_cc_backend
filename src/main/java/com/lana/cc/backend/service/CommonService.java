package com.lana.cc.backend.service;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/22 11:06
 */
public interface CommonService {
    /**
     * 上传保存图片文件信息
     *
     * @param imageFile 图片文件地址
     * @return 文件上传成功与否的保存状态
     */
    ServiceResponseMessage uploadMultipartFile(MultipartFile imageFile);
}
