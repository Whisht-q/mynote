package com.mynote.base.common.note.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zhishubin
 * @date 2023/12/29 14:23
 * @description
 */
@Data
public class NoteTitleDto {

    private String id;

    private List<String> categoryIds;

    private Integer pageNo;

    private Integer pageSize;


    public NoteTitleDto() {
        this.categoryIds = null;
    }
}
