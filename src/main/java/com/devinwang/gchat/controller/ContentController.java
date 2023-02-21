package com.devinwang.gchat.controller;

import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.entity.ChatDiscuss;
import com.devinwang.gchat.entity.vo.ContentData;
import com.devinwang.gchat.entity.vo.UploadContent;
import com.devinwang.gchat.service.ChatDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("content")
public class ContentController {
    @Autowired
    private ChatDiscussService discussService;

    @PostMapping("/save")
    public R saveContent(@RequestBody UploadContent uploadContent) {
        return discussService.saveContent(uploadContent);
    }

    @PostMapping("/getpage")
    public R getPageData(@RequestParam(value = "num") Integer num, @RequestParam(value = "page") Integer page) {
        ArrayList<ContentData> list = discussService.getPageData(num, page);
        System.out.println(list);
        return R.ok().data("list", list);
    }

    @GetMapping("/getOneContent/{contentId}")
    public R getContentById(@PathVariable String contentId) {
        ContentData contentData = discussService.getContentById(contentId);
        return R.ok().data("info", contentData);
    }

    @PostMapping("/addHits")
    public R addHits(@RequestParam(value = "contentId") String contentId) {
        ChatDiscuss chatDiscuss = discussService.getById(contentId);
        chatDiscuss.setHits(chatDiscuss.getHits() + 1);
        boolean b = discussService.updateById(chatDiscuss);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}
