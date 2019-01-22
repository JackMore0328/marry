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
public class SalaryRank implements Serializable {
    private Integer id;
    private String salary;




}