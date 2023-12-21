package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.note.dto.NoteCategoryDto;
import com.mynote.base.common.note.dto.NoteStatusDto;
import com.mynote.base.common.note.entity.Content;
import com.mynote.base.common.note.entity.NoteStatus;
import com.mynote.base.common.note.vo.ContentVo;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.notes.mapper.ContentMapper;
import com.mynote.notes.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.notes.service.NoteCategoryService;
import com.mynote.notes.service.NoteStatusService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 笔记内容表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    private NoteCategoryService noteCategoryService;

    @Autowired
    private NoteStatusService noteStatusService;

    @Autowired
    private ContentMapper contentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCategoryAndStatus(String id, String categoryId, String status) {
        noteCategoryService.insert(new NoteCategoryDto(id, categoryId));
        NoteStatus noteStatusCheck = noteStatusService.getOne(new LambdaQueryWrapper<NoteStatus>()
                .eq(NoteStatus::getNoteId, id));
        if (!CommonUtil.isEmpty(noteStatusCheck)){
            throw new NoteException(ResultCodeEnum.NOTE_IS_EXIST);
        }
        NoteStatusDto noteStatusDto = new NoteStatusDto(id, CommonUtil.strToBool(status));
        NoteStatus noteStatus = new NoteStatus();
        BeanUtils.copyProperties(noteStatusDto,noteStatus);
        noteStatusService.save(noteStatus);
    }

    @Override
    public void logicDelete(String id) {
        contentMapper.logicDelete(id);
    }

    @Override
    public List<Content> selectPublicNote() {
        return contentMapper.selectPublicNote();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNote(Content note, byte status) {
        this.updateById(note);
        NoteStatus noteStatus = noteStatusService.getOne(new LambdaQueryWrapper<NoteStatus>()
                .eq(NoteStatus::getNoteId, note.getId()));
        if (CommonUtil.isEmpty(noteStatus)){
            throw new NoteException(ResultCodeEnum.NOTE_IS_NONE);
        }
        noteStatusService.updateStstus(note.getId(),status);
    }
}
