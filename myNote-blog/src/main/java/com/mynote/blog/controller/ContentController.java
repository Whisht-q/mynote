package com.mynote.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.Result;
import com.mynote.base.common.blog.dto.BlogContentDto;
import com.mynote.base.common.blog.entity.Category;
import com.mynote.base.common.blog.entity.Content;
import com.mynote.base.common.blog.vo.BlogTitleVo;
import com.mynote.base.common.system.vo.UserVo;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.blog.feign.SystemFeignClient;
import com.mynote.blog.service.CategoryService;
import com.mynote.blog.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 博客内容表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Api(value = "博客内容管理", tags = "博客内容管理")
@RestController
@RequestMapping("/blog/content")
public class ContentController {

    @Resource
    private ContentService contentService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private SystemFeignClient systemFeignClient;


    @ApiOperation("博客添加")
    @PostMapping("/addContent")
    public Result<?> addContent(BlogContentDto blogContentDto) {
        Result<UserVo> user = systemFeignClient.getUserById(blogContentDto.getUserId());
        if (CommonUtil.isEmpty(user.getData())) {
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        Category category = categoryService.getOne(new LambdaQueryWrapper<Category>()
                .eq(Category::getId, blogContentDto.getCategoryId()));
        if (CommonUtil.isEmpty(category)) {
            throw new NoteException(ResultCodeEnum.CATEGORY_IS_NONE);
        }
        Content content = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getTitle, blogContentDto.getTitle()));
        if (!CommonUtil.isEmpty(content)) {
            throw new NoteException(ResultCodeEnum.BLOG_IS_EXIST);
        }
        contentService.saveContent(blogContentDto);

        return Result.success();
    }

    @ApiOperation("公开博客列表")
    @GetMapping("/publicList")
    public Result<Page<BlogTitleVo>> publicList(@RequestParam(name = "userId", defaultValue = "") String userId,
                                                @RequestParam(name = "categoryId", defaultValue = "") String categoryId,
                                                @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<BlogTitleVo> page = new Page<>(pageNo, pageSize);
        List<BlogTitleVo> list = contentService.getPublicList(userId,categoryId);
        list.stream().forEach(blogTitleVo -> {
            Result<UserVo> user = systemFeignClient.getUserById(blogTitleVo.getUserId());
            blogTitleVo.setUserName(user.getData().getUsername());
        });
        page.setRecords(list);
        page.setTotal(list.size());

        return Result.success(page);
    }

    @ApiOperation("根据id查询博客列表")
    @PostMapping("/list/{id}")
    public Result<Page<BlogTitleVo>> listById(@PathVariable("id") String id,
                                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Result<UserVo> user = systemFeignClient.getUserById(id);
        if (CommonUtil.isEmpty(user)) {
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        Page<BlogTitleVo> page = new Page<>(pageNo, pageSize);
        List<Content> contentList = contentService.selectListByUserId(id);
        List<BlogTitleVo> collect = contentList.stream().map(content -> {
            BlogTitleVo blogTitleVo = new BlogTitleVo();
            BeanUtils.copyProperties(content, blogTitleVo);
            blogTitleVo.setUserName(user.getData().getUsername());
            return blogTitleVo;
        }).collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(collect.size());

        return Result.success(page);
    }

}
