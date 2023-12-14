package com.example.chefkpo.Util;

import com.example.chefkpo.Dto.IngredientDto;
import com.example.chefkpo.Model.Ingredient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {
    public static void saveToFile(List<Ingredient> ingredientList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Ingredient ingredient : ingredientList) {
                String line = String.format("%s|%.1f|%.1f%n",
                        ingredient.getName(), ingredient.getWeight(), ingredient.getCalories());
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<IngredientDto> loadFromFile(String fileName) {
        List<IngredientDto> ingredientList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                IngredientDto ingredient = parseIngredient(line);
                if (ingredient != null) {
                    ingredientList.add(ingredient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingredientList;
    }

    private static IngredientDto parseIngredient(String line) {
        String regex = "([^|]+)\\|([\\d,]+)\\|([\\d,]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            String name = matcher.group(1);
            double weight = Double.parseDouble(matcher.group(2).replace(',', '.'));
            double caloriesPer100g = Double.parseDouble(matcher.group(3).replace(',', '.'));
            return new IngredientDto(name, weight, caloriesPer100g);
        } else {
            System.out.println("Invalid line: " + line);
            return null;
        }
    }

    public static String getFileNameWithoutExtension(String filename) {
        File file = new File(filename);
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return name;
        }
        return name.substring(0, lastIndexOf);
    }
}
