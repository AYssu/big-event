package com.bigevent.service;

import com.bigevent.pojo.Category;

public interface CategoryService {
    
    Category findCategoryName(String categoryName, String user);

    boolean addCategory(Category category);
}
