package com.devinwang.gchat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatStar;
import com.devinwang.gchat.service.ChatStarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/star")
@RestController
public class ChatStarController {
    @Autowired
    private ChatStarService starService;

    @PostMapping("/addStar")
    public R addStar(@RequestParam(value = "contentId") String contentId, @RequestParam(value = "openId") String openId) {
        return starService.addStar(contentId, openId);
    }

    @PostMapping("/deleteStar")
    public R deleteStar(@RequestParam(value = "contentId") String contentId, @RequestParam(value = "openId") String openId) {
        return starService.deleteStar(contentId, openId);
    }

    @PostMapping("/isLike")
    public R isLike(@RequestParam(value = "contentId") String contentId, @RequestParam(value = "openId") String openId) {
        LambdaQueryWrapper<ChatStar> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ChatStar::getContentId, contentId)
                .eq(ChatStar::getOpenId, openId);
        ChatStar one = starService.getOne(lambdaQueryWrapper);
        System.out.println("是否点赞==============================>");
        System.out.println(one);
        if (one == null) {
            return R.ok().data("isLike", 0);
        } else {
            return R.ok().data("isLike", 1);
        }
    }
}
