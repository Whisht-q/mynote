package com.mynote.base.common;

import com.mynote.base.constant.Enum.ResultCodeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/13 15:18
 * @description 统一返回对象
 */
@Data
public class Result<T> {
    @ApiModelProperty(name = "状态码")
    private Integer code;

    @ApiModelProperty(name = "信息")
    private String message;

    @ApiModelProperty(name = "数据")
    private T data;

    private Result() {
    }

    /**
     * 设置数据,返回对象
     *
     * @param data           数据
     * @param resultCodeEnum 状态码
     * @param <T>            泛型
     * @return 返回对象
     */
    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        //创建Resullt对象，设置值，返回对象
        Result<T> result = new Result<>();
        //判断返回结果中是否需要数据
        if (data != null) {
            //设置数据到result对象
            result.setData(data);
        }
        //设置其他值
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        //返回设置值之后的对象
        return result;
    }

    public static <T> Result<T> build(T data, Integer code, String message) {
        //创建Resullt对象，设置值，返回对象
        Result<T> result = new Result<>();
        //判断返回结果中是否需要数据
        if (data != null) {
            //设置数据到result对象
            result.setData(data);
        }
        //设置其他值
        result.setCode(code);
        result.setMessage(message);
        //返回设置值之后的对象
        return result;
    }

    /**
     * 成功的返回
     *
     * @param data 返回值
     * @param <T>  泛型
     * @return 返回对象
     */
    public static <T> Result<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> Result<T> success() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    /**
     * 失败的返回
     *
     * @param data 返回值
     * @param <T>  泛型
     * @return 返回对象
     */
    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    public static <T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }
}
