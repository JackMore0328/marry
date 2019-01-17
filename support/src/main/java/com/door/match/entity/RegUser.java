/**
 * FileName: RegUser
 * Author:   JackMore
 * Date:     2019/1/7 20:27
 * Description: 注册用户
 */
package com.door.match.entity;

import com.door.match.base.PersistentObject;
import lombok.Data;

import java.util.Date;

/**
 * 〈注册用户〉
 *
 * @author JackMore
 * @create 2019/1/7
 * @since 1.0.0
 */
@Data
public class RegUser extends PersistentObject {
    private Long id;
    private String phone;
    private String birth;
    private Integer sex;
    private String address;
    private Integer height;
    private Integer weight;
    private Integer income;
    private String logo;
    private String img;
    private String profession;
    private Integer education;
    private Integer married;
    private Integer house;
    private Integer car;
    private String remark;
    private Date createTime;
    private Date modifyTime;
    private Integer dataStatus;

    private RegUserMapping regUserMapping;


}