package com.devinwang.gchat.controller;

import com.devinwang.gchat.commonutils.R;
import com.devinwang.gchat.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("oss")
public class OssController {

    @Autowired
    private OssService ossService;

    // 上传图片
    @PostMapping("/upload")
    public R uploadOssFile(@RequestParam("file") MultipartFile file) {
        // 获取上传文件
        // 返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
