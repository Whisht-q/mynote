package com.mynote.base.common.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/18 11:09
 * @description 用户登录Dto
 */
@Data
@ApiModel(value = "用户登录DTO")
public class LoginDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("key")
    private String key;
}
