/**
 * FileName: LoginServiceImpl
 * Author:   JackMore
 * Date:     2018/10/18 17:03
 * Description:
 */
package com.door.match.service;

import com.alibaba.fastjson.JSONObject;
import com.door.match.base.ResultDto;
import com.door.match.dao.CommonDao;
import com.door.match.entity.AgeRank;
import com.door.match.entity.Profession;
import com.door.match.entity.SalaryRank;
import com.door.match.exception.BasicException;
import com.door.match.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 登录服务<br>
 * admin
 * f758f84c142dfca8c710a3db9feb36ce
 *
 * @author JackMore
 * @create 2018/10/18
 * @since 1.0.0
 */
@Service
@Slf4j
public class CommonService {


    @Autowired
    private RedisUtil redis;
    @Autowired
    CommonDao commonDao;

    public void initDic() throws BasicException {
        try {
            List<AgeRank> ageRanks = commonDao.ageRankAll();
            List<SalaryRank> salaryRanks = commonDao.salaryRankAll();
            List<Profession> professions = commonDao.professionAll();

            if (ageRanks != null && ageRanks.size() > 0) {
                redis.set("AGE", JSONObject.toJSONString(ageRanks));
            }
            if (salaryRanks != null && salaryRanks.size() > 0) {
                redis.set("SALARY", JSONObject.toJSONString(salaryRanks));
            }
            if (professions != null && professions.size() > 0) {
                redis.set("PROFESSION", JSONObject.toJSONString(professions));
            }
        } catch (Exception e) {
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "预存字典异常");
        }

    }

}