package com.mynote.blog.service.impl;

import com.mynote.base.common.blog.entity.Comment;
import com.mynote.blog.mapper.CommentMapper;
import com.mynote.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客评论表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
