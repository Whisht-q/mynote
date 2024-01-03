package com.mynote.base.common.note.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhishubin
 * @date 2023/12/29 12:10
 * @description
 */
@Data
@ApiModel(value = "笔记标题列表Vo")
public class ContentTitleVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("分类")
    private String name;

    @ApiModelProperty("修改时间")
    private LocalDateTime updatedTime;
}
