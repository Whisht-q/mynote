package com.mynote.base.common.minio.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 媒资信息
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-28 09:56:51
 */
@Getter
@Setter
@TableName("media_files")
@ApiModel(value = "MediaFiles对象", description = "媒资信息")
public class MediaFiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文件id,md5值")
    @TableId("id")
    private String id;

    @ApiModelProperty("文件类型（图片、文档，视频）")
    @TableField("file_type")
    private String fileType;

    @ApiModelProperty("存储目录")
    @TableField("bucket")
    private String bucket;

    @ApiModelProperty("存储路径")
    @TableField("file_path")
    private String filePath;

    @ApiModelProperty("媒资文件访问地址")
    @TableField("url")
    private String url;

    @ApiModelProperty("上传人")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("上传时间")
    @TableField("create_date")
    private LocalDateTime createDate;

    @ApiModelProperty("修改时间")
    @TableField("change_date")
    private LocalDateTime changeDate;

    @ApiModelProperty("状态,1:正常，0:不展示")
    @TableField("status")
    private String status;

    @ApiModelProperty("文件大小")
    @TableField("file_size")
    private Long fileSize;

    public MediaFiles(){
        this.status = "1";
    }
}
