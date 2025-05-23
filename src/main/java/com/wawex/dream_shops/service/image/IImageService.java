package com.wawex.dream_shops.service.image;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import com.wawex.dream_shops.dto.ImageDto;
import com.wawex.dream_shops.model.Image;


public interface IImageService {

    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
    void updateImage(MultipartFile file, Long imageId);
    
}
