package com.mynote.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mynote.base.common.blog.dto.BlogContentDto;
import com.mynote.base.common.blog.entity.Content;
import com.mynote.base.common.blog.entity.Status;
import com.mynote.base.common.blog.entity.UnionCategory;
import com.mynote.blog.mapper.ContentMapper;
import com.mynote.blog.service.CategoryService;
import com.mynote.blog.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mynote.blog.service.StatusService;
import com.mynote.blog.service.UnionCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private UnionCategoryService unionCategoryService;

    @Autowired
    private StatusService statusService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveContent(BlogContentDto blogContentDto) {
        Content content = new Content();
        BeanUtils.copyProperties(blogContentDto,content);
        this.save(content);
        Content writeContent = this.getOne(new LambdaQueryWrapper<Content>()
                .eq(Content::getTitle, blogContentDto.getTitle()));
        UnionCategory unionCategory = new UnionCategory();
        unionCategory.setBlogId(writeContent.getId());
        unionCategory.setCategoryId(blogContentDto.getCategoryId());
        unionCategoryService.save(unionCategory);
        Status status = new Status();
        status.setBlogId(writeContent.getId());
        status.setStatus(blogContentDto.getStatus());
        statusService.save(status);
    }
}
