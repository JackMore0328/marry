package com.door.match.controller;

import com.door.match.base.ResultDto;
import com.door.match.entity.MapperRecordInfo;
import com.door.match.entity.RegUser;
import com.door.match.entity.ReqData;
import com.door.match.service.MiniLoginService;
import com.door.match.service.MiniMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author dubin
 * @Description //匹配功能控制器
 * @Date 16:12 2019/1/23
 * @Param 
 * @return 
 **/


@Slf4j
@RestController
@RequestMapping("/mapper")
public class MiniMapperController {


    @Autowired
    MiniMapperService miniMapperService;


    /**
     * @Author dubin
     * @Description //重新匹配
     * @Date 16:14 2019/1/23
     * @Param [reqData]
     * @return com.door.match.base.ResultDto<java.lang.Integer>
     **/
    @PostMapping(value = "/mapperagain")
    public ResultDto<Integer> mapperagain(@RequestBody ReqData reqData) {
        try {
            Integer map = miniMapperService.mapperagain(reqData);
            if(map!=null){
                return new ResultDto<Integer>(ResultDto.CODE_SUCC, "匹配信息成功", map);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "匹配信息失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("匹配信息失败：" + reqData.getOpenid() + "：" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }
    @PostMapping(value = "/imgchangelsit")
    public ResultDto<List> imgchangelsit(@RequestBody ReqData reqData) {
        try {
            List map = miniMapperService.imgchangelsit(reqData);
            if(map!=null){
                return new ResultDto<List>(ResultDto.CODE_SUCC, "图像成功", map);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "图像失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取图像失败" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }

    @PostMapping(value = "/getmapperlist")
    public ResultDto<List> getmapperlist(@RequestBody ReqData reqData) {
        try {
            List<MapperRecordInfo> map = miniMapperService.getmapperlist(reqData);
            if(map!=null){
                return new ResultDto<List>(ResultDto.CODE_SUCC, "图像成功", map);
            }else{
                return new ResultDto<>(ResultDto.CODE_AUTH_ERROR, "图像失败", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取图像失败" + e.getLocalizedMessage());
            return new ResultDto<>(ResultDto.CODE_BUZ_ERROR, e.getLocalizedMessage(), null);
        }
    }


}
