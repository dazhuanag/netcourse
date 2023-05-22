package com.company.project.model.vo;

import com.company.project.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentVo extends Comment {

    private String username;

    private String timeDesc;

    private List<CommentVo> childCommentList;

}
