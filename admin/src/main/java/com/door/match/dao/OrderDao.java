package com.door.match.dao;

import com.door.match.entity.SysOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    SysOrder findSysOrderById(@Param("id") Long id);

    List<SysOrder> listSysOrder(SysOrder user);

    long countSysOrder(SysOrder user);

}
