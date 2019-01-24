package com.door.match.dao;

import com.door.match.entity.AgeRank;
import com.door.match.entity.Profession;
import com.door.match.entity.SalaryRank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommonDao {


    List<AgeRank> ageRankAll();

    List<SalaryRank> salaryRankAll();

    List<Profession> professionAll();


}
