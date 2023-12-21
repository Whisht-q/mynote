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
 * 笔记内容表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Getter
@Setter
@TableName("notes_content")
@ApiModel(value = "Content对象", description = "笔记内容表")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("笔记主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("逻辑删除: 0 未删除,1 已删除")
    @TableField("is_deleted")
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("修改时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
