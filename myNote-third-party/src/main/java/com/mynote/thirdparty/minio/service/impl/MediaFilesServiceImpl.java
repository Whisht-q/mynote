package com.mynote.thirdparty.minio.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import com.mynote.base.common.minio.entity.MediaFiles;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.constant.StringCode;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.thirdparty.minio.mapper.MediaFilesMapper;
import com.mynote.thirdparty.minio.service.MediaFilesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 媒资信息 服务实现类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-28 09:56:51
 */
@Service
public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements MediaFilesService {

    @Value("${minio.bucket}")
    private String bucket;

    @Autowired
    private MinioClient minioClient;

    @Override
    public String simplePicUpload(String localPath, MultipartFile file,String userId) {

        String md5 = CommonUtil.getFileMd5(new File(localPath));

        MediaFiles one = this.getOne(new LambdaQueryWrapper<MediaFiles>()
                .eq(MediaFiles::getId, md5));

        if (!CommonUtil.isEmpty(one)){
            return one.getUrl();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String currentDate = dateFormat.format(new Date());
        String name = UUID.randomUUID().toString().replaceAll("-","");
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        String objectName = currentDate + "/" + name + "." + extension;

        String mimeType = getMimeType(extension);

        MediaFiles mediaFiles = new MediaFiles();
        mediaFiles.setId(md5);
        mediaFiles.setBucket(bucket);
        mediaFiles.setUserId(userId);
        mediaFiles.setFileSize(file.getSize());
        mediaFiles.setFilePath(currentDate);
        mediaFiles.setFileType("image");
        mediaFiles.setUrl("http://192.168.11.135:9000/"+bucket +"/" +objectName);

        this.save(mediaFiles);

        try {
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(bucket)
                            .filename(localPath)
                            .object(objectName)
                            .contentType(mimeType)
                            .build());
        } catch (Exception e) {
            throw new NoteException(ResultCodeEnum.PIC_UPLOAD_FAIL);
        }

        return "http://192.168.11.135:9000/"+objectName;
    }

    private String getMimeType(String extension) {
        if (extension == null) {
            extension = "";
        }
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        if (extensionMatch != null) {
            mimeType = extensionMatch.getMimeType();
        }
        return mimeType;
    }

}
