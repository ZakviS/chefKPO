package com.example.chefkpo.Dto;

import com.example.chefkpo.Entity.DishEntity;
import lombok.Data;

import java.util.List;

@Data
public class DishResponse {

    private List<DishEntity> dishes;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
