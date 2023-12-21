package com.mynote.base.common.note.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/21 14:04
 * @description
 */
@Data
@ApiModel(value = "笔记修改DTO")
public class ContentUpdateDto {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("状态")
    private byte status;
}
