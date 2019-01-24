package com.door.match.controller;

import com.door.match.base.ResultDto;
import com.door.match.entity.RegUser;
import com.door.match.entity.ReqData;
import com.door.match.entity.SalaryRank;
import com.door.match.service.MiniLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/mini")
public class MiniLoginController {


    @Autowired
    MiniLoginService miniLoginService;


    /**
     * @Author dubin
     * @Description //登陆
     * @Date 22:00 2019/1/17
     * @Param [sysUser]
     * @return com.door.match.base.ResultDto<java.lang.String>
     **/
    
    
    @PostMapping(value = "/login")
    public ResultDto<HashMap> login(@RequestBody RegUser userinfo) {
        try {
            HashMap<String,Object> map = miniLoginService.login(userinfo);
            if(map!=null){
                return new ResultDto<HashMap>(ResultDto.CODE_SUCC, "登陆成功", map);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "登陆失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("微信登录失败：" + userinfo.getOpenid() + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }
    @PostMapping(value = "/reqbaseparam")
    public ResultDto<List> reqbaseparam(@RequestBody ReqData reqData) {
        try {
            List map = miniLoginService.reqbaseparam(reqData);
            if(map!=null){
                return new ResultDto<List>(ResultDto.CODE_SUCC, "登陆成功", map);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "登陆失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("基本参数请求失败：参数类型" + reqData.getType() + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }
    @PostMapping(value = "/subuserinfo")
    public ResultDto<Boolean> subuserinfo(@RequestBody ReqData reqData) {
        try {
            Boolean map = miniLoginService.subuserinfo(reqData);
            if(map!=null){
                return new ResultDto<Boolean>(ResultDto.CODE_SUCC, "信息录入成功成功", map);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "信息录入失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("信息录入异常，用户：" + reqData.getOpenid() + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }


}
