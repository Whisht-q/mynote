package com.mynote.blog.service.impl;

import com.mynote.base.common.blog.entity.Reply;
import com.mynote.blog.mapper.ReplyMapper;
import com.mynote.blog.service.ReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客评论回复表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

}
