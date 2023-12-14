package com.example.chefkpo.Dto;

import com.example.chefkpo.Entity.DishEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishEntityDto {
    List<DishEntity> dishEntityList;
}
