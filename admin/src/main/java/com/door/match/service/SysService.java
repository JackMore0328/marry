/**
 * FileName: LoginServiceImpl
 * Author:   JackMore
 * Date:     2018/10/18 17:03
 * Description:
 */
package com.door.match.service;

import com.alibaba.fastjson.JSONObject;
import com.door.match.base.Constants;
import com.door.match.base.ResultDto;
import com.door.match.dao.UserDao;
import com.door.match.entity.SysUser;
import com.door.match.exception.BasicException;
import com.door.match.utils.FtdStringUtil;
import com.door.match.utils.PasswordUtil;
import com.door.match.utils.RandomUtil;
import com.door.match.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SysService {

    @Autowired
    private UserDao userDao;


    @Autowired
    private RedisUtil redis;

    /**
     * 登录验证
     */
    public String login(SysUser sysUser) throws BasicException {
        log.info(sysUser.getName() + ",登录平台");
        if (FtdStringUtil.isEmpty(sysUser.getName())) {
            log.error("账户名不能为空");
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "账户名不能为空");
        }
        if (FtdStringUtil.isEmpty(sysUser.getPassword())) {
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "密码不能为空");
        }
        sysUser.setPassword(PasswordUtil.MD5Salt(sysUser.getPassword()));
        SysUser user = userDao.findUserByNameAndPassword(sysUser);
        if (user == null) {
            log.error("用户名或密码不正确！");
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "用户名或密码不正确！");
        }
        String token = RandomUtil.getRandomString(32);
        redis.set(Constants.SYSPREFIX + token, JSONObject.toJSONString(user));
        return token;
    }


    /**
     * 登出
     */
    public void logout(String token) throws BasicException {
        if (!redis.exists(Constants.SYSPREFIX + token)) {
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "删除数据失败,无此token");
        }
        SysUser sysUser = JSONObject.parseObject(redis.get(Constants.SYSPREFIX + token), SysUser.class);
        log.info(sysUser.getName() + "--退出平台");
        redis.remove(Constants.SYSPREFIX + token);
    }


    public String getRedisDic(String type) throws BasicException {
        try {
            return redis.get(type);
        } catch (Exception e) {
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "缓存不存在");
        }
    }

}