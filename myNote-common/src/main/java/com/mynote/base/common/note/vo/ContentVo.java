package com.mynote.base.common.note.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/18 15:18
 * @description
 */
@Data
@ApiModel(value = "笔记内容Vo")
public class ContentVo {

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;
}
