package com.bigevent.service.impl;

import com.bigevent.mapper.UserMapper;
import com.bigevent.pojo.Result;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import com.bigevent.utils.JwtUtil;
import com.bigevent.utils.Md5Util;
import com.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        userMapper.userAdd(username,md5String);
    }

    @Override
    public Result login(String username, String password) {
        User user = findByUserName(username);
        String md5String = Md5Util.getMD5String(password);
        if (user.getPassword().equals(md5String))
        {
            Map<String,Object> map = new HashMap<>();
            map.put("id",user.getId());
            map.put("username",user.getUsername());
            String s = JwtUtil.genToken(map);
            ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
            stringStringValueOperations.set(s,s,1, TimeUnit.HOURS);
            return Result.success(s);
        }else {
            return Result.error("用户账号或密码错误");
        }
    }

    @Override
    public User findById(int uid) {
        return userMapper.findById(uid);
    }

    @Override
    public void update(User user) {
        System.out.println("update user:" + user.getUsername());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void avatar(String url) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        userMapper.avatar(url,(Integer) map.get("id"));
    }

    @Override
    public Result updatePwd(Integer id,String newPwd) {
        userMapper.updatePwd(id,Md5Util.getMD5String(newPwd));
        return Result.success();
    }
}
