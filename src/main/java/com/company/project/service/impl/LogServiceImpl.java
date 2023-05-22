package com.company.project.service.impl;

import com.company.project.dao.LogMapper;
import com.company.project.model.Log;
import com.company.project.service.LogService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 日志服务实现类
 * Created by myself on 2023/04/10.
 */
@Service
@Transactional
public class LogServiceImpl extends AbstractService<Log> implements LogService {
    @Resource
    private LogMapper logMapper;

    /**
     * 暴露该接口给其他服务使用
     * @param uid
     * @param content
     */
    @Override
    public void log(Integer uid, String content) {
        Log log = new Log();
        log.setUid(uid);
        log.setContent(content);
        this.save(log);
    }

}
