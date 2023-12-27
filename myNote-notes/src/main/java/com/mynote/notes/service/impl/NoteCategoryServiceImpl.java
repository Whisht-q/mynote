package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.note.dto.NoteCategoryDto;
import com.mynote.base.common.note.entity.NoteCategory;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.notes.mapper.NoteCategoryMapper;
import com.mynote.notes.service.NoteCategoryService;
import org.springframework.stereotype.Service;

/**
 * @author zhishubin
 * @date 2023/12/27 10:13
 * @description
 */
@Service
public class NoteCategoryServiceImpl extends ServiceImpl<NoteCategoryMapper, NoteCategory> implements NoteCategoryService {
    @Override
    public void insert(NoteCategoryDto noteCategoryDto) {
        int i = baseMapper.checkNoteCategory(noteCategoryDto.getNoteId(), noteCategoryDto.getCategoryId());
        if (i >0){
            throw new NoteException(ResultCodeEnum.NOTE_IS_EXIST);
        }
        NoteCategory noteCategory = new NoteCategory();
        noteCategory.setNoteId(noteCategory.getNoteId());
        noteCategory.setCategoryId(noteCategory.getCategoryId());
        this.save(noteCategory);
    }
}
