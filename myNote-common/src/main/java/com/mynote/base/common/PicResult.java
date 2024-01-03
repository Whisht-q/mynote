package com.mynote.base.common;

import com.mynote.base.common.minio.UploadResp;
import lombok.Data;

import java.util.List;

/**
 * @author zhishubin
 * @date 2024/1/2 15:25
 * @description
 */
@Data
public class PicResult {
    private Integer errno;

    private UploadResp data;


    public static PicResult success(UploadResp resp){
        PicResult picResult = new PicResult();
        picResult.setErrno(0);
        picResult.setData(resp);
        return picResult;
    }
}
