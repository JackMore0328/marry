package com.door.match.entity;


import com.door.match.base.PersistentObject;
import com.door.match.utils.FtdDateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class SysUser extends PersistentObject {

    private Long id;
    private String name;
    private String password;
    private String remark;
    private Date createTime;
    private String createTimeStr;
    private Date modifyTime;
    private Date modifyTimeStr;
    private Integer dataStatus;


    public String getCreateTimeStr() {
        return FtdDateUtil.formatSdf(createTime);
    }

    public String getModifyTimeStr() {
        return FtdDateUtil.formatSdf(modifyTime);
    }


}
