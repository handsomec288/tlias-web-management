package com.itheima.controller;

import com.itheima.mapper.EmpExprMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;


//分页查询
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询员工,{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp) {

        log.info("新增员工,{}", emp);
        empService.save(emp);
        return Result.success();
    }

//        删除员工
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//            log.info("删除员工,{}", ids);
//
//            return Result.success();
//    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工,{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工信息" + id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }


    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}" + emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result select(){
        List<Emp> emp = empService.select();
        return Result.success(emp);
    }

}
