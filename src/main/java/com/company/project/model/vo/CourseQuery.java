package com.company.project.model.vo;

import com.company.project.core.CommonPageQuery;
import lombok.Data;

@Data
public class CourseQuery extends CommonPageQuery {

    private Integer uid;

    private String courseName;

    private Integer courseType;

}
