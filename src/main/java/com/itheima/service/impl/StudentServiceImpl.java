package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Override
    public PageResult listPage(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Student> studentList = studentMapper.pageList(name,degree,clazzId);
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public void add(Student student) {
//        1.封装最后修改时间
        student.setUpdateTime(LocalDateTime.now());
        student.setCreateTime(LocalDateTime.now());
        studentMapper.add(student);
    }

//    4.4id查询学生信息
    @Override
    public Student getById(Integer id) {
        return studentMapper.selectById(id);
    }

//    修改学员信息
    @Override
    public void update(Student student) {
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

//    4.6违纪处理
    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id,score);
    }
}
