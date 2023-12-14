package com.example.chefkpo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish {
    String filename;
    List<Ingredient> listIngredient;
    String descriptions;
}
