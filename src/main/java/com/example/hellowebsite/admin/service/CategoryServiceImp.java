package com.example.hellowebsite.admin.service;

import com.example.hellowebsite.admin.dto.CategoryDto;
import com.example.hellowebsite.admin.entity.Category;
import com.example.hellowebsite.admin.model.CategoryInput;
import com.example.hellowebsite.admin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;

    private Sort getSortBySortValueDesc() {
        return Sort.by(Sort.Direction.DESC, "sortValue");
    }


    @Override
    public List<CategoryDto> list() {
        List<Category> categories = categoryRepository.findAll(getSortBySortValueDesc());
        return CategoryDto.of(categories);
    }


    @Override
    public boolean add(String categoryName) {
        Category category = Category.builder()
                .categoryName(categoryName)
                .usingYn(true)
                .sortValue(0)
                .build();
        categoryRepository.save(category);
        return true;
    }

    @Override
    public boolean update(CategoryInput categoryInput) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryInput.getId());
        if(optionalCategory.isPresent()){
            Category category = optionalCategory.get();

            category.setCategoryName(categoryInput.getCategoryName());
            category.setSortValue(categoryInput.getSortValue());
            category.setUsingYn(categoryInput.isUsingYn());
            categoryRepository.save(category);
        }
        return true;
    }

    @Override
    public boolean del(long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
