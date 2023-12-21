package com.mynote.notes.service.impl;

import com.mynote.base.common.note.entity.Comment;
import com.mynote.notes.mapper.CommentMapper;
import com.mynote.notes.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 笔记评论表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public void logicDelete(String id) {
        commentMapper.logicDelete(id);
    }
}
