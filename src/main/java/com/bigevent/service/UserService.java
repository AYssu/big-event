package com.bigevent.service;

import com.bigevent.pojo.Result;
import com.bigevent.pojo.User;


public interface UserService {
    User findByUserName(String username);

    void register(String username, String password);

    Result login(String username, String password);

    User findById(int uid);

    void update(User user);

    void avatar(String url);


    Result updatePwd(Integer id, String newPwd);
}
