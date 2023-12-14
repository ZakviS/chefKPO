package com.example.chefkpo.Service;

import com.example.chefkpo.Entity.DishEntity;
import com.example.chefkpo.Model.Dish;
import com.example.chefkpo.Model.Ingredient;
import com.example.chefkpo.Repo.DishRepo;
import com.example.chefkpo.Util.BinaryFileUtil;
import com.example.chefkpo.Util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateDishService {

    @Autowired
    private DishRepo dishRepo;

    public void createDish(Dish dish) {
        List<Ingredient> ingredientList = dish.getListIngredient();
        if(ingredientList.isEmpty()){
            throw new RuntimeException("ingredientList is null");
        } else{
            FileUtil.saveToFile(ingredientList, "dish\\" + dish.getFilename());
            DishEntity dishEntity = new DishEntity();
            dishEntity.setFilename(dish.getFilename());
            dishEntity.setFilenameWithoutExt(FileUtil.getFileNameWithoutExtension(dish.getFilename()));
            dishEntity.setDescriptions(dish.getDescriptions());
            dishRepo.save(dishEntity);
        }
    }

    public void createBinaryDish(Dish dish) {
        List<Ingredient> ingredientList = dish.getListIngredient();
        if(ingredientList.isEmpty()){
            throw new RuntimeException("ingredientList is null");
        } else {
            BinaryFileUtil.writeIngredientsToBinaryFile(ingredientList, "dish\\" + dish.getFilename());
            DishEntity dishEntity = new DishEntity();
            dishEntity.setFilename(dish.getFilename());
            dishEntity.setFilenameWithoutExt(FileUtil.getFileNameWithoutExtension(dish.getFilename()));
            dishRepo.save(dishEntity);
        }
    }

}
