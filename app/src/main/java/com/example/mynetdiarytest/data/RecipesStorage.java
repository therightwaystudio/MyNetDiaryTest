package com.example.mynetdiarytest.data;

import com.example.mynetdiarytest.domain.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesStorage {

    private List<Recipe> recipes = new ArrayList<>();

    public RecipesStorage(){
        recipes.add(new Recipe(1,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11985403.jpg"));
        recipes.add(new Recipe(2,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11986497.jpg"));
        recipes.add(new Recipe(3,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11988733.jpg"));
        recipes.add(new Recipe(4,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/11989088.jpg"));
        recipes.add(new Recipe(5,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12153487.jpg"));
        recipes.add(new Recipe(6,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12167354.jpg"));
        recipes.add(new Recipe(7,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12231705.jpg"));
        recipes.add(new Recipe(8,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12362599.jpg"));
        recipes.add(new Recipe(9,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12369258.jpg"));
        recipes.add(new Recipe(10,"", "https://s3.amazonaws.com/img.mynetdiary.com/PremiumRecipePictures/hi-res/12409611.jpg"));
    }

    public List<Recipe> getRecipes(int startPosition, int size){
        if(startPosition < recipes.size()) {
            if (startPosition + size <= recipes.size())
                return recipes.subList(startPosition, startPosition + size);
            else
                return recipes.subList(startPosition, recipes.size());
        } else {
            return new ArrayList<>();
        }
    }

}
