package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.note.entity.NoteStatus;
import com.mynote.notes.mapper.NoteStatusMapper;
import com.mynote.notes.service.NoteStatusService;
import org.springframework.stereotype.Service;

/**
 * @author zhishubin
 * @date 2023/12/27 10:14
 * @description
 */
@Service
public class NoteStatusServiceImpl extends ServiceImpl<NoteStatusMapper, NoteStatus> implements NoteStatusService {
    @Override
    public void updateStstus(String id, byte status) {
        baseMapper.updateStstus(id, status);
    }
}
