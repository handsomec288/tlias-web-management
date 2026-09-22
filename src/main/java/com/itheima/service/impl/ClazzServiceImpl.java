package com.itheima.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult page(String name, LocalDate begin,LocalDate end,Integer page,Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Clazz> dataList = clazzMapper.list(name,begin,end);
        Page<Clazz> p = (Page<Clazz>) dataList;

        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public void delete(Integer id) {
        Integer count = clazzMapper.count(id);
        if(count > 0){
            throw new RuntimeException("该班级下有学生，无法删除");
        }
        clazzMapper.delete(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    @Override
    public Clazz select(Integer id) {
        return clazzMapper.select(id);


    }

    @Override
    public void updata(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updata(clazz);
    }
//    3.6查询所有班级
    @Override
    public List<Clazz> list() {
        List<Clazz> alllist = clazzMapper.alllist();

        return alllist;}
}
