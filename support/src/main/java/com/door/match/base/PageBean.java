package com.door.match.base;

import com.google.common.collect.Lists;
import com.door.match.exception.BasicException;

import java.util.List;

/**
 * 分页存储数据
 *
 * @Description:
 * @Title: PageBean.java
 */
public abstract class PageBean<T extends PersistentObject> {

    /** 默认每页行数 */
    //public static final int DEFAULT_PAGE_SIZE = 20;
    /**
     * 每页行数
     */
    private int pageSize;
    /**
     * 记录总数
     */
    private Long rowCount;
    /**
     * 当前页码
     */
    private int pageNo = 1;


    T bean;

    List<T> beanList;


    public PageBean(T obj) {
        this.bean = obj;
        this.init();
    }

    private void init() {
        beanList = Lists.newArrayList();
    }

    public PageBean<T> execute() throws BasicException {
        this.setPageSize();
        this.setRowCount();
        this.setPageNo();
        bean.setStartNo(this.getStartNo());
        bean.setEndNo(this.getEndNo());
        if (rowCount > 0) this.setBeanList();
        return this;
    }


    protected abstract Long generateRowCount() throws BasicException;

    protected abstract List<T> generateBeanList(T bean) throws BasicException;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize() {
        this.pageSize = bean.getPageSize();
    }


    public Integer getStartNo() {
        return (pageNo - 1) * pageSize;
    }

    public Integer getEndNo() {
        return pageSize * pageNo;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount() throws RuntimeException {
        this.rowCount = generateRowCount();
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo() throws RuntimeException {
        this.pageNo = bean.getPageNo();
    }


    /**
     * 第一条数据位置
     *
     * @return
     */
    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }


    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }

    public List<T> getBeanList() {
        return beanList;
    }

    public void setBeanList() throws RuntimeException {
        this.beanList = generateBeanList(bean);
    }

    /**
     * 获取总页数
     */
    public long getPageCount() {
        if (rowCount % pageSize == 0)
            return rowCount / pageSize;
        else
            return (rowCount / pageSize) + 1;
    }
}
