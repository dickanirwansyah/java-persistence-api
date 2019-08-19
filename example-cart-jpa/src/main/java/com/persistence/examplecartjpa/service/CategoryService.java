package com.persistence.examplecartjpa.service;

import com.persistence.examplecartjpa.entity.Category;
import com.persistence.examplecartjpa.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

}
