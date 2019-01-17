package com.door.match.dao;

import com.door.match.entity.MappingRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MappingDao {


    List<MappingRecord> findMappingRecordById(@Param("id") Long id);

    List<MappingRecord> listAllMappingRecord(MappingRecord record);

    public long countMappingRecord(MappingRecord record);

}
