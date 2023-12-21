package com.mynote.base.common.note.entity;

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
 * 笔记评论回复表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Getter
@Setter
@TableName("notes_reply")
@ApiModel(value = "Reply对象", description = "笔记评论回复表")
public class Reply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论回复表主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("笔记id")
    @TableField("note_id")
    private String noteId;

    @ApiModelProperty("消息发出方id")
    @TableField("user_src_id")
    private String userSrcId;

    @ApiModelProperty("消息接受方id")
    @TableField("user_dest_id")
    private String userDestId;

    @ApiModelProperty("回复时间")
    @TableField("created_time")
    private LocalDateTime createdTime;
}
