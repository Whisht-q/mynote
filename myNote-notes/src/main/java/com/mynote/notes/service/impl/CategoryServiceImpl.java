package com.mynote.notes.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.base.common.note.dto.TitleCategoryDto;
import com.mynote.base.common.note.entity.Category;
import com.mynote.base.common.note.vo.CategoryVo;
import com.mynote.notes.mapper.CategoryMapper;
import com.mynote.notes.mapper.ContentMapper;
import com.mynote.notes.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhishubin
 * @date 2023/12/27 10:04
 * @description
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ContentMapper contentMapper;

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

    @Override
    public List<CategoryVo> getTreeListByUserId(String userId) {
        List<TitleCategoryDto> categoryList = contentMapper.getTreeListByUserId(userId);
        categoryList.forEach(System.out::println);
        Set<CategoryVo> collect = categoryList.stream().map(categoryDto -> {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setName(categoryDto.getParentName());
            categoryVo.setId(categoryDto.getParentId());
            categoryVo.setChildren(getTitleChildren(categoryDto, categoryList));
            return categoryVo;
        }).collect(Collectors.toSet());

        List<CategoryVo> res = new ArrayList<>(collect);
        return res;
    }

    private List<CategoryVo> getTitleChildren(TitleCategoryDto categoryDto, List<TitleCategoryDto> categoryList) {
        List<CategoryVo> collect = categoryList.stream().filter(category -> {
            return category.getParentId().equals(categoryDto.getParentId());
        }).map(category -> {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setId(category.getId());
            categoryVo.setName(category.getName());
            return categoryVo;
        }).collect(Collectors.toList());

        return collect;
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
