package com.mynote.notes.mapper;

import com.mynote.base.common.note.entity.NoteStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 笔记状态表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Mapper
public interface NoteStatusMapper extends BaseMapper<NoteStatus> {

    void updateStstus(@Param("id") String id, @Param("status") byte status);
}
