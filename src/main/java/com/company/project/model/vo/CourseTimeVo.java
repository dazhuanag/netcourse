package com.company.project.model.vo;

import com.company.project.model.CourseTime;
import lombok.Data;

import java.util.List;

@Data
public class CourseTimeVo extends CourseTime {

    private String username;

    private List<CourseTimeVo> courseTimeVoList;

}
