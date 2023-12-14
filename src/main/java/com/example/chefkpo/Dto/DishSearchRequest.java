package com.example.chefkpo.Dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class DishSearchRequest {
    private int page;
    private int elementPerPage;
    private String direction = "dsc";
    private String key;

    public Sort buildSort() {
        switch (direction) {
            case "asc":
                return Sort.by(key).ascending();
            default:
                return Sort.by(key).descending();
        }
    }
}

