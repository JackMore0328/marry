/**
 * FileName: MappingRecord
 * Author:   JackMore
 * Date:     2019/1/7 20:38
 * Description: 匹配记录
 */
package com.door.match.entity;

import com.door.match.base.PersistentObject;
import lombok.Data;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈匹配记录〉
 *
 * @author JackMore
 * @create 2019/1/7
 * @since 1.0.0
 */
@Data
public class MapperRecordInfo extends MappingRecord {
    private String img;
    private Integer sex;
    private Integer age;
    private Integer car;
    private Integer house;
    private Integer height;
    private String contact;
    private String viewofmarriage;
    private String viewofconsume;
    private String viewoffuture;
    private String selfidea;
    private String salarystring;
    private String salarystring2;
    private Integer income2;
    private Integer matchingdegree;
    private Integer partnermacdeg;
    private Integer agerankid;

    private String  agestirng;
    private String address2;
    private String address;
    private Integer professionid;
    private String profession;
    private String education2;
    private String education;
    private Integer married2;
    private Integer married;
    private Integer house2;
    private Integer car2;
    private Integer heightmx;
    private Integer heightmi;

}