package com.devinwang.gchat.service;

import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatDiscuss;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devinwang.gchat.entity.vo.ContentData;
import com.devinwang.gchat.entity.vo.UploadContent;

import java.util.ArrayList;

/**
* @author devin
* @description 针对表【chat_discuss(内容信息)】的数据库操作Service
* @createDate 2022-12-03 22:09:56
*/
public interface ChatDiscussService extends IService<ChatDiscuss> {

    ArrayList<ContentData> getPageData(Integer num, Integer page);

    ContentData getContentById(String contentId);

    R saveContent(UploadContent uploadContent);
}
