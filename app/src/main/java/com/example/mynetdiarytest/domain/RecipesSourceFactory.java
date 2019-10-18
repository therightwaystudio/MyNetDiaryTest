package com.example.mynetdiarytest.domain;


import androidx.paging.DataSource;

import com.example.mynetdiarytest.data.RecipesStorage;
import com.example.mynetdiarytest.domain.models.Recipe;

public class RecipesSourceFactory extends DataSource.Factory<Integer, Recipe> {

    private RecipesStorage recipesStorage;

    public RecipesSourceFactory(RecipesStorage recipesStorage){
        this.recipesStorage = recipesStorage;
    }

    @Override
    public DataSource create() {
        return new RecipesDataSource(recipesStorage);
    }

}
