package com.mynote.base.common.note.entity;

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
 * 笔记状态表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Getter
@Setter
@TableName("notes_note_status")
@ApiModel(value = "NoteStatus对象", description = "笔记状态表")
public class NoteStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("笔记状态表主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("笔记id")
    @TableField("note_id")
    private String noteId;

    @ApiModelProperty("是否公开: 0 公开 , 1 私有")
    @TableField("status")
    private Boolean status;
}
