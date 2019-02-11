package com.door.match.controller;

import com.door.match.annotations.ValidToken;
import com.door.match.base.PageDto;
import com.door.match.base.ResultDto;
import com.door.match.base.SearcherRequest;
import com.door.match.entity.RegUser;
import com.door.match.entity.UserMappingDto;
import com.door.match.exception.BasicException;
import com.door.match.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/reg")
public class RegUserController {

    @Autowired
    UserService userService;

    /**
     * 注册用户详情
     *
     * @param id
     * @return
     */
    //@ValidToken
    @GetMapping(value = "/detail/{id}")
    public ResultDto<UserMappingDto> detail(@PathVariable Long id) {
        try {
            UserMappingDto regUser = userService.findRegUser(id);
            return new ResultDto<>(ResultDto.CODE_SUCC, "查询成功", regUser);
        } catch (Exception e) {
            log.error("获取用户详情失败,id:" + id + "：", e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getMessage(), null);
        }
    }

    @ValidToken
    @PostMapping("/list")
    public ResultDto<PageDto<RegUser>> list(@RequestBody SearcherRequest searcherRequest) {
        try {
            PageDto<RegUser> list = userService.list(searcherRequest);
            return new ResultDto<>(ResultDto.CODE_SUCC, "查询成功", list);
        } catch (BasicException e) {
            log.error("获取用户列表失败：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        } catch (Exception e) {
            log.error("获取用户列表失败：", e);
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, "查询失败", null);
        }
    }
}
