package com.mynote.base.common.blog.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/22 10:31
 * @description
 */

@Data
@ApiModel(value = "博客内容Dto")
public class BlogContentDto {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("分类id")
    private String categoryId;

    @ApiModelProperty("状态")
    private byte status;

    public BlogContentDto(){
        this.status = 0;
    }
}
