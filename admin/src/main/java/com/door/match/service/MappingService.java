package com.door.match.service;

import com.door.match.base.PageBean;
import com.door.match.base.PageDto;
import com.door.match.base.ResultDto;
import com.door.match.base.SearcherRequest;
import com.door.match.config.AdminDto;
import com.door.match.dao.MappingDao;
import com.door.match.entity.MappingRecord;
import com.door.match.exception.BasicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MappingService {

    @Autowired
    private MappingDao mappingDao;


    public List<MappingRecord> findMappingRecordById(Long id) throws BasicException {
        try {
            log.debug("获取匹配详情，id:" + id);
            return mappingDao.findMappingRecordById(id);
        } catch (Exception e) {
            log.error("获取匹配详情失败，id:" + id, e.getMessage());
            throw new BasicException(ResultDto.CODE_BUZ_ERROR, "获取匹配详情失败，id:" + id);
        }
    }

    /**
     * 订单列表
     *
     * @return
     * @throws Exception
     */
    public PageDto<MappingRecord> list(SearcherRequest searcherRequest) throws BasicException {
        MappingRecord obj = new AdminDto<>(new MappingRecord()).transfer(searcherRequest).getBean();
        PageBean<MappingRecord> pageBean = new PageBean<MappingRecord>(obj) {
            @Override
            protected Long generateRowCount() throws BasicException {
                return mappingDao.countMappingRecord(obj);
            }

            @Override
            protected List<MappingRecord> generateBeanList(MappingRecord bean) throws BasicException {
                return mappingDao.listAllMappingRecord(obj);
            }
        }.execute();
        long total = pageBean.getRowCount();
        List<MappingRecord> beanList = pageBean.getBeanList();
        if (beanList == null) {
            return null;
        }
        beanList.forEach(a -> {
            MappingRecord bean = new MappingRecord();
            bean.setId(a.getRegUserId());
            long l = mappingDao.countMappingRecord(new MappingRecord());
            a.setMappingCount(l);
        });

        return new PageDto<>(total, beanList);
    }

}
