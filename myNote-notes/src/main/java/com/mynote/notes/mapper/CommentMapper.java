package com.mynote.notes.mapper;

import com.mynote.base.common.note.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 笔记评论表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
