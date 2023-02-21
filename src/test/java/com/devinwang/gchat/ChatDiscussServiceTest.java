package com.devinwang.gchat;

import com.devinwang.gchat.mapper.ChatDiscussMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatDiscussServiceTest {

    @Autowired
    private ChatDiscussMapper chatDiscussMapper;

    @Test
    public void testIncrementLikes() {
        System.out.println(chatDiscussMapper);
        // int b = chatDiscussMapper.incrementLikes("031fa067e0f044c1ae80b2d5796a886d");
        // System.out.println(b);
    }
}
