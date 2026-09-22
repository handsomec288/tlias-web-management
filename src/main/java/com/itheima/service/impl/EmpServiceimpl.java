package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceimpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpLogService empLogService;

    private EmpExprMapper empExprMapper;
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//
//        Long total = empMapper.count();
//
//
//        Integer start = (page - 1) *pageSize;
//        List<Emp> rows = empMapper.list(page, pageSize);
//
//
//        return new PageResult<Emp>(total, rows);
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        List<Emp> empList = empMapper.list(empQueryParam);


        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }



    @Transient
    @Override
    public void save(Emp emp) {
        try {
            //        1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

//        2.保存员工工作信息
            List<EmpExpr> emprList = emp.getEmprList();
            if (!CollectionUtils.isEmpty(emprList)) {
//            遍历集合给empId赋值
                emprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(emprList);
            }
        }
        finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
        empMapper.deleteExprByIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getByid(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
//        1.根据ID修改员工信息
        empMapper.updateById(emp);

//        2.1根据id删除工作经历
        empMapper.deleteExprByIds(Arrays.asList(emp.getId()));
//        2.2更新员工工作经历
        List<EmpExpr> exprList = emp.getEmprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    @Override
    public List<Emp> select() {
        List<Emp> emp = empMapper.select();
        return emp;
    }

//    返回登录信息
    @Override
    public LoginInfo Login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            //生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",empLogin.getId());
            claims.put("username",empLogin.getUsername());
            String jwt = JwtUtils.generateToken(claims);

            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }
}
