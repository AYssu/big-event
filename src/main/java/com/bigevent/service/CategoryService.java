package com.bigevent.service;

import com.bigevent.pojo.Category;
import com.bigevent.pojo.Result;

import java.util.List;

public interface CategoryService {
    
    Category findCategoryName(String categoryName, int id);

    boolean addCategory(Category category);

    Result<List<Category>> findAllCategorys(Integer id);

    Category findCategoryByID(int cid);

    void update(Category category);
}
