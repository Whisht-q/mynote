package com.mynote;

import com.mynote.base.utils.ImageUtil;
import com.mynote.base.utils.SaltUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.mynote.base.constant.SaltCode.SALTLENGTH;

/**
 * @author zhishubin
 * @date 2023/12/14 17:00
 * @description
 */
public class salttest {

    @Test
    public void test01(){
        System.out.println(SaltUtil.generateRandomString(SALTLENGTH));
    }

    @Test
    public void test02() throws IOException {
        String adcs = ImageUtil.generate("adcs");
        System.out.println(adcs);
    }
}
