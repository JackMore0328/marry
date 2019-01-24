/**
 * FileName: RegUserMapping
 * Author:   JackMore
 * Date:     2019/1/7 20:33
 * Description: 匹配详情
 */
package com.door.match.entity;

import com.door.match.base.PersistentObject;
import lombok.Data;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈匹配详情〉
 *
 * @author JackMore
 * @create 2019/1/7
 * @since 1.0.0
 */
@Data
public class RegUserMapping extends PersistentObject {
    private Long id;
    private Long regUserId;
    private String contact;
    private String selfIdea;
    private String viewOfMarriage;
    private String viewOfConsume;
    private String viewOfFuture;
    private Integer sex2;
    private String address2;
    private Integer age_rank_id;
    private Integer height2;
    private Integer weight2;
    private Integer income2;
    private Integer profession2;
    private String education2;
    private Integer married2;
    private Integer house2;
    private Integer car2;
    private Date createTime;
    private Date modifyTime;
    private Integer dataStatus;
    private Integer heightmi;
    private Integer heightmx;

}