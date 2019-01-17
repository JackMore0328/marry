package com.door.match.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久化基类
 * @author jiangwenwu
 * @Description:
 * @Title: PersistentObject.java
 * @Company: DOOR
 * @date 2018年1月22日下午2:10:18
 */
@Data
public class PersistentObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Date startTime;
    private Date endTime;
    private String startDateStr;
    private String endDateStr;
    private Integer startNo;
    private Integer endNo;
    private Integer pageNo;
    private Integer pageSize;
    private Long total;
    private String keyWords;
    private String name;
    private String password;

}