package com.mynote.notes.service;

import com.mynote.base.common.note.entity.NoteStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 笔记状态表 服务类
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-19 09:58:53
 */
public interface NoteStatusService extends IService<NoteStatus> {

    void updateStstus(String id, byte status);
}
