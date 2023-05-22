package com.company.project.service.impl;

import com.company.project.dao.CommentMapper;
import com.company.project.model.Comment;
import com.company.project.service.CommentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 评论服务实现类
 * Created by myself on 2023/04/10.
 */
@Service
@Transactional
public class CommentServiceImpl extends AbstractService<Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

}
