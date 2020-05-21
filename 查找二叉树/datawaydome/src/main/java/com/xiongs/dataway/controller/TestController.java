package com.xiongs.dataway.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiongs.dataway.model.UserInfo;
import com.xiongs.dataway.service.InterfaceService;
import com.xiongs.dataway.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController extends BasicController<UserInfo>{
    @Autowired
    InterfaceService interfaceService;
    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/test")
    public String test(String id){
        RestTemplate restTemplate = new RestTemplate();
        return userInfoService.findById(id).toString();
    }
    @RequestMapping("/getUser")
    public UserInfo getUser(String id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        return select(userInfo);
    }
}
