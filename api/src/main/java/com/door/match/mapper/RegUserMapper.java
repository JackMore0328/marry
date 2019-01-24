package com.door.match.mapper;

import com.door.match.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface RegUserMapper {
    RegUser selectUserByOpenid(String openid);
    int insertUser(RegUser regUser);

    int selectCountByRuserid(Long id);

    ArrayList<SalaryRank> reqSalarkRank();

    ArrayList<Profession> reqProfessionByParentID(int parentid);

    ArrayList<AgeRank> reqAgeRank();

    int updateRegUByOid(ReqData reqData);

    Long selectRUMByOpenid(String openid);

    Integer updateRegUMapByOid(ReqData reqData);

    Integer insertRegUMapByOid(ReqData reqData);
}
