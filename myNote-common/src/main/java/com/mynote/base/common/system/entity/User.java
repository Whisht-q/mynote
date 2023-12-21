package com.mynote.base.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty("头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("身份 : 11001 普通用户 , 11002 管理员")
    @TableField("role")
    private String role;

    @ApiModelProperty("状态 : 0 冻结 , 1 正常")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty("用户创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}
