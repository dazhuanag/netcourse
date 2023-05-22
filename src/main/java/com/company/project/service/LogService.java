package com.company.project.service;
import com.company.project.model.Log;
import com.company.project.core.Service;


/**
 * 日志服务接口
 * Created by myself on 2023/04/10.
 */
public interface LogService extends Service<Log> {

    void log(Integer uid, String content);

}
