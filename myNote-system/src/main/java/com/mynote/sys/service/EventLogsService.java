package com.mynote.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.system.entity.EventLogs;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mynote.base.common.system.vo.EventLogsVo;

/**
 * <p>
 * 系统操作日志表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
public interface EventLogsService extends IService<EventLogs> {

    IPage<EventLogsVo> selectLogs(Page<EventLogsVo> page,String userId, String eventTypeId);

}
