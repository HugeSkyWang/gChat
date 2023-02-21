package com.devinwang.gchat.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class UploadContent {
    private String openId;
    private String content;
    private List<String> imgs;
}
