package com.mynote.notes.service.impl;

import com.mynote.base.common.note.entity.NoteStatus;
import com.mynote.notes.mapper.NoteStatusMapper;
import com.mynote.notes.service.NoteStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 笔记状态表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Service
public class NoteStatusServiceImpl extends ServiceImpl<NoteStatusMapper, NoteStatus> implements NoteStatusService {

    @Autowired
    private NoteStatusMapper noteStatusMapper;
    @Override
    public void updateStstus(String id, byte status) {
        noteStatusMapper.updateStstus(id,status);
    }
}
