package com.company.project.enums;

import com.company.project.core.DictEnum;

import java.util.Arrays;

public enum DirectionEnum implements DictEnum {
    UNKNOWN("0", "未知"),

    JAVA("1", "Java"),

    FRONT("2", "前端"),
    DATA("3", "数据分析"),

    ;

    private String key;

    private String value;

    DirectionEnum(String key, String value) {
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
        return Arrays.asList(DirectionEnum.values()).stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst().orElse(UNKNOWN)
                .getValue();
    }

}
