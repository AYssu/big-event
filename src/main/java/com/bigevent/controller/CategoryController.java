package com.bigevent.controller;

import com.bigevent.pojo.Category;
import com.bigevent.pojo.Result;
import com.bigevent.service.CategoryService;
import com.bigevent.utils.ThreadLocalUtil;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/category")
    public Result add(@RequestBody Category category)
    {
        Map<String, Object> map = ThreadLocalUtil.get();

        Category category1 = categoryService.findCategoryName(category.getCategoryName(),(String)map.get("user"));
        if (category != null)
        {
            return Result.error("分类已存在");
        }
        boolean a = categoryService.addCategory(category);
        return Result.success();
    }
}
