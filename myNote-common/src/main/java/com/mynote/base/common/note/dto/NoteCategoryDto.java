package com.mynote.base.common.note.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/20 14:14
 * @description
 */
@Data
@AllArgsConstructor
@ApiModel(value = "笔记分类关联DTO")
public class NoteCategoryDto {

    @ApiModelProperty("笔记id")
    private String noteId;

    @ApiModelProperty("分类id")
    private String categoryId;
}
