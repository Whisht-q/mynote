package com.mynote.base.common.note.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 笔记分类表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Getter
@Setter
@TableName("notes_category")
@ApiModel(value = "Category对象", description = "笔记分类表")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("父级分类id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty("分类名称")
    @TableField("name")
    private String name;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("修改时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @ApiModelProperty("逻辑删除: 0 未删除, 1 已删除")
    @TableField("is_deleted")
    private Boolean isDeleted;
}
