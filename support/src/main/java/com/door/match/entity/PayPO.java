package com.door.match.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PayPO implements Serializable {

	private static final long serialVersionUID = 2924407312887280370L;

	private Integer id;

    private String payNo;

    private String transcationId;

    private Integer userId;

    private Integer flag;

    private Integer way;

    private BigDecimal money;

    private BigDecimal trueMoney;

    private String extra;

    private Integer status;

    private Date notifyAt;

    private Date createdAt;

    private Date updatedAt;


}