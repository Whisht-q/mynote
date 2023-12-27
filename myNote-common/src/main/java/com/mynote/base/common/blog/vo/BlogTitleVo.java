package com.mynote.base.common.blog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/22 11:45
 * @description
 */
@Data
@ApiModel("公开博客列表Vo")
public class BlogTitleVo {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("标题")
    private String title;
}
