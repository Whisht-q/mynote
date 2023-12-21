package com.mynote.notes.service;

import com.mynote.base.common.note.dto.NoteCategoryDto;
import com.mynote.base.common.note.entity.NoteCategory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 笔记分类关联表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
public interface NoteCategoryService extends IService<NoteCategory> {

    void insert(NoteCategoryDto noteCategoryDto);
}
