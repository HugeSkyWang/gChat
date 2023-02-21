package com.devinwang.gchat.mapper;

import com.devinwang.gchat.entity.ChatDiscuss;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author devin
* @description 针对表【chat_discuss(内容信息)】的数据库操作Mapper
* @createDate 2022-12-03 22:09:56
* @Entity com.devinwang.gchat.entity.ChatDiscuss
*/
public interface ChatDiscussMapper extends BaseMapper<ChatDiscuss> {

    int incrementLikes(String contentId);

    int reduceLikes(String contentId);
}




