package com.mynote.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.system.entity.EventLogs;
import com.mynote.sys.mapper.EventLogsMapper;
import com.mynote.sys.service.EventLogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.system.vo.EventLogsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Service
public class EventLogsServiceImpl extends ServiceImpl<EventLogsMapper, EventLogs> implements EventLogsService {

    @Autowired
    private EventLogsMapper eventLogsMapper;
    @Override
    public IPage<EventLogsVo> selectLogs(Page<EventLogsVo> page,String userId, String eventTypeId) {
        return eventLogsMapper.selectLogs(page,userId,eventTypeId);
    }
}
