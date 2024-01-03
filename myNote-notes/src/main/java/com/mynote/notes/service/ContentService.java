package com.mynote.notes.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.note.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mynote.base.common.note.vo.ContentTitleVo;
import com.mynote.base.common.note.vo.ContentVo;

import java.util.List;

/**
 * <p>
 * 笔记内容表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
public interface ContentService extends IService<Content> {

    void saveCategoryAndStatus(String id, String categoryId, String status);

    void logicDelete(String id);

    List<Content> selectPublicNote();

    void updateNote(Content note, byte status);

    Page<ContentTitleVo> getContentTitleByUserId(Page<ContentTitleVo> page,String userId, List<String> categoryIds);
}
