package com.mynote.blog.mapper;

import com.mynote.base.common.blog.entity.Status;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 博客状态表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Mapper
public interface StatusMapper extends BaseMapper<Status> {

}
