package com.mynote.notes.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mynote.base.common.Result;
import com.mynote.base.common.note.entity.Comment;
import com.mynote.base.common.note.entity.Content;
import com.mynote.base.common.system.vo.UserVo;
import com.mynote.base.constant.DeleteCode;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.base.utils.RedisUtil;
import com.mynote.notes.feign.SystemFeignClient;
import com.mynote.notes.service.CommentService;
import com.mynote.notes.service.ContentService;
import com.mynote.notes.service.NoteCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Resource
    private SystemFeignClient systemFeignClient;

    @Resource
    private ContentService contentService;

    @Resource
    private CommentService commentService;

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "添加评论")
    @PostMapping("/add")
    public Result<?> add(@RequestParam(name = "userId") String userId,
                         @RequestParam(name = "noteId") String noteId,
                         @RequestParam(name = "content") String content){
        Result<UserVo> user = systemFeignClient.getUserById(userId);
        if (CommonUtil.isEmpty(user)){
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        Content one = contentService.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getId, noteId));
        if (CommonUtil.isEmpty(one)){
            throw new NoteException(ResultCodeEnum.NOTE_IS_NONE);
        }
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setNoteId(noteId);
        comment.setContent(content);
        commentService.save(comment);

        return Result.success();
    }

    @ApiOperation(value = "删除评论")
    @PostMapping("/delete")
    public Result<?> delete(@RequestParam(name = "id") String id) {
        Comment one = commentService.getOne(new LambdaQueryWrapper<Comment>()
                .eq(Comment::getId, id));
        if (CommonUtil.isEmpty(one)){
            throw new NoteException(ResultCodeEnum.COMMENT_IS_NONE);
        }
        commentService.logicDelete(id);
        redisUtil.sSet(DeleteCode.COMMENT_DELETE,id);
        return Result.success();
    }

    @ApiOperation("获取删除id")
    @GetMapping("/getdeleteIds")
    public Result<?> getDeleteList(){
        Set<Object> set = redisUtil.sGet(DeleteCode.COMMENT_DELETE);
        List<String> collect = set.stream().map(Object::toString).collect(Collectors.toList());
        return Result.success(collect);
    }

}
