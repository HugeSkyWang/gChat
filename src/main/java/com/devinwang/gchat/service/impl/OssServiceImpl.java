package com.devinwang.gchat.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.devinwang.gchat.service.OssService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.devinwang.gchat.util.ConstantPropertiesUtils.*;

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ACCESS_KEY_ID;
        String accessKeySecret = ACCESS_KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();

            // 获取上传文件名称
            String fileName = file.getOriginalFilename();

            //1. 在文件夹里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid + fileName;

            //2. 把文件按照日期进行分类
            // 获取当前日期 2022/11/28
            String dataPath = new DateTime().toString("yyyy/MM/dd");

            // 拼接
            // 2022/11/12/efdsfas01.jpg
            fileName = dataPath + "/" +fileName;
            System.out.println("fileName=========================>" + fileName);
            // 创建PutObject请求。
            // 第一个参数 Bucket名称
            // 第二个参数 上传到oss文件路径和文件名称    aa/bb/1.jpg
            // 第三个参数 上传文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSSClient
            ossClient.shutdown();
            // 把上传之后的路径进行返回
            // 需要把上传到阿里云oss路径手动拼接出来
            //https://devin-edu.oss-cn-zhangjiakou.aliyuncs.com/1.jpg
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
