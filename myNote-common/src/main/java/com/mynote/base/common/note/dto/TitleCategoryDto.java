package com.mynote.base.common.note.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhishubin
 * @date 2024/1/2 11:52
 * @description
 */
@Data
public class TitleCategoryDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类主键id")
    private String id;

    @ApiModelProperty("父级分类id")
    private String parentId;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("父级分类名称")
    private String parentName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updatedTime;


}
