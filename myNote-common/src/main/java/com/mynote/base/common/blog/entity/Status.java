package com.mynote.base.common.blog.entity;

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
 * 博客状态表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Getter
@Setter
@TableName("blog_status")
@ApiModel(value = "Status对象", description = "博客状态表")
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("博客状态表主键id")
    @TableId("id")
    private String id;

    @ApiModelProperty("博客id")
    @TableField("blog_id")
    private String blogId;

    @ApiModelProperty("是否公开: 0 公开 , 1 私有")
    @TableField("status")
    private byte status;
}
