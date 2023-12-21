package com.mynote.sys.service.impl;

import com.mynote.base.common.system.entity.EventType;
import com.mynote.sys.mapper.EventTypeMapper;
import com.mynote.sys.service.EventTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作事件类型表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Service
public class EventTypeServiceImpl extends ServiceImpl<EventTypeMapper, EventType> implements EventTypeService {

}
