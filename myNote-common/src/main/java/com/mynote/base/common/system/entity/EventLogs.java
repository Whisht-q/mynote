package com.mynote.base.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 系统操作日志表
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Getter
@Setter
@TableName("sys_event_logs")
@ApiModel(value = "EventLogs对象", description = "系统操作日志表")
public class EventLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("系统日志表主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("ip地址")
    @TableField("ip_address")
    private String ipAddress;

    @ApiModelProperty("操作事件类型id")
    @TableField("event_type_id")
    private String eventTypeId;

    @ApiModelProperty("事件描述信息")
    @TableField("event_description")
    private String eventDescription;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;
}
