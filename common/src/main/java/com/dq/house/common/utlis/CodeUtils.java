package com.dq.house.common.utlis;

import java.util.Random;

/**
 * @author DQ1243
 */

// 验证码工具类
public class CodeUtils {

    // 生成随机验证码
    public static String getCode(Integer length) {
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
