package com.company.project.service.impl;

import com.company.project.core.AbstractService;
import com.company.project.dao.CourseHitMapper;
import com.company.project.dao.CourseMapper;
import com.company.project.model.Course;
import com.company.project.model.CourseHit;
import com.company.project.service.CourseHitService;
import com.company.project.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 课程服务实现类
 * Created by myself on 2023/04/10.
 */
@Service
@Transactional
public class CourseHitServiceImpl extends AbstractService<CourseHit> implements CourseHitService {

    @Resource
    private CourseHitMapper courseHitMapper;

}
