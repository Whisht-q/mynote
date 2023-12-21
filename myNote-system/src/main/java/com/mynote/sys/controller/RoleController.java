package com.mynote.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mynote.base.common.Result;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.base.common.system.dto.RoleDto;
import com.mynote.base.common.system.entity.Role;
import com.mynote.sys.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 02:13:47
 */
@Api(value = "角色管理", tags = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 添加角色
     *
     * @param roleDto
     * @return
     */
    @ApiOperation(value = "添加角色")
    @PostMapping("/addRole")
    public Result<?> addRole(RoleDto roleDto) {
        Role roleCheckName = roleService.getOne(new LambdaQueryWrapper<Role>()
                .eq(Role::getRoleName, roleDto.getRoleName()));
        if (CommonUtil.isEmpty(roleCheckName)) {
            throw new NoteException(ResultCodeEnum.ROLE_NOT_EXIST);
        }
        Role role = new Role();
        roleService.save(role);
        return Result.success();
    }

    //TODO
    @ApiOperation(value = "删除角色")
    @PostMapping("/deleteRole/{id}")
    public Result<?> deleteUser(@PathVariable("id") String id) {

        return Result.success();
    }


}
