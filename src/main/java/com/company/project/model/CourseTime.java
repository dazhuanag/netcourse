package com.company.project.model;

import javax.persistence.*;

@Table(name = "course_time")
public class CourseTime {
    @Id
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "course_id")
    private Integer courseId;

    private Integer uid;

    /**
     * 播放时长，单位秒
     */
    private Integer time;

    /**
     * @return record_id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * @return course_id
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取播放时长，单位秒
     *
     * @return time - 播放时长，单位秒
     */
    public Integer getTime() {
        return time;
    }

    /**
     * 设置播放时长，单位秒
     *
     * @param time 播放时长，单位秒
     */
    public void setTime(Integer time) {
        this.time = time;
    }
}