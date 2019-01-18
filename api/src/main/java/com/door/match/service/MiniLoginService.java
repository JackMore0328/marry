/**
 * FileName: LoginServiceImpl
 * Author:   JackMore
 * Date:     2018/10/18 17:03
 * Description:
 */
package com.door.match.service;

import com.alibaba.fastjson.JSONObject;
import com.door.match.entity.RegUser;
import com.door.match.mapper.RegUserMapper;
import com.door.match.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author dubin
 * @Description //小程序登陆服务
 * @Date 22:02 2019/1/17
 * @Param
 * @return
 **/


@Service
@Slf4j
public class MiniLoginService {

    @Autowired
    private RegUserMapper regUserMapper;
    @Value("${wx.miniprogram.login}")
    private String url;
    @Value("${wx.miniprogram.appid}")
    private String appid;
    @Value("${wx.miniprogram.secret}")
    private String secret;

    public String login(RegUser userinfo) {

        String code = userinfo.getCode();
        String url1 =url
                .replace("APPID", appid).replace("SECRET", secret)
                .replace("JSCODE", code);
        JSONObject json=HttpUtil.doGetstr(url1);
        if(json.toString()!=null&&!json.toString().trim().equals(""))
            return joinUser(json.toJSONString(), userinfo);
        return null;

    }
    private String joinUser( String loginjson,RegUser userinfo){
        JSONObject json = JSONObject.parseObject(loginjson);
//        String session_key = json.getString("session_key");
        String openid = json.getString("openid");
       if (openid!=null&&!openid.trim().equals("")) {
           RegUser user = regUserMapper.selectUserByOpenid(openid);
           int count = 0;
           if (user == null) {
               userinfo.setOpenid(openid);
               count = regUserMapper.insertUser(userinfo);
           }
           if (user != null || count == 1)
               return openid;
       }
        return null;

    }
}