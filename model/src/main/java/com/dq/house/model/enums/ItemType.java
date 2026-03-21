package com.dq.house.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;


public enum ItemType implements BaseEnum {

    APARTMENT(1, "公寓"),

    ROOM(2, "房间");


    @EnumValue  // MyBatis-Plus注解，将属性code与枚举常量相互映射（APARTMENT(ROOM)<->code）
    @JsonValue  // Jackson注解，将属性code与枚举常量相互映射（APARTMENT(ROOM)<->code）
    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }


    @Override
    public String getName() {
        return name;
    }

    ItemType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

}
