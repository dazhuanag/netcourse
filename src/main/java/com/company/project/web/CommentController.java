package com.company.project.web;

import com.company.project.core.CommonPageQuery;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Comment;
import com.company.project.model.User;
import com.company.project.model.vo.CommentQuery;
import com.company.project.model.vo.CommentVo;
import com.company.project.service.CommentService;
import com.company.project.service.UserService;
import com.company.project.utils.FormatUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评论服务对外接口
 * Created by myself on 2023/04/10.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        comment.setCommentTime(new Date());
        commentService.save(comment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Comment comment) {
        Comment byId = commentService.findById(comment.getCommentId());
        commentService.deleteById(comment.getCommentId());
        // 删除所有父ID为该记录的评论
        Condition condition = new Condition(Comment.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("parentId", comment.getCommentId());
        List<Comment> byCondition = commentService.findByCondition(condition);
        if (byCondition.size() > 0) {
            for (Comment comment1 : byCondition) {
                commentService.deleteById(comment1.getCommentId());
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Comment comment) {
        commentService.update(comment);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Integer id) {
        Comment comment = commentService.findById(id);
        return ResultGenerator.genSuccessResult(comment);
    }

    @PostMapping("/list")
    public Result list(@RequestBody CommentQuery query) {
        PageHelper.startPage(query.getPage(), query.getSize());
        Condition condition = new Condition(Comment.class);
        Example.Criteria criteria = condition.createCriteria();
        if (query.getCourseId() != null) {
            criteria.andEqualTo("courseId", query.getCourseId());
        }
        List<Comment> list = commentService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(translate(list));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    private List<CommentVo> translate(List<Comment> list) {
        List<CommentVo> res = new ArrayList<>();
        for (Comment e : list) {
            // 第一级评论
            if (e.getParentId() == 0) {
                CommentVo vo = new CommentVo();
                BeanUtils.copyProperties(e, vo);
                vo.setUsername(getUsernameById(e.getUid()));
                vo.setTimeDesc(FormatUtils.formatDateTime(e.getCommentTime()));
                // 找到所有的子评论
                List<CommentVo> childList = new ArrayList<>();
                for (Comment child : list) {
                    if (child.getParentId().equals(vo.getCommentId())) {
                        CommentVo childVo = new CommentVo();
                        BeanUtils.copyProperties(child, childVo);
                        childVo.setUsername(getUsernameById(child.getUid()));
                        childVo.setTimeDesc(FormatUtils.formatDateTime(child.getCommentTime()));
                        childList.add(childVo);
                    }
                }
                vo.setChildCommentList(childList);
                res.add(vo);
            }
        }
        return res;
    }

    private String getUsernameById(Integer uid) {
        User user = userService.findById(uid);
        if (user != null) {
            return user.getUsername();
        }
        return "";
    }
}
