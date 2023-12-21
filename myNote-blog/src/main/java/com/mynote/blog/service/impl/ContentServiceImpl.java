package com.mynote.blog.service.impl;

import com.mynote.base.common.blog.entity.Content;
import com.mynote.blog.mapper.ContentMapper;
import com.mynote.blog.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客内容表 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:59:53
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
