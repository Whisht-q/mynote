package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.note.entity.Category;
import com.mynote.notes.mapper.CategoryMapper;
import com.mynote.notes.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.note.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 笔记分类表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public Page<CategoryVo> getTreeList(Page<CategoryVo> page) {
        List<Category> list = this.list();
        List<CategoryVo> res = list.stream().filter(category -> {
                    return "0".equals(category.getParentId());
                }).map(category -> {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category, categoryVo);
                    categoryVo.setChildren(getChildList(categoryVo, list));
                    return categoryVo;
                }).sorted(Comparator.comparing(CategoryVo::getName))
                .collect(Collectors.toList());
        page.setRecords(res);
        page.setTotal(res.size());
        page.setCurrent(page.getCurrent());
        return page;

    }

    private List<CategoryVo> getChildList(CategoryVo parent, List<Category> categoryList) {
        return categoryList.stream().filter(category -> {
                    return parent.getId().equals(category.getParentId());
                }).map(category -> {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category, categoryVo);
                    categoryVo.setChildren(getChildList(categoryVo, categoryList));
                    return categoryVo;
                }).sorted(Comparator.comparing(CategoryVo::getName))
                .collect(Collectors.toList());
    }
}
