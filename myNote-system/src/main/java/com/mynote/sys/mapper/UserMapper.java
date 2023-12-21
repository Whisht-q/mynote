package com.mynote.sys.mapper;

import com.mynote.base.common.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
