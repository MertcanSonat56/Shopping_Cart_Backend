package com.wawex.dream_shops.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;
import com.wawex.dream_shops.DreamShopsApplication;
import com.wawex.dream_shops.dto.ImageDto;
import com.wawex.dream_shops.exceptions.ResourceNotFoundException;
import com.wawex.dream_shops.model.Image;
import com.wawex.dream_shops.repository.CategoryRepository;
import com.wawex.dream_shops.repository.ImageRepository;
import com.wawex.dream_shops.response.ApiResponse;
import com.wawex.dream_shops.response.ImageApiResponse;
import com.wawex.dream_shops.service.category.CategoryService;
import com.wawex.dream_shops.service.image.IImageService;

@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService, CategoryRepository categoryRepository, CategoryService categoryService, DreamShopsApplication dreamShopsApplication, ImageRepository imageRepository) {
        this.imageService = imageService;
        
    }

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files,
            @RequestParam Long productId) {

        try {
            List<ImageDto> imageDtos = imageService.saveImages(productId, files);
            return ResponseEntity.ok(new ApiResponse("Upload success!", imageDtos));
        }

        catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!", null));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        
        ByteArrayResource resource = new ByteArrayResource(
                image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }

    @PutMapping("/image/{image}/update")
    public ResponseEntity<ImageApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file) {

        try {
            Image image = imageService.getImageById(imageId);

            if (image != null) {
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ImageApiResponse("Update success!", image));
            }
        } 
        
        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ImageApiResponse(e.getMessage(), null));
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ImageApiResponse("Update failed!", null));
    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ImageApiResponse> deleteImage(@PathVariable Long imageId) {
        
        try {
            Image image = imageService.getImageById(imageId);
            
            if(image != null) {
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok(new ImageApiResponse("Delete success!", image));
            }
        }

        catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ImageApiResponse(e.getMessage(), null));
        }

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ImageApiResponse("Delete failed!", null));
    }
}


