package com.mynote.base.common.note.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/18 15:07
 * @description
 */
@Data
@ApiModel(value = "笔记评论DTO")
public class CommentDto {
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("笔记id")
    private String noteId;

    @ApiModelProperty("评论内容")
    private String content;
}
