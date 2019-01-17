/**
 * FileName: SysOrder
 * Author:   JackMore
 * Date:     2019/1/7 20:31
 * Description: 订单表
 */
package com.door.match.entity;

import com.door.match.base.PersistentObject;
import lombok.Data;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈订单表〉
 *
 * @author JackMore
 * @create 2019/1/7
 * @since 1.0.0
 */
@Data
public class SysOrder extends PersistentObject {
    private Long id;
    private String orderNo;
    private Long regUserId;
    private String name;
    private Date createTime;
    private Integer pay;
    private Integer orderStatus;
    private Integer dataStatus;

}