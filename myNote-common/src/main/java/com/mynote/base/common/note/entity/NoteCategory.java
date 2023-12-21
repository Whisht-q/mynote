package com.mynote.base.common.note.entity;

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
 * 笔记分类关联表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Getter
@Setter
@TableName("notes_note_category")
@ApiModel(value = "NoteCategory对象", description = "笔记分类关联表")
public class NoteCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("笔记分类表id")
    @TableField("id")
    private String id;

    @ApiModelProperty("笔记id")
    @TableField(value = "note_id")
    private String noteId;

    @ApiModelProperty("分类id")
    @TableField(value = "category_id")
    private String categoryId;
}
