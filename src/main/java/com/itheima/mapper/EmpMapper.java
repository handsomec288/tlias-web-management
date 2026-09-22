package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

//    原始分页查询方式——————————————————————————————————————————————————
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);



    public List<Emp> list(  EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,name, gender, phone, job, salary, image, entry_date, create_time, update_time) values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{createTime},#{updateTime})")
    void insert(Emp emp);



    void deleteByIds(List<Integer> ids);

    void deleteExprByIds(List<Integer> ids);

    void getInfo(Integer id);

    Emp getByid(Integer id);

    void updateById(Emp emp);

    List<Map<String,Object>> countEmpJobData();


    List<Map> getGenderData();

    List<Emp> select();


    List<Map<String, Object>> getClazzData();

    List<Map> getEducationData();

    /**
     * 根据用户名和密码查询员工信息
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}
