package com.mynote.notes.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.Result;
import com.mynote.base.common.note.dto.ContentUpdateDto;
import com.mynote.base.common.system.vo.UserVo;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.base.common.note.dto.ContentDto;
import com.mynote.base.common.note.entity.Content;
import com.mynote.notes.feign.SystemFeignClient;
import com.mynote.notes.service.ContentService;
import com.mynote.base.common.note.vo.ContentVo;
import com.mynote.notes.service.NoteStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 笔记内容表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 01:32:27
 */
@Api(value = "笔记内容", tags = "笔记内容")
@RestController
@RequestMapping("/note/content")
public class ContentController {

    @Resource
    private ContentService contentService;

    @Resource
    private SystemFeignClient systemFeignClient;


    @ApiOperation(value = "添加笔记")
    @PostMapping("/add")
    public Result<?> add(ContentDto contentDto, @RequestParam(name = "categoryId") String categoryId,
                         @RequestParam(name = "status", defaultValue = "0") String status) {
        Result<UserVo> userRes = systemFeignClient.getUserById(contentDto.getUserId());
        if (CommonUtil.isEmpty(userRes.getData())) {
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        Content contentCheckTitle = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getTitle, contentDto.getTitle()));
        if (!CommonUtil.isEmpty(contentCheckTitle)) {
            throw new NoteException(ResultCodeEnum.TITLE_IS_EXIST);
        }
        Content content = new Content();
        BeanUtils.copyProperties(contentDto, content);
        contentService.save(content);
        Content writedContent = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getTitle, contentDto.getTitle()));
        contentService.saveCategoryAndStatus(writedContent.getId(), categoryId, status);
        return Result.success();
    }

    @ApiOperation(value = "修改笔记")
    @PostMapping("/update")
    public Result<?> update(ContentUpdateDto contentUpdateDto) {
        Content note = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getId, contentUpdateDto.getId()));
        if (CommonUtil.isEmpty(note)){
            throw new NoteException(ResultCodeEnum.NOTE_IS_NONE);
        }
        note.setTitle(contentUpdateDto.getTitle());
        note.setContent(contentUpdateDto.getContent());
        note.setUpdatedTime(null);
        contentService.updateNote(note,contentUpdateDto.getStatus());

        return Result.success();
    }

    @ApiOperation(value = "删除笔记")
    @PostMapping("/delete/{id}")
    public Result<?> delete(@PathVariable("id") String id) {
        Content content = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getId, id));
        if (CommonUtil.isEmpty(content)) {
            throw new NoteException(ResultCodeEnum.NOTE_IS_NONE);
        }
        contentService.logicDelete(id);
        return Result.success();
    }

    @ApiOperation(value = "笔记查询")
    @PostMapping("/get")
    public Result<ContentVo> get(@RequestParam("id") String id) {
        Content content = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getId, id));
        if (CommonUtil.isEmpty(content)) {
            throw new NoteException(ResultCodeEnum.NOTE_IS_NONE);
        }
        ContentVo contentVo = new ContentVo();
        BeanUtils.copyProperties(content, contentVo);

        return Result.success(contentVo);
    }

    @ApiOperation(value = "用户笔记列表")
    @PostMapping("/mynotes")
    public Result<Page<ContentVo>> getMyNotes(@RequestParam("id") String id,
                                              @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                              @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<ContentVo> page = new Page<>(pageNo, pageSize);
        List<Content> list = contentService.list(new LambdaQueryWrapper<Content>()
                .eq(Content::getUserId, id));
        List<ContentVo> collect = list.stream().map(content -> {
            ContentVo contentVo = new ContentVo();
            BeanUtils.copyProperties(content, contentVo);
            return contentVo;
        }).collect(Collectors.toList());
        page.setRecords(collect);
        page.setTotal(collect.size());

        return Result.success(page);
    }

    @ApiOperation(value = "公开笔记列表")
    @GetMapping("/list")
    public Result<Page<ContentVo>> list(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                        @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<ContentVo> page = new Page<>(pageNo, pageSize);
        List<Content> list = contentService.selectPublicNote();
        List<ContentVo> res = list.stream().map(content -> {
            ContentVo contentVo = new ContentVo();
            BeanUtils.copyProperties(content, contentVo);
            return contentVo;
        }).collect(Collectors.toList());
        page.setRecords(res);
        page.setTotal(res.size());

        return Result.success(page);
    }


}
