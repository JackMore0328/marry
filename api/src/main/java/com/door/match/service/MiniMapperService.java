/**
 * FileName: LoginServiceImpl
 * Author:   JackMore
 * Date:     2018/10/18 17:03
 * Description:
 */
package com.door.match.service;

import com.alibaba.fastjson.JSONObject;
import com.door.match.entity.*;
import com.door.match.mapper.RegMapperMapper;
import com.door.match.mapper.RegUserMapper;
import com.door.match.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author dubin
 * @Description //小程序匹配服务
 * @Date 22:02 2019/1/17
 * @Param
 * @return
 **/


@Service
@Slf4j
public class MiniMapperService {

    @Autowired
    private RegMapperMapper regMapperMapper;
//    @Value("${wx.miniprogram.login}")
//    private String url;
//    @Value("${wx.miniprogram.appid}")
//    private String appid;
//    @Value("${wx.miniprogram.secret}")
//    private String secret;
    @Transactional
    public List imgchangelsit(ReqData reqData) {

       Long count= regMapperMapper.selectcountExOpenid(reqData);
       if(count!=null&&count>0){
           BigDecimal cccou=new BigDecimal(count).divide(new BigDecimal(6),2, BigDecimal.ROUND_HALF_UP);
           int paggecount=cccou.setScale( 0, BigDecimal.ROUND_UP ).intValue();
           int num = new Random().nextInt(paggecount)+1;
           reqData.setStart((num-1)*6l);
           reqData.setEnd((num*6l));
           ArrayList<String > map=regMapperMapper.selectImgExOpenid(reqData);
          if(map!=null&&map.size()>0){
              return map;
          }
       }
       return  null;

    }
    @Transactional
    public Integer mapperagain(ReqData reqData) {
        RegUserMapping rmInfo=regMapperMapper.selectRMInfoByOid(reqData);
        if(rmInfo!=null){
            ArrayList<MappingRecord> allmapper=regMapperMapper.mapperagain(rmInfo);
            if(allmapper!=null){
                int all=allmapper.size()/100;
                if(allmapper.size()%100>0){
                    all=all+1;
                }
                int allcount=0;
                for (int i=0; i<all;i++){
                    List<MappingRecord> onemapper;
                    if(i==(all-1)) {
                         onemapper = allmapper.subList(i * 100, allmapper.size());
                    }else{
                         onemapper=allmapper.subList(i*100,(i+1)*100);
                    }
                    int count= regMapperMapper.insertMappRecord(onemapper);
                    allcount=allcount+count;
                }
                return allcount;
            }
        }
        return null;
    }


    public List<MapperRecordInfo> getmapperlist(ReqData reqData) {
        Long start= (reqData.getCurrentPage()-1l)*reqData.getPagesize();
        Long end = reqData.getCurrentPage()*1l*reqData.getPagesize();
        reqData.setStart(start);
        reqData.setEnd(end);
        List<MapperRecordInfo> infolist =regMapperMapper.getmapperlist(reqData);
        if(infolist!=null){
            return infolist;
        }
        return  null;
    }
}