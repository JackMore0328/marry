/**
 * FileName: RegUser
 * Author:   JackMore
 * Date:     2019/1/7 20:27
 * Description: 注册用户
 */
package com.door.match.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @Author dubin
 * @Description //通用类请求数据分装实体
 * @Date 11:39 2019/1/20
 * @Param 
 * @return 
 **/

@Data
public class ReqData2 implements Serializable {
    private String openid;
    private Long id;
//    private Integer type;
    private String name;//自己昵称
    private String logo; //自己头像
    private String birth; //自己生日
    private Integer age;//自己年龄
    private Integer sex;  //性别id
    private Integer height;  //自己身高
    private String address;//自己地址
    private Integer income;//自己年收入id
    private String salary;//自己年收入范围
    private Integer profession; //自己职业
    private String professionstr; //自己职业
    private String education;//自己的学历
    private Integer married;//感情状况
    private Integer house;//是否购房
    private Integer car; //是否购车
    private String contact;//联系方式
    private String selfidea;//内心独白
    private String viewofmarriage;//对婚姻的看法
    private String viewofconsume;//对消费的看法
    private String viewoffuture;//对未来的规划

    //个人情况
//    private String province;
//    private String city;
//    private String area;
//
//    private Integer year;
//    private Integer month;
//    private Integer day;



    //对方情况
    private Integer age_rank_id;  //对方年龄范围id
    private Integer agerankid;  //对方年龄范围id
    private String agerank;//对方年龄范围
    private Integer heightmi;  //对方身高最小值
    private Integer heightmx;  //对方身高最大值
    private String address2;//对方地址
    private Integer income2;  //对方年收入id
    private String salary1;//对方年收入范围
    private String education2;  //对方学历
    private Integer married2;  //是否接受离异
    private Integer house2;  //是否要求有房
    private Integer car2;  //是否要求有车



//    private String partnerprovince;
//    private String partnercity;
//    private String partnerarea;



    private Integer datastatus;//数据状态
    private MultipartFile file;

}