package com.mynote.base.common.note.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/20 14:31
 * @description
 */
@Data
@AllArgsConstructor
public class NoteStatusDto {

    @ApiModelProperty("笔记id")
    private String noteId;

    @ApiModelProperty("是否公开: 0 公开 , 1 私有")
    private Boolean status;
}
