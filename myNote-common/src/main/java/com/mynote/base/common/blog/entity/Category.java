package com.mynote.base.common.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 博客分类表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:52
 */
@Getter
@Setter
@TableName("blog_category")
@ApiModel(value = "Category对象", description = "博客分类表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("分类名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("修改时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @ApiModelProperty("逻辑删除: 0 未删除, 1 已删除")
    @TableField("is_deleted")
    private Boolean isDeleted;
}
