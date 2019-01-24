package com.door.match.mapper;

import com.door.match.entity.ReqData2;
import com.door.match.entity.UserImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface UpdateMapper {
    ReqData2 selectByUser(String openid);
    Long selectUserId(String openid);
    Integer updateUser(ReqData2 reqData);
    Integer updateUserMapping(ReqData2 reqData);
    ArrayList SelectUserImg(Long reg_id);
    Integer addImg(UserImg userImg);
    Integer selectAge(String openid);
}
