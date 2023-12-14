package com.example.chefkpo.Util;

import com.example.chefkpo.Model.Ingredient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class BinaryFileUtil {
    private static final Pattern pattern = Pattern.compile("Name: (.*), calories: (.*), weight: (.*)");

    public static void writeIngredientsToBinaryFile(List<Ingredient> ingredients, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(ingredients);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }


    public static List<Ingredient> readIngredientsFromBinaryFile(String fileName) {
        List<Ingredient> ingredients = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ingredients = (List<Ingredient>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return ingredients;
    }
}
