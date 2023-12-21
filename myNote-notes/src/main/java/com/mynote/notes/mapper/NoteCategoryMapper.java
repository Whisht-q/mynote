package com.mynote.notes.mapper;

import com.mynote.base.common.note.entity.NoteCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 笔记分类关联表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Mapper
public interface NoteCategoryMapper extends BaseMapper<NoteCategory> {

    int checkNoteCategory(@Param("noteId") String noteId, @Param("categoryId") String categoryId);
}
