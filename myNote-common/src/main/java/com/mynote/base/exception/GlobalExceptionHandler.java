package com.mynote.base.exception;

import com.mynote.base.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhishubin
 * @date 2023/12/13 15:36
 * @description 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 自定义异常类处理方法
     *
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(NoteException.class)
    @ResponseBody
    public Result<?> error(NoteException e) {
        return Result.build(null, e.getCode(), e.getMessage());
    }

    /**
     * 通用异常处理方法
     *
     * @param e 异常
     * @return Result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<?> error(Exception e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }


}
