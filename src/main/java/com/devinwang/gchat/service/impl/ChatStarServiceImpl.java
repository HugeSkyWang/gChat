package com.devinwang.gchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatStar;
import com.devinwang.gchat.mapper.ChatDiscussMapper;
import com.devinwang.gchat.service.ChatStarService;
import com.devinwang.gchat.mapper.ChatStarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author devin
* @description 针对表【chat_star(点赞列表)】的数据库操作Service实现
* @createDate 2022-12-03 22:11:02
*/
@Service
public class ChatStarServiceImpl extends ServiceImpl<ChatStarMapper, ChatStar>
    implements ChatStarService{

    @Autowired
    private ChatDiscussMapper chatDiscussMapper;

    @Override
    @Transactional
    public R addStar(String contentId, String openId) {
        // 增加点赞表的数据
        ChatStar chatStar = new ChatStar();
        chatStar.setContentId(contentId);
        chatStar.setOpenId(openId);
        boolean save = this.save(chatStar);
        if (!save) {
            throw new RuntimeException("增加点赞表失败");
        }
        int count = chatDiscussMapper.incrementLikes(contentId);
        if (count == 0) {
            throw new RuntimeException("增加点赞数量失败");
        }
        return R.ok();
    }

    @Override
    public R deleteStar(String contentId, String openId) {
        LambdaQueryWrapper<ChatStar> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatStar::getContentId, contentId)
                .eq(ChatStar::getOpenId, openId);
        boolean remove = this.remove(lambdaQueryWrapper);
        if (!remove) {
            throw new RuntimeException("删除点赞表失败");
        }
        int count = chatDiscussMapper.reduceLikes(contentId);
        if (count == 0) {
            throw new RuntimeException("减少点赞失败");
        }
        return R.ok();
    }
}




