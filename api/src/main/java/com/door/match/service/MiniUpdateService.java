package com.door.match.service;

import com.door.match.entity.*;
import com.door.match.mapper.RegUserMapper;
import com.door.match.mapper.UpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MiniUpdateService {

    @Autowired
    UpdateMapper updateMapper;
    @Autowired
    RegUserMapper regUserMapper;

    //修改资料前的查询
    public Map select(String openid){
        Map map = new HashMap();
        ArrayList<AgeRank> ageRank = regUserMapper.reqAgeRank();
        ArrayList<SalaryRank> salaryRank = regUserMapper.reqSalarkRank();
        ReqData2 reqData = updateMapper.selectByUser(openid);
        map.put("ageArray",ageRank);
        map.put("salaryArray",salaryRank);
        map.put("reqData",reqData);
        System.out.println(map);
        return map;
    }

    //修改资料
    public Integer update(ReqData2 reqData){
        Long id = updateMapper.selectUserId(reqData.getOpenid());
        reqData.setId(id);
//        System.out.println(reqData);
        Integer i = updateMapper.updateUser(reqData);
        Integer j = updateMapper.updateUserMapping(reqData);
        if (i==1&&j==1){
            return 1;
        }
        return 0;
    }

    //个人主页的查询
    public Map userSelect(String openid){
        HashMap map = new HashMap();
        Integer age = updateMapper.selectAge(openid);
        Long reg_id = updateMapper.selectUserId(openid);
        ReqData2 reqData = updateMapper.selectByUser(openid);
        ArrayList imgList = updateMapper.SelectUserImg(reg_id);
        reqData.setAge(age);
        System.out.println(imgList);
        map.put("reqData",reqData);
        map.put("imgList",imgList);
        return map;
    }

    //添加图片
    public Integer addImg(UserImg userImg){
        String openid = userImg.getOpenid();
        Long reg_id = updateMapper.selectUserId(openid);
        userImg.setReg_id(reg_id);
        Integer num = updateMapper.addImg(userImg);
        return num;
    }
}
