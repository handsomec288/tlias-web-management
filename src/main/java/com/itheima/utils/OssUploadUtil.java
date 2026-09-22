package com.itheima.utils;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.models.PutObjectResult;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Component
public class OssUploadUtil {

    @Autowired
    private AliyunOSSProperties aliyunOSSProperties;

//    // 常量，改成你自己的
//    @Value("${aliyun.oss.REGION}")
//    private String REGION;
//    @Value("${aliyun.oss.BUCKET_NAME}")
//    private  String BUCKET_NAME;




    /**
     * SpringBoot文件上传，接收前端MultipartFile文件
     * @param file 前端上传的文件
     * @return 文件在OSS的访问地址
     */
    public String upload(MultipartFile file) throws Exception {
        String REGION = aliyunOSSProperties.getREGION();
        String BUCKET_NAME = aliyunOSSProperties.getBUCKET_NAME();

        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();
        // 取出文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 生成不重复的key，防止文件名重复覆盖
        String key = "images/" + UUID.randomUUID() + suffix;

        EnvironmentVariableCredentialsProvider provider = new EnvironmentVariableCredentialsProvider();
        OSSClient client = null;
        try {
            client = OSSClient.newBuilder()
                    .credentialsProvider(provider)
                    .region(REGION)
                    .build();

            PutObjectRequest request = PutObjectRequest.newBuilder()
                    .bucket(BUCKET_NAME)
                    .key(key)
                    .body(BinaryData.fromBytes(file.getBytes()))
                    .build();

            PutObjectResult result = client.putObject(request);
            // 拼接文件访问URL
            String url = "https://" + BUCKET_NAME + ".oss-cn-beijing.aliyuncs.com/" + key;
            return url;
        } catch (Exception e) {
            throw new RuntimeException("OSS上传失败", e);
        } finally {
            // 手动关闭客户端
            if(client != null){
                client.close();
            }
        }
    }
}
