package com.company.project.model.vo;

import com.company.project.core.CommonPageQuery;
import lombok.Data;

@Data
public class UserQuery extends CommonPageQuery {

    private Integer uid;

    private String username;

    private Integer role;

}
