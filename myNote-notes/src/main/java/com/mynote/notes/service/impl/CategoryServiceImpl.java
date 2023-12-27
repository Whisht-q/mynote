package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.note.entity.Category;
import com.mynote.base.common.note.vo.CategoryVo;
import com.mynote.notes.mapper.CategoryMapper;
import com.mynote.notes.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhishubin
 * @date 2023/12/27 10:04
 * @description
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public Page<CategoryVo> getTreeList(Page<CategoryVo> page) {
        List<Category> list = this.list();
        List<CategoryVo> res = list.stream()
                .filter(category -> "0".equals(category.getParentId()))
                .map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(category, categoryVo);
            categoryVo.setChildren(getChildren(category, list));
            return categoryVo;
        }).collect(Collectors.toList());

        page.setRecords(res);
        page.setTotal(res.size());

        return page;
    }

    private List<CategoryVo> getChildren(Category root,List<Category> list){
        List<CategoryVo> collect = list.stream()
                .filter(category -> category.getParentId().equals(root.getId()))
                .map(category -> {
                    CategoryVo categoryVo = new CategoryVo();
                    BeanUtils.copyProperties(category, categoryVo);
                    categoryVo.setChildren(getChildren(category, list));
                    return categoryVo;
                }).collect(Collectors.toList());

        return collect;
    }
}
