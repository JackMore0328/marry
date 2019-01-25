package com.door.match.service;

import com.door.match.entity.*;
import com.door.match.mapper.RegMapperMapper;
import com.door.match.mapper.RegUserMapper;
import com.door.match.mapper.UpdateMapper;
import com.door.match.utils.OSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    OSSClientUtil ossClientUtil;

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
    public Map userSelect(ReqData mid){
        HashMap map = new HashMap();
        MapperRecordInfo age = updateMapper.getmapperinfo(mid);
        Integer partnermacdeg=updateMapper.partnermacdeg(age);
        age.setPartnermacdeg(partnermacdeg);
        map.put("reqData",age);
        return map;
    }

    //添加图片
    public Integer addImg(ReqData2 reqData2){
        MultipartFile file = reqData2.getFile();
        String name = ossClientUtil.uploadImg2Oss(file);
        String imgUrl = ossClientUtil.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        String url=split[0];
        String openid = reqData2.getOpenid();
        Long reg_id = updateMapper.selectUserId(openid);
        UserImg userImg = new UserImg();
        userImg.setRegid(reg_id);
        userImg.setUserimg(url);
        Integer num = updateMapper.addImg(userImg);
        return num;
    }

    public Integer deletimg(ReqData imgid) {

       int count= updateMapper.deletimg(imgid);
        if(count>0){

            return count;
        }
        return null;
    }

    public ArrayList<UserImg> getuserimg(ReqData imgid) {
        if(imgid.getMid()!=null&&imgid.getMid()>0){
            ArrayList<UserImg> imgList = updateMapper.SelectUserImg(imgid.getMid());
            if(imgList!=null&&imgList.size()>0){
                return imgList;
            }
        }else{
            ArrayList<UserImg> imgList = updateMapper.SelectUserImgByOid(imgid);
            if(imgList!=null&&imgList.size()>0){
                return imgList;
            }
        }
        return null;
    }

    public MapperRecordInfo getuserinfo(ReqData oid) {
        MapperRecordInfo age = updateMapper.getuserinfo(oid);
        return age;
    }
}
