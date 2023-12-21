package com.mynote.base.common.system.entity;

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
 * 操作事件类型表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Getter
@Setter
@TableName("sys_event_type")
@ApiModel(value = "EventType对象", description = "操作事件类型表")
public class EventType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("操作事件类型id")
    @TableId("id")
    private String id;

    @ApiModelProperty("操作事件类型")
    @TableField("event_type")
    private String eventType;
}
