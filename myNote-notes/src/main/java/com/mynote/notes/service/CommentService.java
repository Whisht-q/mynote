package com.mynote.notes.service;

import com.mynote.base.common.note.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 笔记评论表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
public interface CommentService extends IService<Comment> {

    void logicDelete(String id);
}
