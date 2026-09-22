package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {


    PageResult page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    void delete(Integer id);

    void add(Clazz clazz);

    Clazz select(Integer id);

    void updata(Clazz clazz);

    List<Clazz> list();
}
