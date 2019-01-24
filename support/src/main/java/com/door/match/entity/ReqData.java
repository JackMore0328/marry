/**
 * FileName: RegUser
 * Author:   JackMore
 * Date:     2019/1/7 20:27
 * Description: 注册用户
 */
package com.door.match.entity;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author dubin
 * @Description //通用类请求数据分装实体
 * @Date 11:39 2019/1/20
 * @Param 
 * @return 
 **/

@Data
public class ReqData implements Serializable {
    private Long id;
    private Integer type;
    private String address;
    private String birth;
    private String address2;

    //个人情况
    private String province;
    private String city;
    private String area;

    private Integer year;
    private Integer month;
    private Integer day;

    private Integer gender;

    private Integer salaryid;

    private Integer jobid;
    private String education;
    private Integer relationship;
    private Integer house;
    private Integer car;
    private String openid;

    private Integer myheight;
    private String contactway;
    private String heartwhite;
    private String marrymind;
    private String feemind;
    private String future;
    //对方情况


    private Integer partnerheightmi;
    private Integer partnerheightmx;
    private Integer partnerrelat;
    private Integer ishavehouse;
    private Integer ishavecar;
    private String partnereducat;

    private Integer agerank;

    private String partnerprovince;
    private String partnercity;
    private String partnerarea;

    private Integer partnersalaryid;

    private Integer currentPage;
    private Integer pagesize;
    private Long start;
    private Long end;

}