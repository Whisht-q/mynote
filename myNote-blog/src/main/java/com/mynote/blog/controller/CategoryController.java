package com.mynote.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.Result;
import com.mynote.base.common.blog.entity.Category;
import com.mynote.base.common.blog.vo.BlogCategoryVo;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.constant.StringCode;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客分类表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:52
 */
@Api(value = "博客分类管理", tags = "博客分类管理")
@RestController
@RequestMapping("/blog/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @ApiOperation(value = "添加博客分类")
    @PostMapping("/add")
    public Result<?> add(@RequestParam("parentId") String parentId, @RequestParam("name") String name) {
        Category one = categoryService.getOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, name.toLowerCase()));
        if (!CommonUtil.isEmpty(one)) {
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_EXIST);
        }
        Category categoryPid = categoryService.getById(parentId);
        if (!StringCode.PARENT_IS_NULL.equals(parentId) && CommonUtil.isEmpty(categoryPid)){
            throw new NoteException(ResultCodeEnum.PARENT_CATEGORY_NONE);
        }
        Category category = new Category();
        category.setParentId(parentId);
        category.setName(name.toLowerCase());
        categoryService.save(category);

        return Result.success();
    }

    @ApiOperation(value = "修改博客分类")
    @PostMapping("/update")
    public Result<?> update(@RequestParam("id") String id, @RequestParam("name") String name) {
        Category category = categoryService.getById(id);
        if (CommonUtil.isEmpty(category)) {
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_NONE);
        }
        Category one = categoryService.getOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, name));
        if (!CommonUtil.isEmpty(one)) {
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_EXIST);
        }
        category.setName(name);
        category.setUpdatedTime(null);
        categoryService.save(category);

        return Result.success();
    }

    @ApiOperation(value = "博客分类列表")
    @GetMapping("/list")
    public Result<Page<BlogCategoryVo>> list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        Page<BlogCategoryVo> page = new Page<>(pageNo, pageSize);
        List<Category> list = categoryService.list();
        List<BlogCategoryVo> collect = list.stream().filter(category -> {
            return "0".equals(category.getParentId());
        }).map(category -> {
            BlogCategoryVo blogCategoryVo = new BlogCategoryVo();
            BeanUtils.copyProperties(category, blogCategoryVo);
            blogCategoryVo.setChildren(getChildren(category, list));

            return blogCategoryVo;
        }).collect(Collectors.toList());

        page.setRecords(collect);
        page.setTotal(page.getSize());

        return Result.success(page);
    }

    private List<BlogCategoryVo> getChildren(Category root, List<Category> list) {
        List<BlogCategoryVo> collect = list.stream().filter(category -> {
            return category.getParentId().equals(root.getId());
        }).map(category -> {
            BlogCategoryVo blogCategoryVo = new BlogCategoryVo();
            BeanUtils.copyProperties(category, blogCategoryVo);
            blogCategoryVo.setChildren(getChildren(category, list));
            return blogCategoryVo;
        }).collect(Collectors.toList());

        return collect;
    }

}
