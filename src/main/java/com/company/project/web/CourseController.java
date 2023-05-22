package com.company.project.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.core.CommonPageQuery;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.enums.DirectionEnum;
import com.company.project.enums.RoleEnum;
import com.company.project.enums.SexEnum;
import com.company.project.model.Course;
import com.company.project.model.CourseHit;
import com.company.project.model.CourseTime;
import com.company.project.model.User;
import com.company.project.model.vo.CourseQuery;
import com.company.project.model.vo.CourseVo;
import com.company.project.model.vo.StudyTimeVo;
import com.company.project.model.vo.UserVo;
import com.company.project.service.CourseHitService;
import com.company.project.service.CourseService;
import com.company.project.service.CourseTimeService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 课程服务对外接口
 * Created by myself on 2023/04/10.
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;
    @Resource
    private CourseHitService courseHitService;

    @Resource
    private UserService userService;

    @Resource
    private CourseTimeService courseTimeService;

    @PostMapping("/counthit")
    public Result counthit(@RequestBody CourseQuery query) {
        Condition condition = new Condition(Course.class);
        Example.Criteria criteria = condition.createCriteria();
        if (query.getCourseType() != null) {
            criteria.andEqualTo("courseType", query.getCourseType());
        }
        List<Course> courseList = courseService.findByCondition(condition);
        List<Integer> courseIds = courseList.stream().map(Course::getCourseId).collect(Collectors.toList());
        Condition condition1 = new Condition(CourseHit.class);
        Example.Criteria criteria1 = condition1.createCriteria();
        criteria1.andIn("courseId", courseIds);
        List<CourseHit> courseHits = courseHitService.findByCondition(condition1);
//        Map<Integer, List<CourseHit>> collect = courseHits.stream().collect(Collectors.groupingBy(CourseHit::getUserId));
//        Set<Integer> userIds = collect.keySet();

        // 区分出课程和点击量
        List<String> xData = new ArrayList<>();
        List<Integer> yData = new ArrayList<>();
//        List<JSONObject> yData = new ArrayList<>();
        for (Course course : courseList) {
            xData.add(course.getCourseName());
            yData.add(course.getHitCount());
        }
        List<Integer> zData = new ArrayList<>();
        for (Course course : courseList) {
            xData.add(course.getCourseName());
            List<CourseHit> collect = courseHits.stream().filter(item -> item.getCourseId().equals(course.getCourseId())).collect(Collectors.toList());
            zData.add(collect.size());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("x", xData);
        map.put("y", yData);
        map.put("z", zData);
        return ResultGenerator.genSuccessResult(map);
    }

    @PostMapping("/studytime")
    public Result studytime(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUsername())) {
            return ResultGenerator.genFailResult("请输入学生姓名");
        }

        User findUser = userService.findBy("username", user.getUsername());
        if (findUser == null) {
            return ResultGenerator.genFailResult("学生不存在");
        }

        Condition condition = new Condition(CourseTime.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("uid", findUser.getUid());
        List<CourseTime> courseTimeList = courseTimeService.findByCondition(condition);
        // 按课程分组求和
        Map<Integer, Integer> collect = courseTimeList.stream().collect(Collectors.groupingBy(CourseTime::getCourseId, Collectors.summingInt(CourseTime::getTime)));
        List<StudyTimeVo> dataList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : collect.entrySet()) {
            Course course = courseService.findById(e.getKey());
            if (course != null) {
                dataList.add(new StudyTimeVo(course.getCourseName(), e.getValue() / 60 + 1));
            }
        }
        return ResultGenerator.genSuccessResult(dataList);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Course course) {
        courseService.save(course);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Course course) {
        courseService.deleteById(course.getCourseId());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody JSONObject json) {
        Course course = JSONObject.parseObject(JSON.toJSONString(json), Course.class);
        Integer uid = json.getInteger("uid");
        if (course.getHitCount() != null) {
            Course byId = courseService.findById(course.getCourseId());
            if (byId != null) {
                course.setHitCount(byId.getHitCount() + 1);
                CourseHit courseHit = new CourseHit();
                courseHit.setCourseId(byId.getCourseId());
                courseHit.setUserId(uid);
                courseHitService.save(courseHit);
            } else {
                course.setHitCount(null);
            }
        }
        courseService.update(course);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Course cond) {
        Course course = courseService.findById(cond.getCourseId());
        return ResultGenerator.genSuccessResult(course);
    }

    @PostMapping("/list")
    public Result list(@RequestBody CourseQuery query) {
        if (query.getUid() != null) {
            User user = userService.findById(query.getUid());
            query.setCourseType(user.getDirection());
        }
        PageHelper.startPage(query.getPage(), query.getSize());
        Condition condition = new Condition(Course.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotBlank(query.getCourseName())) {
            criteria.andLike("courseName", "%" + query.getCourseName() + "%");
        }
        if (query.getCourseType() != null) {
            criteria.andEqualTo("courseType", query.getCourseType());
        }
        List<Course> list = courseService.findByCondition(condition);
        Condition condition1 = new Condition(CourseTime.class);
        Example.Criteria criteria1 = condition1.createCriteria();
        criteria1.andEqualTo("uid", query.getUid());
        List<Integer> collect = list.stream().map(Course::getCourseId).collect(Collectors.toList());
        criteria1.andIn("courseId", collect);
        List<CourseTime> courseTimes = courseTimeService.findByCondition(condition1);
        PageInfo pageInfo = new PageInfo(translate(list, courseTimes));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/listOtherPopularCourse")
    public Result listOtherCourseType(@RequestBody CourseQuery query) {
        if (query.getUid() != null) {
            User user = userService.findById(query.getUid());
            query.setCourseType(user.getDirection());
        }
        PageHelper.startPage(query.getPage(), query.getSize());
        Condition condition = new Condition(Course.class);
        Example.Criteria criteria = condition.createCriteria();
        if (query.getCourseType() != null) {
            criteria.andNotEqualTo("courseType", query.getCourseType());
        }
        List<Course> list = courseService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(translateOtherCourseType(list));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    private List<CourseVo> translate(List<Course> list, List<CourseTime> courseTimes) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<Course> learnOrderCourses = list.stream().sorted(Comparator.comparing(Course::getSort)).collect(Collectors.toList());
        List<Integer> sortsList = learnOrderCourses.stream().map(Course::getSort).distinct().collect(Collectors.toList());

        List<Integer> courseIds = learnOrderCourses.stream().map(Course::getCourseId).collect(Collectors.toList());
        Map<Integer, List<CourseTime>> allCourseMap = courseTimes.stream().collect(Collectors.groupingBy(CourseTime::getCourseId));

        List<Course> collect = list.stream().sorted(Comparator.comparing(Course::getHitCount).reversed()).collect(Collectors.toList());
        Course first = null;
        Course second = null;
        if (collect.size() >= 1) {
            first = collect.get(0);
        }
        if (collect.size() >= 2) {
            second = collect.get(1);
        }

        List<CourseVo> res = new ArrayList<>();
        for (Course course : learnOrderCourses) {
            CourseVo courseVo = new CourseVo();
            BeanUtils.copyProperties(course, courseVo);
            if (course.getCourseType() != null) {
                courseVo.setCourseTypeDesc(DirectionEnum.translate(String.valueOf(course.getCourseType())));
            }
            User user = userService.findById(course.getUploadUid());
            if (user != null) {
                courseVo.setUploadUsername(user.getUsername());
            }
            Integer courseId = courseVo.getCourseId();
            if (first != null && courseId.equals(first.getCourseId())) {
                courseVo.setPopular(true);
            }
            if (second != null && courseId.equals(second.getCourseId())) {
                courseVo.setPopular(true);
            }

            Integer sort = courseVo.getSort();
            int index = sortsList.indexOf(sort);
            Integer lastSort = sortsList.get(Math.max(0, index - 1));
            Course lastCourse = learnOrderCourses.stream().filter(item -> item.getSort().equals(lastSort)).findFirst().orElse(null);
            if (lastCourse == null) {
                courseVo.setCanPlay(true);
            } else {
                List<CourseTime> courseTime = allCourseMap.get(lastCourse.getCourseId());
                int lastCurrent = courseTime != null && !courseTime.isEmpty() ? courseTime.stream().mapToInt(CourseTime::getTime).sum() : 0;
                String lastPercent = getPercent(lastCurrent, lastCourse.getTotalPlayTime());
                String lastReplace = lastPercent.replace("%", "");
                boolean canPlay = !StringUtils.isEmpty(lastReplace) && Double.parseDouble(lastReplace) >= 80;
                courseVo.setCanPlay(canPlay);
            }

            List<CourseTime> currentCourseTime = allCourseMap.get(courseVo.getCourseId());
            int current = currentCourseTime != null && !currentCourseTime.isEmpty() ? currentCourseTime.stream().mapToInt(CourseTime::getTime).sum() : 0;
            String currentPercent = getPercent(current, courseVo.getTotalPlayTime());
            courseVo.setProcess(currentPercent);

            res.add(courseVo);
        }
        res = res.stream().sorted(Comparator.comparing(CourseVo::getSort)).collect(Collectors.toList());
        Integer firstLearn = sortsList.get(0);
        res = res.stream().peek(item -> {
            if (item.getSort().equals(firstLearn)) {
                item.setCanPlay(true);
            }
        }).collect(Collectors.toList());
        return res;
    }

    private String getPercent(int current, int total) {
        NumberFormat fmt = NumberFormat.getPercentInstance();
        fmt.setMaximumFractionDigits(2);  //保留两位小数
        total = total == 0 ? 1 : total;   //三目运算符避免除0异常
        return fmt.format((float) current / total);
    }

    private List<CourseVo> translateOtherCourseType(List<Course> list) {
        Map<Integer, List<Course>> map = list.stream().collect(Collectors.groupingBy(Course::getCourseType));
        List<CourseVo> res = new ArrayList<>();
        Set<Integer> types = map.keySet();
        List<Course> popularCourses = new ArrayList<>();
        for (Integer type : types) {
            List<Course> courses = map.get(type);
            if (!courses.isEmpty()) {
                courses = courses.stream().sorted(Comparator.comparing(Course::getHitCount).reversed()).collect(Collectors.toList());
                popularCourses.add(courses.get(0));
            }
        }
        for (Course e : popularCourses) {
            CourseVo vo = new CourseVo();
            BeanUtils.copyProperties(e, vo);
            if (e.getCourseType() != null) {
                vo.setCourseTypeDesc(DirectionEnum.translate(String.valueOf(e.getCourseType())));
            }
            User user = userService.findById(e.getUploadUid());
            if (user != null) {
                vo.setUploadUsername(user.getUsername());
            }
            vo.setPopular(true);
            res.add(vo);
        }
        return res;
    }
}
