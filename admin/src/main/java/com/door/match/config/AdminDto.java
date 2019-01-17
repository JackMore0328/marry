/**
 * FileName: DtoUtil
 * Author:   JackMore
 * Date:     2018/11/1 20:32
 * Description:
 */
package com.door.match.config;

import com.door.match.base.PersistentObject;
import com.door.match.base.SearcherRequest;

/**
 * 〈类型转换〉<br>
 *
 * @author JackMore
 * @create 2018/11/1
 * @since 1.0.0
 */
public class AdminDto<T extends PersistentObject> {

    private T bean ;

    public AdminDto<T> transfer(SearcherRequest searcherRequest) {
        if (searcherRequest.getUserName() != null) {
            bean.setName(searcherRequest.getUserName());
        }
        if (searcherRequest.getPassword() != null) {
            bean.setPassword(searcherRequest.getPassword());
        }
        if (searcherRequest.getStartTime() != null) {
            bean.setStartTime(searcherRequest.getStartTime());
        }
        if (searcherRequest.getEndTime() != null) {
            bean.setEndTime(searcherRequest.getEndTime());
        }
        if (searcherRequest.getStartDateStr() != null) {
            bean.setStartDateStr(searcherRequest.getStartDateStr());
        }
        if (searcherRequest.getEndDateStr() != null) {
            bean.setEndDateStr(searcherRequest.getEndDateStr());
        }
        if (searcherRequest.getEndDateStr() != null) {
            bean.setKeyWords(searcherRequest.getEndDateStr());
        }
        if (searcherRequest.getPageNo() != null) {
            bean.setPageNo(searcherRequest.getPageNo());
        }
        if (searcherRequest.getPageSize() != null) {
            bean.setPageSize(searcherRequest.getPageSize());
        }
        if (searcherRequest.getStartNo() != null) {
            bean.setStartNo(searcherRequest.getStartNo());
        }
        if (searcherRequest.getEndNo() != null) {
            bean.setEndNo(searcherRequest.getEndNo());
        }
        return this;
    }
    public AdminDto(T obj) {
        this.bean = obj;
    }
    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}