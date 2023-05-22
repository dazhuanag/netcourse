package com.company.project.web;
import com.company.project.core.CommonPageQuery;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.enums.DirectionEnum;
import com.company.project.model.Course;
import com.company.project.model.CourseTime;
import com.company.project.model.User;
import com.company.project.model.vo.CourseTimeQuery;
import com.company.project.model.vo.CourseTimeVo;
import com.company.project.model.vo.CourseVo;
import com.company.project.service.CourseTimeService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程时间服务对外接口
* Created by myself on 2023/04/10.
*/
@RestController
@RequestMapping("/course/time")
public class CourseTimeController {
    @Resource
    private CourseTimeService courseTimeService;

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody CourseTime courseTime) {
        courseTimeService.save(courseTime);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        courseTimeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody CourseTime courseTime) {
        courseTimeService.update(courseTime);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Integer id) {
        CourseTime courseTime = courseTimeService.findById(id);
        return ResultGenerator.genSuccessResult(courseTime);
    }

    @PostMapping("/list")
    public Result list(@RequestBody CourseTimeQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        Condition condition = new Condition(CourseTime.class);
        Example.Criteria criteria = condition.createCriteria();
        if (query.getCourseId() != null) {
            criteria.andEqualTo("courseId", query.getCourseId());
        }
        List<CourseTime> list = courseTimeService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

//    private List<CourseTimeVo> translate(List<CourseTime> list) {
//        List<CourseTimeVo> res = new ArrayList<>();
//        for (CourseTime e : list) {
//
//            CourseTimeVo vo = new CourseTimeVo();
//            BeanUtils.copyProperties(e, vo);
//            User user = userService.findById(e.getUid());
//            if (user != null) {
//                vo.setUsername(user.getUsername());
//            }
//            res.add(vo);
//        }
//        return res;
//    }

}
