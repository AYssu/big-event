package com.bigevent.mapper;

import com.bigevent.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from category where category_name=#{categoryName} and create_user=#{id}")
    Category findCategoryName(String categoryName, int id);


    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) " +
            "values (#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void add(Category category);

    @Select("select * from category where create_user=#{id}")
    List<Category> findAllCategorys(Integer id);

    @Select("select * from category where id=#{cid}")
    Category findCategoryByID(int cid);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias},update_time=now() where id = #{id}")
    void update(Category category);

    @Delete("delete from category where id=#{i}")
    void delectCategoryByID(int i);
}
