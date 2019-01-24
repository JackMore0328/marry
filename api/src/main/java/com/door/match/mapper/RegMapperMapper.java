package com.door.match.mapper;

import com.door.match.entity.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RegMapperMapper {

    Long selectcountExOpenid(ReqData reqData);

    ArrayList selectImgExOpenid(ReqData reqData);

    RegUserMapping selectRMInfoByOid(ReqData reqData);

    ArrayList<MappingRecord> mapperagain(RegUserMapping rmInfo);

    int insertMappRecord(List<MappingRecord> list);

    List<MapperRecordInfo> getmapperlist(ReqData reqData);
}
