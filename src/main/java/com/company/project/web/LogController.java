package com.company.project.web;
import com.company.project.core.CommonPageQuery;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.enums.DirectionEnum;
import com.company.project.model.Course;
import com.company.project.model.Log;
import com.company.project.model.User;
import com.company.project.model.vo.CourseVo;
import com.company.project.model.vo.LogQuery;
import com.company.project.model.vo.LogVo;
import com.company.project.service.LogService;
import com.company.project.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志服务对外接口
* Created by myself on 2023/04/10.
*/
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody Log log) {
        logService.save(log);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Integer id) {
        logService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Log log) {
        logService.update(log);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Integer id) {
        Log log = logService.findById(id);
        return ResultGenerator.genSuccessResult(log);
    }

    @PostMapping("/list")
    public Result list(@RequestBody LogQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        Condition condition = new Condition(Log.class);
        Example.Criteria criteria = condition.createCriteria();
        if (StringUtils.isNotBlank(query.getContent())) {
            criteria.andLike("content", "%" + query.getContent() + "%");
        }
        List<Log> list = logService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(translate(list));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    private List<LogVo> translate(List<Log> list) {
        List<LogVo> res = new ArrayList<>();
        for (Log e : list) {
            LogVo vo = new LogVo();
            BeanUtils.copyProperties(e, vo);
            User user = userService.findById(e.getUid());
            if (user != null) {
                vo.setUsername(user.getUsername());
            }
            res.add(vo);
        }
        return res;
    }


}
