package com.mynote.base.common.system.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhishubin
 * @date 2023/12/14 17:51
 * @description
 */
@Data
@ApiModel(value = "事件日志Vo")
public class EventLogsVo {
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("ip地址")
    private String ipAddress;

    @ApiModelProperty("操作事件类型id")
    private String eventTypeId;

    @ApiModelProperty("操作事件类型")
    private String eventType;

    @ApiModelProperty("事件描述信息")
    private String eventDescription;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;
}
