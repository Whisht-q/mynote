package com.mynote.base.common.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/14 16:04
 * @description
 */
@Data
@ApiModel(value = "用户DTO")
public class UserDto {

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("盐")
    private String salt;

    @ApiModelProperty("头像")
    private String avatar;
}
