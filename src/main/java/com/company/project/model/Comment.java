package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Comment {
    /**
     * 评论ID
     */
    @Id
    @Column(name = "comment_id")
    private Integer commentId;

    private Integer uid;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 父评论ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "comment_time")
    private Date commentTime;

    /**
     * 获取评论ID
     *
     * @return comment_id - 评论ID
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * 设置评论ID
     *
     * @param commentId 评论ID
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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
     * @return teacher_id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取父评论ID
     *
     * @return parent_id - 父评论ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父评论ID
     *
     * @param parentId 父评论ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return comment_time
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * @param commentTime
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }
}