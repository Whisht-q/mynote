package com.mynote.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.system.entity.EventLogs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mynote.base.common.system.vo.EventLogsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统操作日志表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Mapper
public interface EventLogsMapper extends BaseMapper<EventLogs> {

    IPage<EventLogsVo> selectLogs(Page<EventLogsVo> page, @Param("userId") String userId,
                                  @Param("eventTypeId") String eventTypeId);

}
