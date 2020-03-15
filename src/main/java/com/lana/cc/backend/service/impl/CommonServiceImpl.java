package com.lana.cc.backend.service.impl;

import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.rsp.UploadImageRsp;
import com.lana.cc.backend.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/22 11:06
 */

@Service
public class CommonServiceImpl implements CommonService {
    Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Value("${lana.upload.image.filepath}")
    private String imageFilePath;

    @Override
    public ServiceResponseMessage uploadMultipartFile(MultipartFile imageFile) {
        try {
            // 获取文件名称
            String fileName = imageFile.getOriginalFilename();
            //获取文件名称的后缀
            String suffixName = fileName != null ? fileName.substring(fileName.lastIndexOf(".")) : ".jpg";
            String newFileName = String.format("%s%s",UUID.randomUUID().toString(),suffixName);
            //设置文件存储路径
            String path = imageFilePath + newFileName;
            logger.debug("上传的文件名：{} 修改后的文件名：{} 文件全路径：{}",fileName,newFileName,path);
            File dest = new File(path);
            //检测目录是否存在
            if (!dest.getParentFile().exists()) {
                if(dest.getParentFile().mkdirs()){
                    logger.info("创建文件目录成功");
                }
            }
            imageFile.transferTo(dest);
            UploadImageRsp uploadImageRsp = new UploadImageRsp();
            uploadImageRsp.setImagePath(newFileName);
            return ServiceResponseMessage.createBySuccessCodeMessage("文件上传成功", uploadImageRsp);
        } catch (IOException ex) {
            logger.error("文件保存失败",ex);
            return ServiceResponseMessage.createByFailCodeMessage("文件保存失败");
        }
    }
}
