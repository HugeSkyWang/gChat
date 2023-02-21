package com.devinwang.gchat.service;

import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatStar;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author devin
* @description 针对表【chat_star(点赞列表)】的数据库操作Service
* @createDate 2022-12-03 22:11:02
*/
public interface ChatStarService extends IService<ChatStar> {

    R addStar(String contentId, String openId);

    R deleteStar(String contentId, String openId);
}
