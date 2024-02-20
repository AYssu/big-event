package com.bigevent.service.impl;

import com.bigevent.mapper.CategoryMapper;
import com.bigevent.pojo.Category;
import com.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findCategoryName(String categoryName, String user) {
        return categoryMapper.findCategoryName(categoryName,user);
    }

    @Override
    public boolean addCategory(Category category) {
        categoryMapper.add(category);
        return true;
    }
}
