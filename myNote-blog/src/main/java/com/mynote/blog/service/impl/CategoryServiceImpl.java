package com.mynote.blog.service.impl;

import com.mynote.base.common.blog.entity.Category;
import com.mynote.blog.mapper.CategoryMapper;
import com.mynote.blog.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:52
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
