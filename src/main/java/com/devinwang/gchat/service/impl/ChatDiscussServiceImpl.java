package com.devinwang.gchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatDiscuss;
import com.devinwang.gchat.entity.ChatImg;
import com.devinwang.gchat.entity.ChatUser;
import com.devinwang.gchat.entity.vo.ContentData;
import com.devinwang.gchat.entity.vo.UploadContent;
import com.devinwang.gchat.service.ChatDiscussService;
import com.devinwang.gchat.mapper.ChatDiscussMapper;
import com.devinwang.gchat.service.ChatImgService;
import com.devinwang.gchat.service.ChatStarService;
import com.devinwang.gchat.service.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author devin
 * @description 针对表【chat_discuss(内容信息)】的数据库操作Service实现
 * @createDate 2022-12-03 22:09:56
 */
@Service
public class ChatDiscussServiceImpl extends ServiceImpl<ChatDiscussMapper, ChatDiscuss>
        implements ChatDiscussService {
    @Autowired
    private ChatUserService chatUserService;
    @Autowired
    private ChatImgService chatImgService;

    @Override
    public ArrayList<ContentData> getPageData(Integer num, Integer page) {
        ArrayList<ContentData> result = new ArrayList<>();
        Page<ChatDiscuss> contentPage = new Page<>(page, num);
        LambdaQueryWrapper<ChatDiscuss> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(ChatDiscuss::getGmtCreate);

        this.page(contentPage, queryWrapper);
        List<ChatDiscuss> records = contentPage.getRecords();
        for (ChatDiscuss record : records) {
            ContentData contentData = new ContentData();
            String openId = record.getOpenId();
            LambdaQueryWrapper<ChatUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ChatUser::getOpenid, openId);
            ChatUser chatUser = chatUserService.getOne(wrapper);
            contentData.setContentId(record.getContentId());
            contentData.setAvatarUrl(chatUser.getAvatarUrl());
            contentData.setNickName(chatUser.getNickName());
            contentData.setCreatTime(record.getGmtCreate());
            contentData.setContent(record.getContent());
            contentData.setHits(record.getHits());
            contentData.setLikeNum(record.getLikes());
            result.add(contentData);
        }
        return result;
    }

    @Override
    public ContentData getContentById(String contentId) {
        ChatDiscuss chatDiscuss = this.getById(contentId);
        ContentData contentData = new ContentData();
        LambdaQueryWrapper<ChatUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(ChatUser::getOpenid, chatDiscuss.getOpenId());
        ChatUser chatUser = chatUserService.getOne(wrapper1);
        contentData.setContentId(chatDiscuss.getContentId());
        contentData.setAvatarUrl(chatUser.getAvatarUrl());
        contentData.setNickName(chatUser.getNickName());
        contentData.setCreatTime(chatDiscuss.getGmtCreate());
        contentData.setContent(chatDiscuss.getContent());
        contentData.setHits(chatDiscuss.getHits());
        contentData.setLikeNum(chatDiscuss.getLikes());
        LambdaQueryWrapper<ChatImg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatImg::getId, contentId);
        List<ChatImg> chatImgs = chatImgService.list(wrapper);
        ArrayList<String> imageList = new ArrayList<>();
        for (ChatImg chatImg : chatImgs) {
            imageList.add(chatImg.getUrl());
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(imageList);

        contentData.setImageList(imageList);
        return contentData;
    }

    @Override
    public R saveContent(UploadContent uploadContent) {
        System.out.println(uploadContent);
        ChatDiscuss chatDiscuss = new ChatDiscuss();
        chatDiscuss.setOpenId(uploadContent.getOpenId());
        chatDiscuss.setContent(uploadContent.getContent());
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        chatDiscuss.setContentId(uuid);
        boolean save = this.save(chatDiscuss);
        if (!save) {
            return R.error();
        }
        List<String> imgs = uploadContent.getImgs();
        ArrayList<ChatImg> chatImgs = new ArrayList<>();
        for (String img : imgs) {
            ChatImg chatImg = new ChatImg();
            chatImg.setId(uuid);
            chatImg.setUrl(img);
            chatImgs.add(chatImg);
        }
        boolean b = chatImgService.saveBatch(chatImgs);
        if (!b) {
            return R.error();
        }
        return R.ok();
    }
}




