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
public class MappingRecord extends PersistentObject {
    private Long id;
    private Long regUserId;
    private Long mappingId;
    private Date createTime;
    private Integer dataStatus;
    private Long mappingCount;

}