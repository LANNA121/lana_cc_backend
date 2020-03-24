package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ResultCodeEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.CategoriesReq;
import com.lana.cc.backend.service.CommonService;
import com.lana.cc.backend.service.GarbageSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

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
    @Autowired
    GarbageSearchService garbageSearchService;

    @ResponseBody
    @PostMapping(value = "/upload")
    @Security(roles = RoleEnum.ALL,checkToken = false)
    public ServiceResponseMessage upload(@RequestParam("imageFile") MultipartFile imageFile) {
        if (imageFile.isEmpty()) {
            ServiceResponseMessage.createByFailCodeMessage(ResultCodeEnum.IMAGE_IS_EMPTY, "图片不存在");
        }
        return commonService.uploadMultipartFile(imageFile);
    }
    @ResponseBody
    @Security(roles = RoleEnum.ALL,checkToken = false)
    @GetMapping(value = "/tools/{searchKey}/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage searchGarbageClassByKey(@PathVariable @NotBlank String searchKey){
        return garbageSearchService.searchGarbageClassByKey(searchKey);
    }
    @ResponseBody
    @Security(roles = RoleEnum.ALL,checkToken = false)
    @GetMapping(value = "/tools/{classNum}/categories",produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage searchGarbageCategoriesByNum(@PathVariable @Min(1) @Max(4) Integer classNum){
        return garbageSearchService.searchGarbageCategoriesByNum(classNum);
    }

    @ResponseBody
    @Security(roles = RoleEnum.OSS)
    @PutMapping(value = "/tools/categories",consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage modifyClassCategories(@RequestBody CategoriesReq categoriesReq){
        return garbageSearchService.modifyClassCategories(categoriesReq.getClassKey(),categoriesReq.getClassNum());
    }

    @ResponseBody
    @Security(roles = RoleEnum.OSS)
    @PostMapping(value = "/tools/categories",consumes =MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage postClassCategories(@RequestBody CategoriesReq categoriesReq){
        return garbageSearchService.postClassCategories(categoriesReq);
    }

}
