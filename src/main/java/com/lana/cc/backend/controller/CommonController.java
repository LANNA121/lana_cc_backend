package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Security(roles = RoleEnum.ALL,checkToken = false)
    public ServiceResponseMessage upload(@RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.IMAGE_IS_EMPTY, "图片不存在");
        }
        return commonService.uploadMultipartFile(imageFile);
    }
}
