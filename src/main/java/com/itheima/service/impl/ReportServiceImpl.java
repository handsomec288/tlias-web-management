package com.itheima.service.impl;


import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.ClassOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Override
    public JobOption getEmpJobData() {
//        1.调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap ->dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap ->dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    @Override
    public List<Map> getGenderData() {
        List<Map> list = empMapper.getGenderData();
        return list;
    }

//    班级人数柱状图
    @Override
    public ClassOption getClazzData() {
        List<Map<String, Object>> list = empMapper.getClazzData();
        List<Object> clazzList = list.stream().map(dataMap ->dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap ->dataMap.get("num")).toList();
        return new ClassOption(clazzList,dataList);
    }
//    学历统计图


    @Override
    public List<Map> getEducationData() {
        List<Map> list = empMapper.getEducationData();
        return list;
    }
}
