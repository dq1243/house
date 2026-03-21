package com.dq.house.web.admin.custom.converter;

import com.dq.house.model.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @author DQ1243
 */

// ConverterFactory可以将一个父类转换为多个目标子类型
// SpringMvc将web传入的String转换成BaseEnum的具体枚举类的枚举值（ItemType.APARTMENT）
@Component
public class StringToBaseEnumConverter implements ConverterFactory<String, BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
            return (code) -> {
            // targetType.getEnumConstants() 获取枚举类的所有枚举值
            for (T type : targetType.getEnumConstants()) {
                if (type.getCode().equals(Integer.valueOf(code))) {
                    return type;
                }
            }
            throw new IllegalArgumentException("code：" + code + "非法");
        };
    }
}
