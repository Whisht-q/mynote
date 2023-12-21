package com.mynote.blog.service.impl;

import com.mynote.base.common.blog.entity.Status;
import com.mynote.blog.mapper.StatusMapper;
import com.mynote.blog.service.StatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客状态表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {

}
