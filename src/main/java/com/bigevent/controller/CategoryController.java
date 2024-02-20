package com.bigevent.controller;

import com.bigevent.pojo.Category;
import com.bigevent.pojo.Result;
import com.bigevent.service.CategoryService;
import com.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated Category category)
    {
        Map<String, Object> map = ThreadLocalUtil.get();

        Category category1 = categoryService.findCategoryName(category.getCategoryName(),(Integer)map.get("id"));
        if (category1 != null)
        {
            return Result.error("分类已存在");
        }
        category.setCreateUser((Integer) map.get("id"));

        categoryService.addCategory(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list()
    {
        Map<String, Object> map = ThreadLocalUtil.get();

        return categoryService.findAllCategorys((Integer)map.get("id"));
    }

    @GetMapping("/detail")
    public Result<Category> detail(int id)
    {
        Category category = categoryService.findCategoryByID(id);
        if (category==null)
        {
            return Result.error("异常分类");
        }
        return Result.success(category);
    }
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category)
    {
        Category category1 = categoryService.findCategoryByID(category.getId());
        if (category1==null)
        {
            return Result.error("分类不存在");
        }
        categoryService.update(category);
        return Result.success();
    }
}
