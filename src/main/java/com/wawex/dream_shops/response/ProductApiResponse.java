package com.wawex.dream_shops.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductApiResponse {
    
    private String message;
    private Object data;
}
