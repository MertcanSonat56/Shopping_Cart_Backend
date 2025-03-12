package com.wawex.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wawex.dream_shops.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
