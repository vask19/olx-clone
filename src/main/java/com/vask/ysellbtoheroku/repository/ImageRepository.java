package com.vask.ysellbtoheroku.repository;
import com.vask.ysellbtoheroku.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
}
