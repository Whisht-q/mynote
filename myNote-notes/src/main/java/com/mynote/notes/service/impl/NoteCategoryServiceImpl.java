package com.mynote.notes.service.impl;

import com.mynote.base.common.note.dto.NoteCategoryDto;
import com.mynote.base.common.note.entity.NoteCategory;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.notes.mapper.NoteCategoryMapper;
import com.mynote.notes.service.NoteCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 笔记分类关联表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Service
public class NoteCategoryServiceImpl extends ServiceImpl<NoteCategoryMapper, NoteCategory> implements NoteCategoryService {

    @Autowired
    private NoteCategoryMapper noteCategoryMapper;

    @Override
    public void insert(NoteCategoryDto noteCategoryDto) {
        int res = noteCategoryMapper.checkNoteCategory(noteCategoryDto.getNoteId(), noteCategoryDto.getCategoryId());
        if (CommonUtil.isExist(res)){
            throw new NoteException(ResultCodeEnum.NOTE_CATEGORY_IS_EXIST);
        }
        NoteCategory noteCategory = new NoteCategory();
        BeanUtils.copyProperties(noteCategoryDto,noteCategory);
        noteCategoryMapper.insert(noteCategory);
    }
}
