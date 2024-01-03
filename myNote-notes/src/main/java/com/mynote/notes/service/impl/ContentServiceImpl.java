package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.note.entity.Content;
import com.mynote.base.common.note.entity.NoteCategory;
import com.mynote.base.common.note.entity.NoteStatus;
import com.mynote.base.common.note.vo.ContentTitleVo;
import com.mynote.base.utils.CommonUtil;
import com.mynote.notes.mapper.ContentMapper;
import com.mynote.notes.mapper.NoteCategoryMapper;
import com.mynote.notes.mapper.NoteStatusMapper;
import com.mynote.notes.service.ContentService;
import com.mynote.notes.service.NoteCategoryService;
import com.mynote.notes.service.NoteStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhishubin
 * @date 2023/12/27 10:06
 * @description
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

    @Autowired
    private NoteCategoryService noteCategoryService;

    @Autowired
    private NoteStatusService noteStatusService;

    @Autowired
    private NoteStatusMapper noteStatusMapper;

    @Autowired
    private ContentMapper contentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveCategoryAndStatus(String id, String categoryId, String status) {
        boolean b = CommonUtil.strToBool(status);
        NoteCategory noteCategory = new NoteCategory();
        noteCategory.setNoteId(id);
        noteCategory.setCategoryId(categoryId);
        noteCategoryService.save(noteCategory);
        NoteStatus noteStatus = new NoteStatus();
        noteStatus.setNoteId(id);
        noteStatus.setStatus(b);
        noteStatusService.save(noteStatus);
    }

    @Override
    public void logicDelete(String id) {
        baseMapper.logicDelete(id);
    }

    @Override
    public List<Content> selectPublicNote() {
        List<Content> contents = baseMapper.selectPublicNote();
        return contents;
    }

    @Override
    public void updateNote(Content note, byte status) {
        baseMapper.updateById(note);
        noteStatusMapper.updateStstus(note.getId(), status);
    }

    @Override
    public Page<ContentTitleVo> getContentTitleByUserId(Page<ContentTitleVo> page, String userId, List<String> categoryIds) {

        Page<ContentTitleVo> list = contentMapper.getContentTitleByUserId(page,userId, categoryIds);

        return list;
    }
}
