package com.mynote.thirdparty.minio.service;

import com.mynote.base.common.minio.entity.MediaFiles;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 媒资信息 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-28 09:56:51
 */
public interface MediaFilesService extends IService<MediaFiles> {

    String simplePicUpload(String localPath, MultipartFile file,String userId);

}
