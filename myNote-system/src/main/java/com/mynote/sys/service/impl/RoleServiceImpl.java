package com.mynote.sys.service.impl;

import com.mynote.base.common.system.entity.Role;
import com.mynote.sys.mapper.RoleMapper;
import com.mynote.sys.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
