package com.company.project.model.vo;

import com.company.project.model.User;
import lombok.Data;

@Data
public class UserVo extends User {

    private String directionDesc;

    private String sexDesc;

    private String roleDesc;

}
