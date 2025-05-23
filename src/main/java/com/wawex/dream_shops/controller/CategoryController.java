package com.wawex.dream_shops.controller;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.Category;
import com.wawex.dream_shops.repository.CategoryRepository;
import com.wawex.dream_shops.response.ApiResponse;
import com.wawex.dream_shops.response.CategoryApiResponse;
import com.wawex.dream_shops.service.category.ICategoryService;

@RestController
@RequestMapping("${api.prefix}/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories() {

        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("Category Found!", null));
        }

        catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Error!", null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryApiResponse> addCategory(@RequestBody Category name) {
        
        try {
            Category theCategory = categoryService.addCategory(name);
            return ResponseEntity.ok(new CategoryApiResponse("Category Added Success", theCategory));
        }

        catch (Exception e) {
            return ResponseEntity.status(CONFLICT).body(new CategoryApiResponse("Error!", null));
        }
    }

    @GetMapping("/category/{id}/category")
    public ResponseEntity<CategoryApiResponse> getCategoryById(@PathVariable Long id) {
        
        try {
            Category theCategory = categoryService.getCategoryById(id);
            return ResponseEntity.ok(new CategoryApiResponse("Found", theCategory));    
        } 

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CategoryApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{name}/category")
    public ResponseEntity<CategoryApiResponse> getCategoryByName(@PathVariable String name) {
        
        try {
            Category thCategory = categoryService.getCategoryByName(name);
            return ResponseEntity.ok(new CategoryApiResponse("Found", thCategory));    
        } 
        
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CategoryApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<CategoryApiResponse> deleteCategory(@PathVariable Long id) {
        
        try {
            categoryService.deleteCategoryById(id);
            return ResponseEntity.ok(new CategoryApiResponse("Category Deleted", null));
        } 
        
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CategoryApiResponse(e.getMessage(), null));
        }
    }

    public ResponseEntity<CategoryApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new CategoryApiResponse("Update success!", updatedCategory));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new CategoryApiResponse(e.getMessage(), null));
        }
    }
}


