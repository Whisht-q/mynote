package com.mynote.base.common.minio.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhishubin
 * @date 2023/12/28 10:11
 * @description
 */
@Data
@ApiModel(value = "文件上传Dto")
public class FileUploadDto {

    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty("文件大小")
    private Long fileSize;

    @ApiModelProperty("上传人")
    private String userId;


}
