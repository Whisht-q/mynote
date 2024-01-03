package com.mynote.base.common.note.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/18 15:11
 * @description
 */
@Data
@ApiModel(value = "笔记内容DTO")
public class ContentDto {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    private String categoryId;

    private String status;
}
