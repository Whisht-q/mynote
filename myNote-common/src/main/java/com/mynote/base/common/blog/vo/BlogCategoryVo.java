package com.mynote.base.common.blog.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhishubin
 * @date 2023/12/22 11:18
 * @description
 */
@Data
@ApiModel("博客分类Vo")
public class BlogCategoryVo {

    @ApiModelProperty("分类主键id")
    private String id;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("子分类")
    private List<BlogCategoryVo> children;
}
