package com.wawex.dream_shops.service.product;

import java.util.List;
import com.wawex.dream_shops.model.Product;
import com.wawex.dream_shops.request.AddProductRequest;
import com.wawex.dream_shops.request.ProductUpdateRequest;

public interface IProductService {
    
    Product addProduct(AddProductRequest product);
    Product getProductById(Long id);

    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);
    
    
}
