package com.company.project.model;

import javax.persistence.*;

public class User {
    @Id
    private Integer uid;

    /**
     * 角色1管理员2老师3学生
     */
    private Integer role;

    /**
     * 专业方向1Java2前端3数据分析
     */
    private Integer direction;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别1男2女
     */
    private Integer sex;

    /**
     * 电话
     */
    private String tel;

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
     * 获取角色1管理员2老师3学生
     *
     * @return role - 角色1管理员2老师3学生
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置角色1管理员2老师3学生
     *
     * @param role 角色1管理员2老师3学生
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * 获取专业方向1Java2前端3数据分析
     *
     * @return direction - 专业方向1Java2前端3数据分析
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * 设置专业方向1Java2前端3数据分析
     *
     * @param direction 专业方向1Java2前端3数据分析
     */
    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取性别1男2女
     *
     * @return sex - 性别1男2女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别1男2女
     *
     * @param sex 性别1男2女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取电话
     *
     * @return tel - 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话
     *
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
}