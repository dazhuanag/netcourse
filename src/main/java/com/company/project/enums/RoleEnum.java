package com.company.project.enums;

import com.company.project.core.DictEnum;

import java.util.Arrays;

public enum RoleEnum implements DictEnum {
    UNKNOWN("0", "未知"),

    ADMIN("1", "管理员"),

    TEA("2", "老师"),
    STU("3", "学生"),

    ;

    private String key;

    private String value;

    RoleEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static String translate(String key) {
        return Arrays.asList(RoleEnum.values()).stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst().orElse(UNKNOWN)
                .getValue();
    }

}
