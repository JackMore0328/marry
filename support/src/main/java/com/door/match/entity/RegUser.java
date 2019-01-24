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
 * @Author dubin
 * @Description //微信小程序注册用户
 * @Date 11:39 2019/1/20
 * @Param 
 * @return 
 **/


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
    private Integer profession;
    private String education;
    private Integer married;
    private Integer house;
    private Integer car;
    private String remark;
    private Date createTime;
    private Date modifyTime;
    private Integer dataStatus;
    private String openid;
    private Integer firstinfostatu;
    private RegUserMapping regUserMapping;
    //以下封装请求参数
    private String nickName;
    private String avatarUrl;
    private String country;
    private String province;
    private Integer gender;
    private String code;



}