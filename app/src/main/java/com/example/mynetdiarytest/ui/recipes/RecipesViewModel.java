package com.example.mynetdiarytest.ui.recipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mynetdiarytest.android.mvvm.BaseViewModel;
import com.example.mynetdiarytest.domain.IRecipesDataSource;
import com.example.mynetdiarytest.domain.RecipesDataSource;
import com.example.mynetdiarytest.domain.models.Recipe;

import java.util.List;

public class RecipesViewModel extends BaseViewModel {

    private MutableLiveData<List<Recipe>> recipesMutableLiveData = new MutableLiveData<>();

    private IRecipesDataSource recipesDataSource = new RecipesDataSource();

    public RecipesViewModel(){
        recipesMutableLiveData.postValue(recipesDataSource.getRecipes());
    }

    public LiveData<List<Recipe>> getRecipesLiveData() {
        return recipesMutableLiveData;
    }

}
