package com.example.chefkpo.Repo;

import com.example.chefkpo.Entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepo extends JpaRepository<DishEntity, Long> {
    DishEntity findByFilename(String filename);
    Page<DishEntity> findAll(Pageable pageable);
}
