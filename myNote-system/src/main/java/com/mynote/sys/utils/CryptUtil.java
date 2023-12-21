package com.mynote.sys.utils;

import cn.hutool.crypto.digest.DigestUtil;
import com.mynote.base.utils.SaltUtil;
import com.mynote.base.common.system.dto.UserDto;
import com.mynote.base.common.system.dto.UserInfoDto;
import com.mynote.base.common.system.entity.User;
import org.springframework.beans.BeanUtils;

import static com.mynote.base.constant.SaltCode.SALTLENGTH;
import static com.mynote.base.constant.SaltCode.SALTMSG;

/**
 * @author zhishubin
 * @date 2023/12/14 16:48
 * @description 密码加密工具类
 */
public class CryptUtil {

    private CryptUtil(){};

    public static UserDto cryptCode(UserDto userDto) {
        String password = userDto.getPassword();
        UserDto resDto = new UserDto();
        String salt = SaltUtil.generateRandomString(SALTLENGTH);
        userDto.setSalt(salt);
        password = firstCrypt(password);
        password = secondCrypt(salt, password);
        userDto.setPassword(password);
        BeanUtils.copyProperties(userDto,resDto);

        return resDto;
    }

    public static UserDto cryptCode(UserInfoDto userInfoDto) {
        String password = userInfoDto.getPassword();
        UserDto resDto = new UserDto();
        String salt = SaltUtil.generateRandomString(SALTLENGTH);
        resDto.setSalt(salt);
        password = firstCrypt(password);
        password = secondCrypt(salt, password);
        userInfoDto.setPassword(password);
        BeanUtils.copyProperties(userInfoDto,resDto);

        return resDto;
    }

    public static String cryptCode(String password,String salt){
        password = firstCrypt(password);
        password = secondCrypt(salt,password);
        return password;
    }

    public static Boolean verifyCryptCode(String password,User user){
        String dbPassword = cryptCode(password, user.getSalt());
        return dbPassword.equals(user.getPassword());
    }

    public static String firstCrypt(String password) {
        return DigestUtil.md5Hex(password + "&" + SALTMSG);
    }

    public static String secondCrypt(String salt, String password) {
        return DigestUtil.md5Hex(password + "*" + salt);
    }
}
