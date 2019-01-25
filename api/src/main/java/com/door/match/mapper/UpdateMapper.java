package com.door.match.mapper;

import com.door.match.entity.MapperRecordInfo;
import com.door.match.entity.ReqData;
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
    ArrayList<UserImg> SelectUserImg(Long reg_id);
    Integer addImg(UserImg userImg);
    Integer selectAge(String openid);
    MapperRecordInfo getmapperinfo(ReqData reqData);

    int deletimg(ReqData imgid);

    Integer partnermacdeg(MapperRecordInfo mid);

    ArrayList<UserImg> SelectUserImgByOid(ReqData imgid);

    MapperRecordInfo getuserinfo(ReqData oid);
}
