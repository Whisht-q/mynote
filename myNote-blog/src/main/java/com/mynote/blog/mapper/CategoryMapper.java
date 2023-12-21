package com.mynote.blog.mapper;

import com.mynote.base.common.blog.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 博客分类表 Mapper 接口
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:52
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
