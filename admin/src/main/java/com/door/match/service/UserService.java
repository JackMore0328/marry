package com.door.match.service;

import com.door.match.base.PageBean;
import com.door.match.base.PageDto;
import com.door.match.base.SearcherRequest;
import com.door.match.config.AdminDto;
import com.door.match.dao.UserDao;
import com.door.match.entity.RegUser;
import com.door.match.entity.UserMappingDto;
import com.door.match.exception.BasicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired

    SysService sysService;


    public UserMappingDto findRegUser(Long id) throws BasicException {
        log.info("获取注册用户详情，id: "+id);
        UserMappingDto user = userDao.findRegUserById(id);
        return user;
    }

    /**
     * 注册用户列表
     *
     * @return
     * @throws Exception
     */
    public PageDto<RegUser> list(SearcherRequest searcherRequest) throws BasicException {
        log.info("获取注册用户列表 ");
        RegUser obj = new AdminDto<>(new RegUser()).transfer(searcherRequest).getBean();
        PageBean<RegUser> pageBean = new PageBean<RegUser>(obj) {
            @Override
            protected Long generateRowCount() throws BasicException {
                return userDao.countRegUsers(obj);
            }

            @Override
            protected List<RegUser> generateBeanList(RegUser bean) throws BasicException {
                return userDao.listRegUser(obj);
            }
        }.execute();
        long total = pageBean.getRowCount();
        List<RegUser> beanList = pageBean.getBeanList();

        if (beanList == null) {
            return null;
        }
        return new PageDto<>(total, beanList);
    }

}
