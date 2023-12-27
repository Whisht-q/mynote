package com.mynote.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mynote.base.common.blog.dto.BlogContentDto;
import com.mynote.base.common.blog.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mynote.base.common.blog.vo.BlogTitleVo;

import java.util.List;

/**
 * <p>
 * 博客内容表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
public interface ContentService extends IService<Content> {

    void saveContent(BlogContentDto blogContentDto);

    List<BlogTitleVo> getPublicList(String userId,String categoryId);

    List<Content> selectListByUserId( String id);
}
