package com.bigevent.mapper;

import com.bigevent.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,create_time,update_time) " +
            "values(#{username},#{password},now(),now())")
    void userAdd(String username, String password);

    @Select("select * from user where id=#{uid}")
    User findById(int uid);
    @Update("update user set nickname='${nickname}',email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set user_pic=#{url},update_time=now() where id = #{id}")
    void avatar(String url,Integer id);

    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(Integer id, String newPwd);
}
