package com.mynote.blog.feign;

import com.mynote.base.common.Result;
import com.mynote.base.common.system.vo.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhishubin
 * @date 2023/12/22 10:46
 * @description
 */
@FeignClient(name = "mynote-system")
public interface SystemFeignClient {

    @PostMapping("/sys/user/getUser")
    Result<UserVo> getUserById(@RequestParam("id") String id);
}
