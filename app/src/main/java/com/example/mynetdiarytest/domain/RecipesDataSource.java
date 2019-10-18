package com.example.mynetdiarytest.domain;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.example.mynetdiarytest.data.RecipesStorage;
import com.example.mynetdiarytest.domain.models.Recipe;

import java.util.List;

public class RecipesDataSource extends PositionalDataSource<Recipe> {

    private RecipesStorage recipesStorage;

    public RecipesDataSource(RecipesStorage recipesStorage){
        this.recipesStorage = recipesStorage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Recipe> callback) {
        List<Recipe> recipes = recipesStorage.getRecipes(params.requestedStartPosition, params.requestedLoadSize);
        callback.onResult(recipes, params.requestedStartPosition);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Recipe> callback) {
        List<Recipe> recipes = recipesStorage.getRecipes(params.startPosition, params.loadSize);
        callback.onResult(recipes);
    }
}
