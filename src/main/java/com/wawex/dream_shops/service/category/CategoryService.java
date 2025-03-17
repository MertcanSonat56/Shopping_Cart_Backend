package com.wawex.dream_shops.service.category;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.wawex.dream_shops.exceptions.AlreadyExistsException;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.Category;
import com.wawex.dream_shops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService() {
        this.categoryRepository = null;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
        .map(categoryRepository::save)
        .orElseThrow(() -> new AlreadyExistsException(category.getName()+"already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {

        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("Category not Found!"));
    }

    @Override
    public void deleteCategoryById(Long id) {

        categoryRepository.findById(id)
          .ifPresentOrElse(categoryRepository::delete, () -> {
            throw new ResourceNotFoundException("Category not found");
          });   
    }

}


