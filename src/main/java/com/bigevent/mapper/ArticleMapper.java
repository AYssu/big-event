package com.bigevent.mapper;

import com.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time) values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void add(Article article);


    List<Article> list(Integer id, Integer categoryId, String state);

    @Update("update article set title='${title}',content=#{content},cover_img=#{coverImg},state=#{state},update_time=now(),category_id=#{categoryId} where id=#{id}")
    void edit(Article article);

    @Delete("delete from article where id=#{id}")
    void delete(Integer id);
}
