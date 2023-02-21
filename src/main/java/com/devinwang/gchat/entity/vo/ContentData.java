package com.devinwang.gchat.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ContentData {
    private String contentId;
    private String avatarUrl;
    private String nickName;
    private Date creatTime;
    private String content;
    private Integer hits;
    private Integer likeNum;
    private List<String> imageList;
}
