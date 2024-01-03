package com.mynote.thirdparty.minio.controller;

import com.mynote.base.common.PicResult;
import com.mynote.base.common.Result;
import com.mynote.base.common.minio.UploadResp;
import com.mynote.base.common.minio.dto.FileUploadDto;
import com.mynote.thirdparty.minio.service.MediaFilesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 媒资信息 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-28 09:56:51
 */
@Api(value = "媒资管理",tags = "媒资管理")
@RestController
@RequestMapping("third/party/minio")
public class MediaFilesController {

    @Resource
    private MediaFilesService mediaFilesService;

    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public PicResult upload(@RequestPart MultipartFile file, String userId) {

        String url;
        try {
            File tempFile = File.createTempFile("minio", ".temp");
            file.transferTo(tempFile);
            String absolutePath = tempFile.getAbsolutePath();
            url = mediaFilesService.simplePicUpload(absolutePath, file, userId);
            UploadResp uploadResp = new UploadResp();
            uploadResp.setUrl(url);
            return PicResult.success(uploadResp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
