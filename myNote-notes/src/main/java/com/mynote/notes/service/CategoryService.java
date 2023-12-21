package com.mynote.notes.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.note.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mynote.base.common.note.vo.CategoryVo;

/**
 * <p>
 * 笔记分类表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
public interface CategoryService extends IService<Category> {

    Page<CategoryVo> getTreeList(Page<CategoryVo> page);

}
