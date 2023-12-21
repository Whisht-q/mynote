package com.mynote.base.common.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 博客分类关联表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Getter
@Setter
@TableName("blog_union_category")
@ApiModel(value = "UnionCategory对象", description = "博客分类关联表")
public class UnionCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("博客分类表id")
    @TableField("id")
    private String id;

    @ApiModelProperty("博客id")
    @TableField(value = "blog_id")
    private String blogId;

    @ApiModelProperty("分类id")
    @TableField(value = "category_id")
    private String categoryId;
}
