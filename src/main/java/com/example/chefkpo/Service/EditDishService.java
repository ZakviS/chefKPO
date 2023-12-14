package com.example.chefkpo.Service;

import com.example.chefkpo.Entity.DishEntity;
import com.example.chefkpo.Model.Ingredient;
import com.example.chefkpo.Model.Dish;
import com.example.chefkpo.Repo.DishRepo;
import com.example.chefkpo.Util.BinaryFileUtil;
import com.example.chefkpo.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditDishService {

    @Autowired
    private DishRepo dishRepo;

    public void editDish(Dish dish) {
        List<Ingredient> ingredientList = dish.getListIngredient();
        DishEntity dishEntity =  dishRepo.findByFilename(dish.getFilename());
        dishEntity.setDescriptions(dish.getDescriptions());
        dishRepo.save(dishEntity);
        FileUtil.saveToFile(ingredientList, "dish\\" + dish.getFilename());
    }

    public void editBinaryDish(Dish dish) {
        List<Ingredient> ingredientList = dish.getListIngredient();
        DishEntity dishEntity =  dishRepo.findByFilename(dish.getFilename());
        dishEntity.setDescriptions(dish.getDescriptions());
        dishRepo.save(dishEntity);
        BinaryFileUtil.writeIngredientsToBinaryFile(ingredientList, "dish\\" + dish.getFilename());
    }

}
