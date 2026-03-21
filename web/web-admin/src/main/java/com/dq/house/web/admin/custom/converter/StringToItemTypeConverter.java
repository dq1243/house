//package com.dq.house.web.admin.custom.converter;
//
//import com.dq.house.model.enums.ItemType;
//import lombok.NonNull;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
///**
// * @author DQ1243
// */
//@Component
//public class StringToItemTypeConverter implements Converter<String, ItemType> {
//
//    @Override
//    public ItemType convert(@NonNull String code) {
//        for (ItemType type : ItemType.values()) {
//            if (type.getCode().equals(Integer.valueOf(code))) {
//                return type;
//            }
//        }
//        throw new IllegalArgumentException("code：" + code + "非法");
//    }
//}