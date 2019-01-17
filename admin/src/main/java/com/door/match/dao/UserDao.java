package com.door.match.dao;

import com.door.match.entity.RegUser;
import com.door.match.entity.SysUser;
import com.door.match.entity.UserMappingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {


    SysUser findUserByNameAndPassword(SysUser user);

    UserMappingDto findRegUserById(@Param("id") Long id);

    List<RegUser> listRegUser(RegUser user);

    long countRegUsers(RegUser user);

}
