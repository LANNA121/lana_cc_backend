package com.lana.cc.backend.controller;

import com.lana.cc.backend.annotation.Security;
import com.lana.cc.backend.pojo.enums.RoleEnum;
import com.lana.cc.backend.pojo.vo.common.ServiceResponseMessage;
import com.lana.cc.backend.pojo.vo.req.*;
import com.lana.cc.backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author LANA
 * @version 1.0
 * @date 2020/2/20 17:38
 */

@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    @Autowired
    AccountService accountService;

    @Security(roles = RoleEnum.ALL, checkToken = false)
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage login(@RequestBody LoginReq loginReq) {
        return accountService.login(loginReq);
    }

    @Security(roles = RoleEnum.ALL, checkToken = false)
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage signIn(@RequestBody RegisterReq registerReq) {
        return accountService.signIn(registerReq);
    }

    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
    @PutMapping(value = "/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage modifyProfile(@RequestBody ModifyProfileReq modifyProfileReq) {
        return accountService.modifyProfile(modifyProfileReq);
    }

    @Security(roles = RoleEnum.OSS)
    @PutMapping(value = "/modify/password", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage modifyAccountPassword(@RequestBody ModifyAccountPasswordReq modifyAccountPasswordReq) {
        return accountService.modifyAccountPassword(modifyAccountPasswordReq);
    }

    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchProfileByUid(@RequestParam(value = "uid",required = false) Integer uid) {
        return accountService.fetchProfileByUid(uid);
    }

    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
    @DeleteMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage deleteAccountProfileByUid(@RequestParam(value = "uid") Integer uid) {
        return accountService.deleteAccountProfileByUid(uid);
    }

    @Security(roles = RoleEnum.OSS)
    @GetMapping(value = "/all/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchAllProfile() {
        return accountService.fetchAllProfile();
    }


    @Security(roles = RoleEnum.OSS)
    @PostMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage modifyUserIdentityPromotion(@RequestBody UidAndIdentityReq uidAndIdentityReq) {
        return accountService.modifyUserIdentityPromotion(uidAndIdentityReq.getUid(),uidAndIdentityReq.getRole());
    }

    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
    @GetMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage fetchAllAddressByUid(@RequestParam(value = "uid",required = false) Integer uid) {
        return accountService.fetchAllAddressByUid(uid);
    }

    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
    @PostMapping(value = "/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage postNewAddress(@RequestBody AddressReq addressReq) {
        return accountService.createNewAddress(addressReq);
    }

    @Security(roles = {RoleEnum.USER, RoleEnum.OSS})
    @DeleteMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
    public ServiceResponseMessage deleteAddressByIdAndUid(@RequestParam(value = "addressId",defaultValue = "0") int addressId, @RequestParam(value = "uid",required = false) Integer uid) {
        return accountService.deleteAddressByIdAndUid(addressId,uid);
    }
}
