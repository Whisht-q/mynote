package com.mynote.notes.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.Result;
import com.mynote.base.common.system.vo.UserVo;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.constant.StringCode;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.base.common.note.dto.CategoryDto;
import com.mynote.base.common.note.entity.Category;
import com.mynote.notes.feign.SystemFeignClient;
import com.mynote.notes.service.CategoryService;
import com.mynote.base.common.note.vo.CategoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 笔记分类表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 01:32:27
 */
@Api(value = "笔记分类", tags = "笔记分类表")
@RestController
@RequestMapping("/note/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private SystemFeignClient systemFeignClient;

    @ApiOperation(value = "添加笔记分类")
    @PostMapping("/add")
    public Result<?> add(@RequestParam(name = "parentId", defaultValue = "0") String parentId,
                         @RequestParam(name = "categoryName") String categoryName) {
        Category parent = categoryService.getById(parentId);
        if (!StringCode.PARENT_IS_NULL.equals(parentId) && CommonUtil.isEmpty(parent)) {
            throw new NoteException(ResultCodeEnum.PARENT_CATEGORY_NONE);
        }
        Category category = categoryService.getOne(new LambdaQueryWrapper<Category>().eq(Category::getName, categoryName.toLowerCase()));
        if (!CommonUtil.isEmpty(category)) {
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_EXIST);
        }
        Category categoryInsert = new Category();
        categoryInsert.setParentId(parentId);
        categoryInsert.setName(categoryName.toLowerCase());
        categoryService.save(categoryInsert);

        return Result.success();
    }

    @ApiOperation(value = "分类列表")
    @GetMapping("/list")
    public Result<Page<CategoryVo>> list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                          @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<CategoryVo> page = new Page<>(pageNo,pageSize);
        Page<CategoryVo> treeList = categoryService.getTreeList(page);

        return Result.success(treeList);
    }

    @ApiOperation(value = "根据id查询笔记分类")
    @PostMapping("/mylist")
    public Result<List<CategoryVo>> myList(@RequestParam("userId")String userId){
        Result<UserVo> user = systemFeignClient.getUserById(userId);
        if (CommonUtil.isEmpty(user.getData())){
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        List<CategoryVo> res = categoryService.getTreeListByUserId(userId);

        return Result.success(res);
    }

    /**
     *
     * @param categoryDto
     * @return
     */
    @ApiOperation(value = "修改分类")
    @PostMapping("/update")
    public Result<?> update(CategoryDto categoryDto) {
        Category category = categoryService.getOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getId, categoryDto.getId()));
        if (CommonUtil.isEmpty(category)){
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_NONE);
        }
        Category categoryCheckName = categoryService.getOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getName, categoryDto.getName()));
        if (!CommonUtil.isEmpty(categoryCheckName)){
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_EXIST);
        }
        BeanUtils.copyProperties(categoryDto,category);
        category.setUpdatedTime(null);
        categoryService.updateById(category);

        return Result.success();
    }

    /**
     * TODO
     * @param id
     * @return
     */
    @ApiOperation(value = "删除分类")
    @PostMapping("/delete")
    public Result<?> delete(@RequestParam(name = "id", defaultValue = "") String id) {

        return Result.success();
    }


}
