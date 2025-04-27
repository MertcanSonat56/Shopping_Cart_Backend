package com.wawex.dream_shops.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderItemDto {
    
    private Long productId;

    private String productName;
    private String productBrand;

    private int quantity;
    private BigDecimal price;

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}



