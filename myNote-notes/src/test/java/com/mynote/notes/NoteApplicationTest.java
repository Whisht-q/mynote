package com.mynote.notes;

import com.mynote.base.common.note.vo.ContentTitleVo;
import com.mynote.notes.mapper.ContentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhishubin
 * @date 2023/12/29 13:54
 * @description
 */
@SpringBootTest
public class NoteApplicationTest {

    @Autowired
    private ContentMapper contentMapper;

    @Test
    public void test01(){
        List<String> list = new ArrayList<>();
        list.add("1737039260129701889");
        list.add("1737039354799337473");
        list.add("1737280743588028417");
        //List<ContentTitleVo> res = contentMapper.getContentTitleByUserId("1", list);
        //res.forEach(System.out::println);
    }
}
