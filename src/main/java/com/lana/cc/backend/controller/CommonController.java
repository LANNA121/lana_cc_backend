package com.lana.cc.backend.controller;

import com.lana.cc.backend.aspect.ExceptionHandlerAspect;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.rsp.UploadImageRsp;
import com.lana.cc.backend.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/22 10:17
 */

@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    CommonService commonService;

    @ResponseBody
    @PostMapping(value = "/upload")
    public ServiceResponseMessage upload(@RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.IMAGE_NULL, "图片不存在");
        }
        return commonService.uploadMultipartFile(imageFile);

    }
}
