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
import java.util.ArrayList;

/**
 * @Author dubin
 * @Description //薪资等级实体
 * @Date 11:38 2019/1/20
 * @Param 
 * @return 
 **/


@Data
public class Profession implements Serializable {
    private Integer id;
    private  Integer parentid;
    private String name;
    private Integer sequence;
    private ArrayList<Profession> children;



}