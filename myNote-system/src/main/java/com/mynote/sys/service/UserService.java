package com.mynote.sys.service;

import com.mynote.base.common.system.dto.UserDto;
import com.mynote.base.common.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
public interface UserService extends IService<User> {

    /**
     * 添加用户
     * @param userDto 用户信息
     */
    void addUser(UserDto userDto);

    /**
     * 校验用户名是否已存在
     * @param username 用户名
     * @return
     */
    Boolean isExist(String username);

}
