package com.mynote.notes.mapper;

import com.mynote.base.common.note.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 笔记分类表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
