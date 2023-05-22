//package com.company.project.web;
//import com.company.project.core.CommonPageQuery;
//import com.company.project.core.Result;
//import com.company.project.core.ResultGenerator;
//import com.company.project.enums.RoleEnum;
//import com.company.project.model.User;
//import com.company.project.model.vo.UserQuery;
//import com.company.project.model.vo.UserVo;
//import com.company.project.service.UserService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.web.bind.annotation.*;
//import tk.mybatis.mapper.entity.Condition;
//import tk.mybatis.mapper.entity.Example;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
//* Created by XXOO on 2023/04/12.
//*/
//@RestController
//@RequestMapping("/user")
//public class UserController {
//    @Resource
//    private UserService userService;
//
//    @PostMapping("/login")
//    public Result login(@RequestBody User user) {
//        User loginUser = userService.findBy("username", user.getUsername());
//        if (user.getRole() == null || user.getRole().equals("")) {
//            // 默认
//            user.setRole(1);
//        }
//        if (user.getUsername().equals(loginUser.getUsername())
//                && user.getPassword().equals(loginUser.getPassword())
//                && user.getRole().equals(loginUser.getRole())) {
//            return ResultGenerator.genSuccessResult(loginUser);
//        }
//        return ResultGenerator.genFailResult("登录失败");
//    }
//
//    @PostMapping("/add")
//    public Result add(@RequestBody User user) {
//        User userByName = userService.findBy("username", user.getUsername());
//        if (userByName != null) {
//            return ResultGenerator.genFailResult("该用户名已存在。");
//        }
//        userService.save(user);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/delete")
//    public Result delete(@RequestBody User user) {
//        userService.deleteById(user.getUid());
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/update")
//    public Result update(@RequestBody User user) {
//        userService.update(user);
//        return ResultGenerator.genSuccessResult();
//    }
//
//    @PostMapping("/detail")
//    public Result detail(@RequestBody Integer id) {
//        User user = userService.findById(id);
//        return ResultGenerator.genSuccessResult(user);
//    }
//
////    @PostMapping("/list")
////    public Result list(@RequestBody CommonPageQuery query) {
////        PageHelper.startPage(query.getPage(), query.getSize());
////        List<User> list = userService.findAll();
////
////        PageInfo pageInfo = new PageInfo(list);
////        return ResultGenerator.genSuccessResult(pageInfo);
////    }
//
//        @PostMapping("/list")
//    public Result list(@RequestBody UserQuery query) {
//        Condition condition = new Condition(User.class);
//        Example.Criteria criteria = condition.createCriteria();
//        if (query.getUid() != null){
//            criteria.andEqualTo("uid", query.getUid());
//        }
//        if (StringUtils.isNotBlank(query.getUsername())){
//            criteria.andLike("username", "%" + query.getUsername() + "%");
//        }
//        if (query.getRole() != null) {
//            criteria.andEqualTo("role", query.getRole());
//        }
//        PageHelper.startPage(query.getPage(), query.getSize());
////        List<User> list = userService.findAll();
//        List<User> list = userService.findByCondition(condition);
//        PageInfo pageInfo = new PageInfo(list);
//        List<UserVo> voList = new ArrayList<>();
//        // 翻译字典
//        list.forEach(user -> {
//            UserVo userVo = new UserVo();
//            BeanUtils.copyProperties(user, userVo);
//            userVo.setRoleDesc(RoleEnum.translate(String.valueOf(userVo.getRole())));
//            voList.add(userVo);
//        });
//        pageInfo.setList(voList);
//        return ResultGenerator.genSuccessResult(pageInfo);
//    }
//}
