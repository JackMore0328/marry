package com.door.match.dao;

import com.door.match.entity.PayPO;
import com.door.match.entity.SysOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderDao {

    PayPO findSysOrderById(@Param("id") Long id);

    List<PayPO> listSysOrder(PayPO user);

    long countSysOrder(PayPO user);

}
