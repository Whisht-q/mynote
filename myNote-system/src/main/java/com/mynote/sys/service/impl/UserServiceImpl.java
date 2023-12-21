package com.mynote.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mynote.base.common.system.dto.UserDto;
import com.mynote.base.common.system.entity.User;
import com.mynote.sys.mapper.UserMapper;
import com.mynote.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 10:23:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户
     *
     * @param userDto 用户信息
     */
    @Override
    public void addUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        this.save(user);
    }

    @Override
    public Boolean isExist(String username) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(username != null, User::getUsername, username));
        return user != null;
    }

}
