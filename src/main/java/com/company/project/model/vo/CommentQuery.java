package com.company.project.model.vo;

import com.company.project.core.CommonPageQuery;
import lombok.Data;

@Data
public class CommentQuery extends CommonPageQuery {

    private String courseId;

}
