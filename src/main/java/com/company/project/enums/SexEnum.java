package com.company.project.enums;

import com.company.project.core.DictEnum;

import java.util.Arrays;

public enum SexEnum implements DictEnum {
    UNKNOWN("0", "未知"),

    MAN("1", "男"),

    WOMAN("2", "女"),

    ;

    private String key;

    private String value;

    SexEnum(String key, String value) {
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
        return Arrays.asList(SexEnum.values()).stream()
                .filter(e -> e.getKey().equals(key))
                .findFirst().orElse(UNKNOWN)
                .getValue();
    }

}
