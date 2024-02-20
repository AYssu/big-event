package com.bigevent.controller;

import com.bigevent.pojo.Result;
import com.bigevent.pojo.User;
import com.bigevent.service.UserService;
import com.bigevent.utils.Md5Util;
import com.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{4,16}$") String username ,@Pattern(regexp = "^\\S{4,16}$") String password)
    {
        User user = userService.findByUserName(username);
        if (user == null) {
            //用户不存在 注册用户
            userService.register(username, password);
            return Result.success();
        } else {

            return Result.error("用户名被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{4,16}$") String username,@Pattern(regexp = "^\\S{4,16}$") String password)
    {
        User user = userService.findByUserName(username);
        if (user==null)
        {
            return Result.error("用户不存在，请先注册");
        }

        return userService.login(username,password);
    }

    @GetMapping("/userInfo")
    public Result<User> userinfo()
    {
        Map<String ,Object> map = ThreadLocalUtil.get();
        System.out.println("Thread uid："+map.get("id"));
        User user = userService.findById((int)map.get("id"));
        if (user==null)
        {
            return Result.error("用户不存在");
        }
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user)
    {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/avatar")
    public Result avatar(@RequestParam(name = "avatar") @URL String url)
    {
        userService.avatar(url);
        return Result.success();
    }

    @PatchMapping("updatePwd")
    public Result updatePwd(@RequestBody @NotEmpty Map<String,String> map)
    {
        String oldPwd = map.get("old_pwd");
        String newPwd = map.get("new_pwd");
        String rePwd = map.get("re_pwd");

        if (!newPwd.equals(rePwd))
        {
            return Result.error("两次密码不一致");
        }
        Map<String,Object> map1 = ThreadLocalUtil.get();
        User user = userService.findById((Integer)map1.get("id"));
        if (user==null)
        {
            return Result.error("用户不存在");
        }
        String md5String = Md5Util.getMD5String(oldPwd);
        if (!md5String.equals(user.getPassword()))
        {
            return Result.error("原密码错误！");
        }

        return userService.updatePwd(user.getId(),newPwd);
    }

}
