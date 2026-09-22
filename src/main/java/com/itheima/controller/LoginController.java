package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;

//    登录接口
    @RequestMapping
    public Result login(@RequestBody Emp emp){// @RequestBody:将请求体中的json数据，转换为java对象
               log.info("用户名:{}",emp.getUsername());
               log.info("密码:{}",emp.getPassword());
               LoginInfo loginInfo = empService.Login(emp);
               if(loginInfo!=null){
                   return Result.success(loginInfo);
               }else {
                   return Result.error("用户名或密码错误");
               }
    }

}
