package com.itheima.service;


import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;

import java.util.List;

public interface StudentService {


    PageResult listPage(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void violation(Integer id, Integer score);
}
