package com.door.match.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.door.match.entity.ReqData2;
import com.door.match.entity.UserImg;
import com.door.match.service.MiniUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@RestController
public class MiniUpdateController {

    @Autowired
    MiniUpdateService miniUpdateService;

//    修改页面的查询
    @PostMapping("/select")
    public Map select(@RequestBody String openid){
        JSONObject jsonObject = JSON.parseObject(openid);
        String openid1 = jsonObject.getString("openid");
        System.out.println(openid1);
        Map map = miniUpdateService.select(openid1);
        return map;
    }

//    修改资料
    @PostMapping("/update")
    public Integer update(@RequestBody ReqData2 reqData){
        System.out.println(reqData);
        Integer num = miniUpdateService.update(reqData);
        return num;
    }

//    个人主页的查询
    @PostMapping("/userSelect")
    public Map userSelect(@RequestBody String openid){
        JSONObject jsonObject = JSON.parseObject(openid);
        String openid1 = jsonObject.getString("openid");
        Map map = miniUpdateService.userSelect(openid1);
        return map;
    }

    @PostMapping("/addImg")
    public Integer addImg(@RequestBody UserImg userImg){
        System.out.println(userImg);
        Integer num = miniUpdateService.addImg(userImg);
        return num;
    }
}
