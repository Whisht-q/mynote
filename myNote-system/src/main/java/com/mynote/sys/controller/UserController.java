package com.mynote.sys.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mynote.base.common.Result;
import com.mynote.base.constant.Enum.ResultCodeEnum;
import com.mynote.base.exception.NoteException;
import com.mynote.base.utils.CommonUtil;
import com.mynote.base.utils.ImageUtil;
import com.mynote.base.utils.RedisUtil;
import com.mynote.base.common.system.dto.LoginDto;
import com.mynote.base.common.system.dto.RegisterDto;
import com.mynote.base.common.system.dto.UserDto;
import com.mynote.base.common.system.dto.UserInfoDto;
import com.mynote.base.common.system.entity.User;
import com.mynote.sys.service.UserService;
import com.mynote.sys.utils.CryptUtil;
import com.mynote.base.common.system.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhishubin
 * @since 2023-12-14 02:13:47
 */
@Api(value = "用户管理", tags = "用户管理")
@RestController
@ComponentScan(basePackages = "com.mynote.exception")
@RequestMapping("/sys/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    private final String LOGIN_KEY = "loginUser:";

    /**
     * 用户列表
     *
     * @return
     */
    @ApiOperation(value = "用户列表")
    @GetMapping("/list")
    public Result<?> getList() {
        List<User> list = userService.list();
        return Result.success(list);
    }

    /**
     * 注册用户
     *
     * @param registerDto
     * @return
     */
    @ApiOperation(value = "注册用户")
    @PostMapping("/add")
    public Result<?> addUser(RegisterDto registerDto) {
        Boolean exist = userService.isExist(registerDto.getUsername());
        if (!exist) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(registerDto, userDto);
            userDto = CryptUtil.cryptCode(userDto);
            userService.addUser(userDto);
            return Result.success("用户添加成功");
        }
        throw new NoteException(ResultCodeEnum.NAME_REPEAT);
    }

    /**
     * 登录
     *
     * @param loginDto
     * @return
     */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<?> login(LoginDto loginDto) {
        //生成key
        String key = DigestUtil.md5Hex(loginDto.getCaptcha().toLowerCase() + loginDto.getKey());
        Object checkCode = redisUtil.get(LOGIN_KEY + key);
        //判断验证码
        if (CommonUtil.isEmpty(checkCode) || !(checkCode.toString()).equalsIgnoreCase(loginDto.getCaptcha())) {
            throw new NoteException(ResultCodeEnum.CAPTCHA_ERROR);
        }
        redisUtil.del(LOGIN_KEY + key);
        //判断用户是否存在
        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, loginDto.getUsername()));
        if (user == null) {
            throw new NoteException(ResultCodeEnum.AUTHENTICATION_FAILED);
        }
        Boolean b = CryptUtil.verifyCryptCode(loginDto.getPassword(), user);
        if (b) {
            return Result.success();
        }
        throw new NoteException(ResultCodeEnum.AUTHENTICATION_FAILED);
    }

    /**
     * 获取验证码
     *
     * @param key
     * @return
     */
    @ApiOperation(value = "获取验证码")
    @GetMapping("/getCaptcha/{key}")
    public Result<String> getCaptcha(@PathVariable("key") String key) {
        //生成验证码
        String captcha = ImageUtil.generateVerificationCode();
        //生成key
        String captchaKey = DigestUtil.md5Hex(captcha.toLowerCase() + key);
        //存入redis
        redisUtil.set(LOGIN_KEY + captchaKey, captcha, 300);
        String res;
        try {
            res = ImageUtil.generate(captcha);
        } catch (IOException e) {
            throw new NoteException(ResultCodeEnum.CAPTCHA_GENERATE_ERROR);
        }
        return Result.success(res);
    }

    /**
     * 用户信息修改
     *
     * @param userInfoDto
     * @return
     */
    @ApiOperation(value = "用户信息修改")
    @PostMapping("/update")
    public Result<?> updateUser(@RequestParam("id") String id, UserInfoDto userInfoDto) {
        //查询原用户
        User userOld = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, id));
        if (CommonUtil.isEmpty(userOld)) {
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        //判断新用户名是否可用
        User userCheckName = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userInfoDto.getUsername()));
        if (!CommonUtil.isEmpty(userCheckName)) {
            throw new NoteException(ResultCodeEnum.NAME_REPEAT);
        }
        //新密码加密
        UserDto userDto = CryptUtil.cryptCode(userInfoDto);
        User userUpdate = new User();
        BeanUtils.copyProperties(userDto, userUpdate);
        userUpdate.setId(id);
        userService.updateById(userUpdate);

        return Result.success();
    }

    /**
     * 用户冻结
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户冻结")
    @PostMapping("/ban/{id}")
    public Result<?> banUser(@PathVariable("id") String id) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, id));
        if (CommonUtil.isEmpty(user)) {
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        user.setStatus(false);
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 用户解冻
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户解冻")
    @PostMapping("/unban/{id}")
    public Result<?> unBanUser(@PathVariable("id") String id) {
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, id));
        if (CommonUtil.isEmpty(user)) {
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        user.setStatus(true);
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation(value = "查询用户")
    @PostMapping("/getUser")
    public Result<UserVo> getUserById(@RequestParam("id") String id){
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, id));
        if (CommonUtil.isEmpty(user)){
            throw new NoteException(ResultCodeEnum.USER_NOT_EXIST);
        }
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setPassword("********");
        return Result.success(userVo);
    }
}
