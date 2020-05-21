package com.xiongs.dataway.service.impl;

import com.xiongs.dataway.dao.UserInfoMapper;
import com.xiongs.dataway.model.UserInfo;
import com.xiongs.dataway.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public UserInfo findById(String id) {
        return userInfoMapper.findById(id);
    }
}
