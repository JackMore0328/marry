package com.door.match.controller;

import com.door.match.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
@Configuration
public class InitController implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CommonService commonService;

    /**
     * 初始化 参数
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            //commonService.initDic();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化字典异常", e);
        }
    }


}
