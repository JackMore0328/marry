package com.door.match.mapper;

import com.door.match.entity.RegUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegUserMapper {
    RegUser selectUserByOpenid(String openid);
    int insertUser(RegUser regUser);
}
