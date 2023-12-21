package com.mynote.notes.controller;

import com.mynote.base.common.Result;
import com.mynote.base.common.note.dto.CommentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 笔记评论表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 01:32:27
 */
@Api(value = "笔记评论",tags = "笔记评论")
@RestController
@RequestMapping("/note/comment")
public class CommentController {

    @ApiOperation(value = "添加评论")
    @PostMapping("/add")
    public Result<?> add(CommentDto commentDto){

        return Result.success();
    }

}
