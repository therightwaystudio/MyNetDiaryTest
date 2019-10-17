package com.example.mynetdiarytest.domain;

import com.example.mynetdiarytest.domain.models.Recipe;

import java.util.List;

public interface IRecipesDataSource {

    public List<Recipe> getRecipes();

}
