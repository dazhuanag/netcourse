package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class CourseHit {
    /**
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 课程ID
     */
    @Id
    @Column(name = "course_hit_id")
    private Integer courseHitId;

    /**
     * 课程名称
     */
    @Column(name = "user_id")
    private Integer userId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseHitId() {
        return courseHitId;
    }

    public void setCourseHitId(Integer courseHitId) {
        this.courseHitId = courseHitId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
