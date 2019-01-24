package com.door.match.controller;

import com.door.match.base.ResultDto;
import com.door.match.entity.SysUser;
import com.door.match.exception.BasicException;
import com.door.match.service.SysService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/sys")
public class SysController {


    @Autowired
    SysService sysService;


    /**
     * 登录
     *
     * @param sysUser
     * @return
     */
    @PostMapping(value = "/login")
    public ResultDto<String> login(@RequestBody SysUser sysUser) {
        try {
            String result = sysService.login(sysUser);
            return new ResultDto<>(ResultDto.CODE_SUCC, "", result);
        } catch (BasicException e) {
            log.error("登录失败：" + sysUser.getName() + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        } catch (Exception e) {
            log.error("登录失败：" + sysUser.getName() + "：", e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, "登录失败", null);
        }
    }


    /**
     * 登出
     *
     * @param token
     * @return
     */
    @GetMapping(value = "/logout")
    public ResultDto<String> logout(@RequestHeader String token) {
        try {
            sysService.logout(token);
            return new ResultDto<>(ResultDto.CODE_SUCC, "登出成功", null);
        } catch (BasicException e) {
            log.error("登出失败：" + token + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        } catch (Exception e) {
            log.error("登出失败：" + token + "：", e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, "登出失败", null);
        }
    }

    @GetMapping(value = "/dic/{type}")
    public ResultDto<String> getDic(@PathVariable String type) {
        try {
            return new ResultDto<>(ResultDto.CODE_SUCC, "获取成功", sysService.getRedisDic(type));
        } catch (Exception e) {
            log.error("获取失败：" + type, e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, "获取失败", null);
        }
    }

}
