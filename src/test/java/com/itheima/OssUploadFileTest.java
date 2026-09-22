package com.itheima;

import com.aliyun.sdk.service.oss2.OSSClient;
import com.aliyun.sdk.service.oss2.OSSClientBuilder;
import com.aliyun.sdk.service.oss2.models.PutObjectRequest;
import com.aliyun.sdk.service.oss2.models.PutObjectResult;
import com.aliyun.sdk.service.oss2.transport.BinaryData;
import com.aliyun.sdk.service.oss2.credentials.EnvironmentVariableCredentialsProvider;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class OssUploadFileTest {
    public static void main(String[] args) {
        // ========== 这里修改成你自己的信息 ==========
        String region = "cn-beijing"; // bucket对应的地域
        String bucket = "java-ai288";
        String key = "myphoto.jpg"; // OSS里面保存的文件名，可以写目录：images/myphoto.jpg
        String localFilePath = "D:\\images\\1.jpg"; // 本地电脑文件路径（Windows用\\）
        // ==========================================

        EnvironmentVariableCredentialsProvider provider = new EnvironmentVariableCredentialsProvider();

        OSSClientBuilder clientBuilder = OSSClient.newBuilder()
                .credentialsProvider(provider)
                .region(region);

        try (OSSClient client = clientBuilder.build()) {
            // 读取本地文件
            Path path = Paths.get(localFilePath);
            BinaryData binaryData = BinaryData.fromBytes(Files.readAllBytes(path));

            PutObjectRequest request = PutObjectRequest.newBuilder()
                    .bucket(bucket)
                    .key(key)
                    .body(binaryData)
                    .build();

            PutObjectResult result = client.putObject(request);
            System.out.println("✅ 文件上传成功！");
            System.out.printf("状态码：%d, 请求ID：%s, eTag:%s%n",
                    result.statusCode(), result.requestId(), result.eTag());
        } catch (Exception e) {
            System.out.println("❌ 上传失败");
            e.printStackTrace();
        }
    }
}
