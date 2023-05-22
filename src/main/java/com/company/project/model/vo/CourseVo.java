package com.company.project.model.vo;

import com.company.project.model.Course;
import lombok.Data;

@Data
public class CourseVo extends Course {

    private String courseTypeDesc;

    private String uploadUsername;

    /**
     * 是否热门
     */
    private Boolean popular = false;

    /**
     * 观看进度
     */
    private String process;

    /**
     * 能够播放
     */
    private Boolean canPlay;
}
