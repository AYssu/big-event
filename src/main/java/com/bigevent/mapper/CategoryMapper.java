package com.bigevent.mapper;

import com.bigevent.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {

    @Select("select * from category where category_name=#{categoryName} and creat_user=#{user}")
    Category findCategoryName(String categoryName, String user);


    @Insert("insert into category(category_name=#{categoryName},)")
    void add(Category category);
}
