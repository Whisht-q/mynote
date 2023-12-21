package com.mynote.notes.service.impl;

import com.mynote.base.common.note.entity.Reply;
import com.mynote.notes.mapper.ReplyMapper;
import com.mynote.notes.service.ReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 笔记评论回复表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

}
