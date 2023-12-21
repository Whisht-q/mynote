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
 * 系统角色表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "Role对象", description = "系统角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("角色名")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("创建人")
    @TableField("created_user")
    private String createdUser;
}
