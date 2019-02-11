/**
 * FileName: LoginServiceImpl
 * Author:   JackMore
 * Date:     2018/10/18 17:03
 * Description:
 */
package com.door.match.service;

import com.alibaba.fastjson.JSONObject;
import com.door.match.entity.*;
import com.door.match.mapper.RegUserMapper;
import com.door.match.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Transactional
    public HashMap<String, Object> login(RegUser userinfo) {

        String code = userinfo.getCode();
        String url1 =url
                .replace("APPID", appid).replace("SECRET", secret)
                .replace("JSCODE", code);
        JSONObject json=HttpUtil.doGetstr(url1);
        if(json.toString()!=null&&!json.toString().trim().equals("")){
            return joinUser(json.toJSONString(), userinfo);
        }
        return null;

    }

    private HashMap<String, Object> joinUser( String loginjson,RegUser userinfo){
        JSONObject json = JSONObject.parseObject(loginjson);
        String openid = json.getString("openid");
        HashMap<String, Object> map=null;
       if (openid!=null&&!openid.trim().equals("")) {
           RegUser user = regUserMapper.selectUserByOpenid(openid);
           if (user == null) {
               userinfo.setOpenid(openid);
               int count = regUserMapper.insertUser(userinfo);
               if(count==1){
                   map=new HashMap<String, Object>();
                   map.put("count",0);
                   map.put("openid",openid);
               }
           }else{
               Long umid=regUserMapper.selectRUMByOpenid(openid);
               if(umid!=null &&umid.longValue()>0){
                   map=new HashMap<String, Object>();
                   map.put("count",umid);
                   map.put("openid",openid);
               }else{
                   map=new HashMap<String, Object>();
                   map.put("count",0);
                   map.put("openid",openid);
               }
           }
       }
        return map;

    }
    @Transactional
    public ArrayList reqbaseparam(ReqData reqData) {
        if(reqData.getType()!=null){
            if (reqData.getType().intValue()==4){
                ArrayList<SalaryRank> listparam= regUserMapper.reqSalarkRank();
                return listparam;
            }else if(reqData.getType().intValue()==5){
                //职业
                ArrayList<Profession> listparam= regUserMapper.reqProfessionByParentID(1);
                if(listparam!=null&&listparam.size()>0){
                    for (Profession profession:listparam ) {
                        ArrayList<Profession> listparamchildren= regUserMapper.reqProfessionByParentID(profession.getId());
                        if(listparamchildren!=null){
                            profession.setChildren(listparamchildren);
                        }

                    }
                    return listparam;
                }

            }else if(reqData.getType().intValue()==10){
                //年龄
                ArrayList<AgeRank> listparam= regUserMapper.reqAgeRank();
                return listparam;
            }

        }
        return null;
    }
    @Transactional
    public Boolean subuserinfo(ReqData reqData) {
        String province = reqData.getProvince();
        String city = reqData.getCity();
        String area = reqData.getArea();
        if(city.trim().equals("*")||city.trim().equals("全部")){
            reqData.setAddress(province);
        }else if(area.trim().equals("*")||area.trim().equals("全部")){
            reqData.setAddress(province+"@@"+city);
        }else{
            reqData.setAddress(province+"@@"+city+"@@"+area);
        }
        Calendar c=Calendar.getInstance();
        c.set(reqData.getYear(),reqData.getMonth(),reqData.getDay());
        Date d=c.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        reqData.setBirth(sdf.format(d));
        int count=regUserMapper.updateRegUByOid(reqData);
        String province1 = reqData.getPartnerprovince();
        String city1 = reqData.getPartnercity();
        String area1 = reqData.getPartnerarea();
        if(city1.trim().equals("*")||city1.trim().equals("全部")){
            reqData.setAddress2(province1);
        }else if(area1.trim().equals("*")||area1.trim().equals("全部")){
            reqData.setAddress2(province1+"@@"+city1);
        }else{
            reqData.setAddress2(province1+"@@"+city1+"@@"+area1);
        }
       if(count>0){
           Long umid=regUserMapper.selectRUMByOpenid(reqData.getOpenid());

           Integer count1;
           if(umid!=null&&umid>0){
               reqData.setId(umid);
               count1=regUserMapper.updateRegUMapByOid(reqData);
           }else{
               count1=regUserMapper.insertRegUMapByOid(reqData);
           }
           if(count1!=null&&count1==1){
               return true;
           }
       }
       return  false;

    }

}