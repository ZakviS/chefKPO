package com.example.chefkpo.Service;

import com.example.chefkpo.Repo.DishRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class DeleteDishService {

    @Autowired
    private DishRepo dishRepo;

    private String dishStorage = "C:\\Users\\ZakviS\\Desktop\\chefKPO\\dish\\";

    public void deleteFile(String filename) throws IOException {
        Path dishStoragePath = Paths.get(dishStorage + filename);
        Files.delete(dishStoragePath);
        dishRepo.delete(dishRepo.findByFilename(filename));
    }
}
