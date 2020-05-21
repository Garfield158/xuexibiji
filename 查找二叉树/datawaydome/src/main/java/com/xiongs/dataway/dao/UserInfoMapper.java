package com.xiongs.dataway.dao;

import com.xiongs.dataway.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoMapper {
    @Select("SELECT * FROM userInfo WHERE id=#{id}")
    UserInfo findById(@Param("id") String id);
}
