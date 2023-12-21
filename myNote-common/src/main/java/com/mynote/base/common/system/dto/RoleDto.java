package com.mynote.base.common.system.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/14 17:13
 * @description
 */
@Data
@ApiModel(value = "角色DTO")
public class RoleDto {

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("创建人")
    private String createdUser;
}
