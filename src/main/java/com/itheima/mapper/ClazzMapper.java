package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {



    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    @Delete("delete from clazz where clazz.id = #{id}")
    void delete(Integer id);


    void add(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz select(Integer id);

    @Update("update clazz\n" +
            "    SET name = #{name},\n" +
            "        room = #{room},\n" +
            "        begin_date = #{beginDate},\n" +
            "        end_date = #{endDate},\n" +
            "        master_id = #{masterId},\n" +
            "        subject = #{subject},\n" +
            "        update_time = #{updateTime}\n" +
            "    WHERE id = #{id}")
    void updata(Clazz clazz);

    @Select("select count(*) from student where clazz_id = #{id}")
    Integer count(Integer id);

    List<Clazz> alllist();
}
