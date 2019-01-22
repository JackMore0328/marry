/**
 * FileName: RegUser
 * Author:   JackMore
 * Date:     2019/1/7 20:27
 * Description: 注册用户
 */
package com.door.match.entity;

import com.door.match.base.PersistentObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author dubin
 * @Description //薪资等级实体
 * @Date 11:38 2019/1/20
 * @Param 
 * @return 
 **/


@Data
public class AgeRank implements Serializable {
    /*
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '年龄等级id',
  `agerank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '年龄等级值',
  `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatetime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `minage` int(3) DEFAULT NULL COMMENT '最小年龄',
  `maxage` int(3) DEFAULT NULL COMMENT '最大年龄',
     **/


    private Integer id;
    private String agerank;
    private Integer minage;
    private Integer maxage;




}