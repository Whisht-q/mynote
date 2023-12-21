package com.mynote.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mynote.base.common.Result;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.base.common.system.entity.EventType;
import com.mynote.sys.service.EventTypeService;
import com.mynote.base.common.system.vo.EventTypeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 操作事件类型表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 02:13:47
 */
@Api(value = "操作事件类型", tags = "操作事件类型")
@RestController
@RequestMapping("/sys/eventType")
public class EventTypeController {

    @Resource
    private EventTypeService eventTypeService;

    /**
     * 添加事件类型
     *
     * @param id
     * @param eventType
     * @return
     */
    @ApiOperation("添加事件类型")
    @PostMapping("/addType")
    public Result<?> addType(@RequestParam("id") String id, @RequestParam("eventType") String eventType) {
        EventType typeId = eventTypeService.getOne(new LambdaQueryWrapper<EventType>().eq(EventType::getId, id));
        if (!CommonUtil.isEmpty(typeId)) {
            throw new NoteException(ResultCodeEnum.EVENT_ID_REPEAT);
        }
        EventType type = eventTypeService.getOne(new LambdaQueryWrapper<EventType>().eq(EventType::getEventType, eventType));
        if (!CommonUtil.isEmpty(type)) {
            throw new NoteException(ResultCodeEnum.EVENT_TYPE_REPEAT);
        }
        EventType eventTypeEntity = new EventType();
        eventTypeEntity.setId(id);
        eventTypeEntity.setEventType(eventType);
        eventTypeService.save(eventTypeEntity);

        return Result.success();
    }

    /**
     * 删除事件类型
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除事件类型")
    @PostMapping("/deleteType/{id}")
    public Result<?> deleteType(@PathVariable("id") String id) {
        EventType eventType = eventTypeService.getById(id);
        if (CommonUtil.isEmpty(eventType)) {
            throw new NoteException(ResultCodeEnum.EVENT_TYPE_NONE);
        }
        eventTypeService.removeById(id);

        return Result.success();
    }

    /**
     * 查询事件类型
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询事件类型")
    @PostMapping("/get/{id}")
    public Result<EventTypeVo> getEvent(@PathVariable("id") String id) {
        EventType eventType = eventTypeService.getOne(new LambdaQueryWrapper<EventType>().eq(EventType::getId, id));
        EventTypeVo eventTypeVo = new EventTypeVo();
        BeanUtils.copyProperties(eventType, eventTypeVo);
        return Result.success(eventTypeVo);
    }

    /**
     * 事件类型列表
     *
     * @return
     */
    @ApiOperation(value = "事件类型列表")
    @GetMapping("/list")
    public Result<List<EventTypeVo>> list() {
        List<EventType> list = eventTypeService.list();
        List<EventTypeVo> res = list.stream().map(eventType -> {
            EventTypeVo eventTypeVo = new EventTypeVo();
            BeanUtils.copyProperties(eventType, eventTypeVo);
            return eventTypeVo;
        }).collect(Collectors.toList());
        return Result.success(res);
    }

}
