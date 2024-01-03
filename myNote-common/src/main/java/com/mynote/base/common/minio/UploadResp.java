package com.mynote.base.common.minio;

import lombok.Data;

/**
 * @author zhishubin
 * @date 2024/1/2 15:26
 * @description
 */
@Data
public class UploadResp {

    private String url;

    private String alt;

    private String href;

    public UploadResp(){
        this.alt = null;
        this.href = null;
    }
}
