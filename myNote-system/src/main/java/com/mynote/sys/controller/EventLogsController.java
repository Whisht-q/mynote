package com.mynote.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.Result;
import com.mynote.base.common.system.entity.EventLogs;
import com.mynote.base.common.system.entity.EventType;
import com.mynote.base.common.system.entity.User;
import com.mynote.sys.service.EventLogsService;
import com.mynote.sys.service.EventTypeService;
import com.mynote.sys.service.UserService;
import com.mynote.sys.utils.IpUtil;
import com.mynote.base.common.system.vo.EventLogsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 系统操作日志表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 02:13:47
 */
@Api(value = "系统操作日志", tags = "系统操作日志")
@RestController
@RequestMapping("/sys/eventLogs")
public class EventLogsController {

    @Resource
    private UserService userService;

    @Resource
    private EventTypeService eventTypeService;

    @Resource
    private EventLogsService eventLogsService;

    /**
     * 添加日志
     * @param userId
     * @param eventTypeId
     * @param req
     * @return
     */
    @ApiOperation(value = "添加日志")
    @PostMapping("/addLog")
    public Result<?> addLog(@RequestParam("userId") String userId, @RequestParam("eventTypeId") String eventTypeId
            , HttpServletRequest req) {
        String ipAddress = IpUtil.getClientIp(req);
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        EventType eventType = eventTypeService.getOne(new LambdaQueryWrapper<EventType>().eq(EventType::getId, eventTypeId));
        EventLogs eventLogs = new EventLogs();
        eventLogs.setUserId(userId);
        eventLogs.setEventTypeId(eventTypeId);
        eventLogs.setIpAddress(ipAddress);
        eventLogs.setEventDescription(user.getUsername() + "执行了" + eventType.getEventType() + "操作");
        eventLogsService.save(eventLogs);

        return Result.success();
    }



    /**
     * 日志列表
     * @param userId
     * @param eventTypeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "日志列表")
    @GetMapping("/list")
    public Result<IPage<EventLogsVo>> list(@RequestParam(value = "userId", required = false) String userId,
                                           @RequestParam(value = "eventId", required = false) String eventTypeId,
                                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<EventLogsVo> page = new Page<>(pageNo, pageSize);
        IPage<EventLogsVo> res = eventLogsService.selectLogs(page, userId, eventTypeId);

        return Result.success(res);
    }

}
