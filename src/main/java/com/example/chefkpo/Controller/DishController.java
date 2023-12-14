package com.example.chefkpo.Controller;

import com.example.chefkpo.Dto.DishDto;
import com.example.chefkpo.Dto.DishResponse;
import com.example.chefkpo.Dto.DishSearchRequest;
import com.example.chefkpo.Model.Dish;
import com.example.chefkpo.Service.CreateDishService;
import com.example.chefkpo.Service.DeleteDishService;
import com.example.chefkpo.Service.EditDishService;
import com.example.chefkpo.Service.GetDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private CreateDishService createDishService;
    @Autowired
    private GetDishService getDishService;
    @Autowired
    private EditDishService editDishService;
    @Autowired
    private DeleteDishService deleteDishService;

    @PostMapping("/createDish")
    public ResponseEntity<?> createDish(@RequestBody Dish dish) {
        createDishService.createDish(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getDish")
    public ResponseEntity<DishDto> getDish(@RequestParam String filename) {
        DishDto dishDto = getDishService.getDish(filename);
        return new ResponseEntity<>(dishDto, HttpStatus.OK);
    }

    @PutMapping("/editDish")
    public ResponseEntity<?> editDish(@RequestBody Dish dish) {
        editDishService.editDish(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/createBinaryDish")
    public ResponseEntity<?> createBinaryDish(@RequestBody Dish dish) {
        createDishService.createBinaryDish(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getBinaryDish")
    public ResponseEntity<DishDto> getBinaryDish(@RequestParam String filename) {
        DishDto dishDto = getDishService.getBinaryDish(filename);
        return new ResponseEntity<>(dishDto, HttpStatus.OK);
    }

    @PutMapping("/editBinaryDish")
    public ResponseEntity<?> editBinaryDish(@RequestBody Dish dish) {
        editDishService.editBinaryDish(dish);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getAll")
    public ResponseEntity<DishResponse> getAll(@RequestBody DishSearchRequest dishSearchRequest) {
        DishResponse dishResponse = getDishService.getAll(dishSearchRequest);
        return new ResponseEntity<>(dishResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam String filename) throws IOException {
        deleteDishService.deleteFile(filename);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
