package com.itheima.controller;


import com.itheima.pojo.ClassOption;
import com.itheima.pojo.JobOption;
import com.itheima.pojo.Result;
import com.itheima.service.ReportService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;


    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getGenderData(){
        log.info("统计员工性别");
        List<Map> list = reportService.getGenderData();
        return Result.success(list);
    }

//    5.4统计每个班级人数
    @GetMapping("/studentCountData")
    public Result getClazzData(){
        log.info("统计班级人数");
         ClassOption classOption = reportService.getClazzData();
        return Result.success(classOption);
    }

//    5.3学员学历统计
    @GetMapping("/studentDegreeData")
    public Result getEducationData(){
        log.info("统计学历");
        List<Map> list = reportService.getEducationData();
        return Result.success(list);
    }
}
