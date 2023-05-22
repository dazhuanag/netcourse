package com.company.project.service.impl;

import com.company.project.dao.CourseTimeMapper;
import com.company.project.model.CourseTime;
import com.company.project.service.CourseTimeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 课程时长服务实现类
 * Created by myself on 2023/04/10.
 */
@Service
@Transactional
public class CourseTimeServiceImpl extends AbstractService<CourseTime> implements CourseTimeService {
    @Resource
    private CourseTimeMapper courseTimeMapper;

}
