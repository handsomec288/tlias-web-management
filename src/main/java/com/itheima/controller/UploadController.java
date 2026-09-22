package com.itheima.controller;


import com.itheima.pojo.Result;
import com.itheima.utils.OssUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

//    @PostMapping("/upload")
//    public Result upload(String name,Integer age, MultipartFile file) throws IOException {
//        log.info("姓名:{}",name);
//        log.info("年龄:{}",age);
//        log.info("文件名:{}",file.getOriginalFilename());
//        log.info("文件大小:{}",file.getSize());
////        1.获取原始文件名
//        String originalFilename = file.getOriginalFilename();
//
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFilename = UUID.randomUUID().toString() + extension;
//        file.transferTo(new File("D:/images/" + newFilename));
//        return Result.success();
//    }
    @Autowired
    private OssUploadUtil ossUploadUtil;


    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件名:{}",file.getOriginalFilename());
        String url = ossUploadUtil.upload(file);
        return Result.success(url);
    }



}
