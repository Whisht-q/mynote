package com.mynote.base.common.note.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhishubin
 * @date 2023/12/18 14:57
 * @description
 */
@Data
@ApiModel(value = "笔记分类Vo")
public class CategoryVo {

    @ApiModelProperty("分类主键id")
    private String id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("子级分类")
    private List<CategoryVo> children;
}
