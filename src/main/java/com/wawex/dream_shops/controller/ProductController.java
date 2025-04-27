package com.wawex.dream_shops.controller;

import java.util.List;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.wawex.dream_shops.dto.ProductDto;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.Product;
import com.wawex.dream_shops.repository.CategoryRepository;
import com.wawex.dream_shops.request.AddProductRequest;
import com.wawex.dream_shops.request.ProductUpdateRequest;
import com.wawex.dream_shops.response.ApiResponse;
import com.wawex.dream_shops.response.ProductApiResponse;
import com.wawex.dream_shops.service.category.CategoryService;
import com.wawex.dream_shops.service.product.IProductService;

@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService, CategoryController categoryController, CategoryRepository categoryRepository, CategoryService categoryService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {

        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);

        return ResponseEntity.ok(new ApiResponse("success", null));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<ProductApiResponse> getProductById(@PathVariable Long productId) {
        
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDto(product);

            return ResponseEntity.ok(new ProductApiResponse("success", null));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ProductApiResponse("Error", null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ProductApiResponse> addProduct(@RequestBody AddProductRequest product) {
       
       try {
           Product theProduct = productService.addProduct(product);
           return ResponseEntity.ok(new ProductApiResponse("Add product success", theProduct));
       }

       catch (Exception e) {
           return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ProductApiResponse("Error!", null));
       }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ProductApiResponse> updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable Long productId) {
        
        try {
            Product theProduct = productService.updateProduct(request, productId);
            return ResponseEntity.ok(new ProductApiResponse("Update product success!", theProduct));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ProductApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/product/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete product success!", null));
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ProductApiResponse> getProductByBrandAndName(@PathVariable String brandName, @PathVariable String productName) {
        
        try {
            List<Product> products = productService.getProductsByBrandAndName(brandName, brandName);
            
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ProductApiResponse("No products found!", null));
            }

            List<ProductDto> convertedProducts = productService.getConvertedProducts(products);

            return ResponseEntity.ok(new ProductApiResponse("success", null));
        }

        catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ProductApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category, brand);

            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found!", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", null));
        }

        catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name) {
        
        try {
            List<Product> products = productService.getProductsByName(name);
           
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found!", null));
            }

            return ResponseEntity.ok(new ApiResponse("success", null));
        }

        catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand) {
        
        try {
            List<Product> products = productService.getProductsByBrand(brand);

            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found!", null));
           }

           return ResponseEntity.ok(new ApiResponse("success", null));
        }
        
        catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category) {
        
        try {
            List<Product> products = productService.getProductsByCategory(category);
            
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No products found", null)); 
            }

            return ResponseEntity.ok(new ApiResponse("success", null));
        }

        catch(Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand, @RequestParam String name) {
    
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            return ResponseEntity.ok(new ApiResponse("success", null));
        }

        catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }

    }
}







