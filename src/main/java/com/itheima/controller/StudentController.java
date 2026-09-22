package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //    4.1学员列表查询
    @GetMapping
    public Result listPage(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        log.info("查询学员列表，name={},degree={},clazzId={},page={},pageSize={}", name, degree, clazzId, page, pageSize);
        PageResult pageResult = studentService.listPage(name, degree, clazzId, page, pageSize);
        return Result.success(pageResult);
    }

    //    11.3.3接口4.3添加学员
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学员，student={}", student);
        studentService.add(student);
        return Result.success();
    }

    //    11.3.4根据id查询学生4.4
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询学生，id={}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

//    3.5修改学院信息

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学院信息，student={}", student);
        studentService.update(student);
        return Result.success();
    }


//    4.2删除学员

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学员，ids={}", ids);
        studentService.delete(ids);
        return Result.success();
    }

//    4.6违纪学生处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("违纪学生处理，id={},score={}",id,score);
        studentService.violation(id,score);
        return Result.success();

    }
}