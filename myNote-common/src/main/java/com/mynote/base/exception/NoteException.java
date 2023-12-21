package com.mynote.base.exception;

import com.mynote.base.constant.Enum.ResultCodeEnum;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/13 15:38
 * @description 自定义异常处理类
 */
@Data
public class NoteException extends RuntimeException {
    //异常状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param message 信息
     * @param code    状态码
     */
    public NoteException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum 枚举类对象
     */
    public NoteException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "NoteException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
