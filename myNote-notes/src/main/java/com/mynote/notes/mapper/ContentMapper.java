package com.mynote.notes.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.note.entity.Content;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mynote.base.common.note.vo.ContentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 笔记内容表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Mapper
public interface ContentMapper extends BaseMapper<Content> {

    void logicDelete(String id);

    List<Content> selectPublicNote();
}
