package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping("/depts")
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer id) {
        System.out.println("删除部门数据" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        System.out.println("新增部门数据" + dept);
        deptService.add(dept);
        return Result.success();
    }
//    根据id查询部门
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable Integer id){
        System.out.println("根据id查询部门"+id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        System.out.println("修改部门数据" + dept);
        deptService.update(dept);
        return Result.success();
    }

}
