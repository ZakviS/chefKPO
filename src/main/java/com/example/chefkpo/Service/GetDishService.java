package com.example.chefkpo.Service;

import com.example.chefkpo.Dto.*;
import com.example.chefkpo.Entity.DishEntity;
import com.example.chefkpo.Model.Ingredient;
import com.example.chefkpo.Repo.DishRepo;
import com.example.chefkpo.Util.BinaryFileUtil;
import com.example.chefkpo.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetDishService {

    @Autowired
    private DishRepo dishRepo;

    public DishDto getDish(String filename) {
        List<IngredientDto> loadedIngredients = FileUtil.loadFromFile("dish\\" + filename);
        return new DishDto(loadedIngredients);
    }

    public DishDto getBinaryDish(String filename) {
        List<Ingredient> loadedIngredients = BinaryFileUtil.readIngredientsFromBinaryFile("dish\\" + filename);
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        for (Ingredient ingredient : loadedIngredients) {
            IngredientDto ingredientDto = new IngredientDto(ingredient.getName(), ingredient.getWeight(), ingredient.getCalories());
            ingredientDtoList.add(ingredientDto);
        }
        return new DishDto(ingredientDtoList);
    }

    public DishResponse getAll(DishSearchRequest dishSearchRequest) {

        Page<DishEntity> dishes;
        dishes = dishRepo.findAll(PageRequest.of(dishSearchRequest.getPage(), dishSearchRequest.getElementPerPage(), dishSearchRequest.buildSort()));

        List<DishEntity> dishEntityList = pageInModel(dishes);
        DishResponse dishResponse = new DishResponse();
        dishResponse.setDishes(dishEntityList);
        dishResponse.setPageNo(dishes.getNumber());
        dishResponse.setPageSize(dishes.getSize());
        dishResponse.setTotalElements(dishes.getTotalElements());
        dishResponse.setTotalPages(dishes.getTotalPages());
        dishResponse.setLast(dishes.isLast());

        return dishResponse;
    }

    public List<DishEntity> pageInModel(Page<DishEntity> dishes){
        try{
            List<DishEntity> dishEntities = dishes.stream()
                    .map(dish    -> {
                        DishEntity dto = new DishEntity();
                        dto.setId(dish.getId());
                        dto.setFilename(dish.getFilename());
                        dto.setFilenameWithoutExt(dish.getFilenameWithoutExt());
                        dto.setDescriptions(dish.getDescriptions());
                        return dto;
                    })
                    .collect(Collectors.toList());
            return dishEntities;
        } catch (Exception e){
            throw e;
        }
    }

}
