package com.example.hellowebsite.admin.service;

import com.example.hellowebsite.admin.dto.CategoryDto;
import com.example.hellowebsite.admin.entity.Category;
import com.example.hellowebsite.admin.model.CategoryInput;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> list();
    boolean add(String categoryName);
    boolean update(CategoryInput categoryInput);
    boolean del(long id);
}
