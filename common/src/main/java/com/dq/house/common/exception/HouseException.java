package com.dq.house.common.exception;

import com.dq.house.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author DQ1243
 */

// 自定义异常类，继承RuntimeException
@Data
public class HouseException extends RuntimeException {

    private Integer code;

    public HouseException (Integer code, String message) {
        super(message);
        this.code = code;
    }

    public HouseException (ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
