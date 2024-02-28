package com.bigevent.service.impl;

import com.bigevent.mapper.CategoryMapper;
import com.bigevent.pojo.Category;
import com.bigevent.pojo.Result;
import com.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryName(String categoryName, int id) {
        return categoryMapper.findCategoryName(categoryName,id);
    }

    @Override
    public boolean addCategory(Category category) {
        categoryMapper.add(category);
        return true;
    }

    @Override
    public Result<List<Category>> findAllCategorys(Integer id) {
        List<Category> list = categoryMapper.findAllCategorys(id);
        if (list==null)
        {
            return Result.error("用户无分类数据");
        }

        return Result.success(list);
    }

    @Override
    public Category findCategoryByID(int cid) {
        return categoryMapper.findCategoryByID(cid);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delectCategoryByID(int i) {
        categoryMapper.delectCategoryByID(i);
    }
}
