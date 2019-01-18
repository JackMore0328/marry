package com.door.match.controller;

import com.door.match.base.ResultDto;
import com.door.match.entity.RegUser;
import com.door.match.service.MiniLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public ResultDto<String> login(@RequestBody RegUser userinfo) {
        try {
            String result = miniLoginService.login(userinfo);
            if(result!=null&&!result.trim().equals("")){
                return new ResultDto<>(ResultDto.CODE_SUCC, "登陆成功", result);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "登陆失败", null);
            }
        } catch (Exception e) {
            log.error("微信登录失败：" + userinfo.getOpenid() + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }


}
