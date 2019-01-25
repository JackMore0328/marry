package com.door.match.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.door.match.base.ResultDto;
import com.door.match.entity.MapperRecordInfo;
import com.door.match.entity.ReqData;
import com.door.match.entity.ReqData2;
import com.door.match.entity.UserImg;
import com.door.match.service.MiniUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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
    public ResultDto<Map> userSelect(@RequestBody ReqData mid){
        try {
            Map map = miniUpdateService.userSelect(mid);
            if(map!=null){
                return new ResultDto<Map>(ResultDto.CODE_SUCC, "对象信息获取成功", map);
            }else{
                return new ResultDto<Map>(ResultDto.CODE_AUTH_ERROR, "对象信息获取失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("对象信息获取失败" + e.getLocalizedMessage());
            return new ResultDto<Map>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }
    /**
     * @Author dubin
     * @Description //获取用户自己详细信息
     * @Date 16:26 2019/1/25
     * @Param [mid]
     * @return com.door.match.entity.MapperRecordInfo
     **/
   @PostMapping("/getuserinfo")
    public ResultDto<MapperRecordInfo> getuserinfo(@RequestBody ReqData oid){
        try {
            MapperRecordInfo map = miniUpdateService.getuserinfo(oid);
            if(map!=null){
                return new ResultDto<MapperRecordInfo>(ResultDto.CODE_SUCC, "对象信息获取成功", map);
            }else{
                return new ResultDto<MapperRecordInfo>(ResultDto.CODE_AUTH_ERROR, "对象信息获取失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("对象信息获取失败" + e.getLocalizedMessage());
            return new ResultDto<MapperRecordInfo>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }
    /**
     * @Author dubin
     * @Description //删除图片
     * @Date 16:26 2019/1/25
     * @Param [imgid]
     * @return com.door.match.base.ResultDto<java.lang.Integer>
     **/
    
    
    @PostMapping("/deletimg")
    public ResultDto<Integer> deletimg(@RequestBody ReqData imgid){
        try {
            Integer map = miniUpdateService.deletimg(imgid);
            if(map!=null){
                return new ResultDto<Integer>(ResultDto.CODE_SUCC, "图片删除成功", map);
            }else{
                return new ResultDto<Integer>(ResultDto.CODE_AUTH_ERROR, "图片删除失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("图片删除失败" + e.getLocalizedMessage());
            return new ResultDto<Integer>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }
//获取图片
    @PostMapping("/getuserimg")
    public ResultDto<ArrayList> getuserimg(@RequestBody ReqData imgid){
        try {
            ArrayList<UserImg> imgList = miniUpdateService.getuserimg(imgid);
            if(imgList!=null){
                return new ResultDto<ArrayList>(ResultDto.CODE_SUCC, "图片获取成功", imgList);
            }else{
                return new ResultDto<ArrayList>(ResultDto.CODE_AUTH_ERROR, "图片获取失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("图片获取失败" + e.getLocalizedMessage());
            return new ResultDto<ArrayList>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }

    /**
     * @Author dubin
     * @Description //上传图片
     * @Date 17:23 2019/1/25
     * @Param [reqData2]
     * @return java.lang.Integer
     **/
    @PostMapping("/addImg")
    public Integer addImg(ReqData2 reqData2){
        Integer num = miniUpdateService.addImg(reqData2);
        return num;
    }
}
