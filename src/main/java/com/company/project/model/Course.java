package com.company.project.model;

import javax.persistence.*;

public class Course {
    /**
     * 课程ID
     */
    @Id
    @Column(name = "course_id")
    private Integer courseId;

    /**
     * 课程名称
     */
    @Column(name = "course_name")
    private String courseName;

    /**
     * 课程分类，对应三种方向
     */
    @Column(name = "course_type")
    private Integer courseType;

    /**
     * 课程介绍
     */
    @Column(name = "course_intro")
    private String courseIntro;

    @Column(name = "course_pic")
    private String coursePic;

    @Column(name = "course_video")
    private String courseVideo;

    /**
     * 点击量
     */
    @Column(name = "hit_count")
    private Integer hitCount;

    /**
     * 上传人
     */
    @Column(name = "upload_uid")
    private Integer uploadUid;

    /**
     * 课程排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 总播放时长
     */
    @Column(name = "total_play_time")
    private Integer totalPlayTime;


    /**
     * 获取课程ID
     *
     * @return course_id - 课程ID
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * 设置课程ID
     *
     * @param courseId 课程ID
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * 获取课程名称
     *
     * @return course_name - 课程名称
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * 设置课程名称
     *
     * @param courseName 课程名称
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * 获取课程分类，对应三种方向
     *
     * @return course_type - 课程分类，对应三种方向
     */
    public Integer getCourseType() {
        return courseType;
    }

    /**
     * 设置课程分类，对应三种方向
     *
     * @param courseType 课程分类，对应三种方向
     */
    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    /**
     * 获取课程介绍
     *
     * @return course_intro - 课程介绍
     */
    public String getCourseIntro() {
        return courseIntro;
    }

    /**
     * 设置课程介绍
     *
     * @param courseIntro 课程介绍
     */
    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    /**
     * @return course_pic
     */
    public String getCoursePic() {
        return coursePic;
    }

    /**
     * @param coursePic
     */
    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    /**
     * @return course_video
     */
    public String getCourseVideo() {
        return courseVideo;
    }

    /**
     * @param courseVideo
     */
    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    /**
     * 获取点击量
     *
     * @return hit_count - 点击量
     */
    public Integer getHitCount() {
        return hitCount;
    }

    /**
     * 设置点击量
     *
     * @param hitCount 点击量
     */
    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
    }

    /**
     * 获取上传人
     *
     * @return upload_uid - 上传人
     */
    public Integer getUploadUid() {
        return uploadUid;
    }

    /**
     * 设置上传人
     *
     * @param uploadUid 上传人
     */
    public void setUploadUid(Integer uploadUid) {
        this.uploadUid = uploadUid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTotalPlayTime() {
        return totalPlayTime;
    }

    public void setTotalPlayTime(Integer totalPlayTime) {
        this.totalPlayTime = totalPlayTime;
    }
}
