package com.company.project.web;
import com.company.project.core.CommonPageQuery;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.enums.DirectionEnum;
import com.company.project.enums.RoleEnum;
import com.company.project.enums.SexEnum;
import com.company.project.model.User;
import com.company.project.model.vo.UserQuery;
import com.company.project.model.vo.UserVo;
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
 * 用户服务对外接口
* Created by myself on 2023/04/10.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User loginUser = userService.findBy("username", user.getUsername());
        if (user.getRole() == null || user.getRole().equals("")) {
            // 默认
            user.setRole(1);
        }
        if (loginUser!=null && user.getUsername().equals(loginUser.getUsername())
                && user.getPassword().equals(loginUser.getPassword())
                && user.getRole().equals(loginUser.getRole())) {
            return ResultGenerator.genSuccessResult(loginUser);
        }
        return ResultGenerator.genFailResult("登录失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody User user) {
        userService.deleteById(user.getUid());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody User u) {
        User user = userService.findById(u.getUid());
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestBody UserQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        if (query.getUid() != null) {
            criteria.andEqualTo("uid", query.getUid());
        }
        if (query.getRole() != null) {
            criteria.andEqualTo("role", query.getRole());
        }
        if (StringUtils.isNotBlank(query.getUsername())) {
            criteria.andLike("username", "%" + query.getUsername() + "%");
        }
        List<User> list = userService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(translate(list));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    private List<UserVo> translate(List<User> list) {
        List<UserVo> res = new ArrayList<>();
        for (User e : list) {
            UserVo vo = new UserVo();
            BeanUtils.copyProperties(e, vo);
            if (e.getSex() != null) {
                vo.setSexDesc(SexEnum.translate(String.valueOf(e.getSex())));
            }
            if (e.getDirection() != null) {
                vo.setDirectionDesc(DirectionEnum.translate(String.valueOf(e.getDirection())));
            }
            if (e.getRole() != null) {
                vo.setRoleDesc(RoleEnum.translate(String.valueOf(e.getRole())));
            }
            res.add(vo);
        }
        return res;
    }

}
