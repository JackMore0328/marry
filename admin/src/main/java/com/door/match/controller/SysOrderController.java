package com.door.match.controller;

import com.door.match.annotations.ValidToken;
import com.door.match.base.PageDto;
import com.door.match.base.ResultDto;
import com.door.match.base.SearcherRequest;
import com.door.match.entity.PayPO;
import com.door.match.entity.SysOrder;
import com.door.match.exception.BasicException;
import com.door.match.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/order")
public class SysOrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @ValidToken
    @GetMapping(value = "/detail/{id}")
    public ResultDto<PayPO> detail(@PathVariable Long id) {
        try {
            PayPO sysOrder = orderService.findSysOrderById(id);
            return new ResultDto<>(ResultDto.CODE_SUCC, "查询成功", sysOrder);
        } catch (Exception e) {
            log.error("获取订单详情失败,id:" + id + "：", e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getMessage(), null);
        }
    }
    //@ValidToken
    @PostMapping("/list")
    public ResultDto<PageDto<PayPO>> login(@RequestBody SearcherRequest searcherRequest) {
        try {
            PageDto<PayPO> list = orderService.list(searcherRequest);
            return new ResultDto<>(ResultDto.CODE_SUCC, "查询成功", list);
        } catch (BasicException e) {
            log.error("获取订单列表失败：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        } catch (Exception e) {
            log.error("获取订单列表失败：", e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, "查询失败", null);
        }
    }

}
