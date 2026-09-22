package com.itheima.controller;


import com.itheima.pojo.Clazz;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    @GetMapping
    public Result page(String name ,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                        @RequestParam(defaultValue = "1") Integer page ,
                        @RequestParam(defaultValue = "10")Integer pageSize){

        PageResult pageResult = clazzService.page(name,begin,end,page,pageSize);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        clazzService.delete(id);
        return Result.success();
    }

//    3.3添加班级

    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        clazzService.add(clazz);
        return Result.success();
    }

//    3.4ID查询班级
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        Clazz clazz = clazzService.select(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result updata(@RequestBody Clazz clazz){
        clazzService.updata(clazz);
        return Result.success();
    }

//    3.6查询所有班级
    @GetMapping("/list")
    public Result list() {
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }

}
