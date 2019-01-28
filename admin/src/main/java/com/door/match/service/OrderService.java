package com.door.match.service;

import com.door.match.base.PageBean;
import com.door.match.base.PageDto;
import com.door.match.base.ResultDto;
import com.door.match.base.SearcherRequest;
import com.door.match.config.AdminDto;
import com.door.match.dao.OrderDao;
import com.door.match.entity.PayPO;
import com.door.match.entity.SysOrder;
import com.door.match.exception.BasicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;


    public PayPO findSysOrderById(Long id) throws BasicException {
        try {
            log.debug("获取订单详情，id:" + id);
            return orderDao.findSysOrderById(id);
        } catch (Exception e) {
            log.error("获取订单详情失败，id:" + id, e.getMessage());
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "获取订单详情失败，id:" + id);
        }
    }

    /**
     * 订单列表
     *
     * @return
     * @throws Exception
     */
    public PageDto<PayPO> list(SearcherRequest searcherRequest) throws BasicException {
        PayPO obj = new AdminDto<>(new PayPO()).transfer(searcherRequest).getBean();
        PageBean<PayPO> pageBean = new PageBean<PayPO>(obj) {
            @Override
            protected Long generateRowCount() throws BasicException {
                return orderDao.countSysOrder(obj);
            }

            @Override
            protected List<PayPO> generateBeanList(PayPO bean) throws BasicException {
                return orderDao.listSysOrder(obj);
            }
        }.execute();
        long total = pageBean.getRowCount();
        List<PayPO> beanList = pageBean.getBeanList();

        if (beanList == null) {
            return null;
        }
        return new PageDto<>(total, beanList);
    }

}
