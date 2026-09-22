package com.itheima.mapper;

import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<Student> pageList(String name, Integer degree, Integer clazzId);

    void add(Student student);

    @Select("select * from student where id = #{id}")
    Student selectById(Integer id);

    void update(Student student);

    void deleteByIds(List<Integer> ids);

    @Update("update student set violation_score = #{score}, violation_count=violation_count+1,update_time = now() where id = #{id}")
    void violation(Integer id, Integer score);
}
