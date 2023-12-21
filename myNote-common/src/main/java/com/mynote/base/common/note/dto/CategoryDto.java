package com.mynote.base.common.note.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/18 14:52
 * @description
 */
@Data
@ApiModel(value = "笔记分类DTO")
public class CategoryDto {

    @ApiModelProperty("分类主键id")
    private String id;

    @ApiModelProperty("分类名称")
    private String name;
}
