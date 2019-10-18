package com.example.mynetdiarytest.ui.recipes;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.mynetdiarytest.android.mvvm.BaseViewModel;
import com.example.mynetdiarytest.data.RecipesStorage;
import com.example.mynetdiarytest.domain.RecipesSourceFactory;
import com.example.mynetdiarytest.domain.models.Recipe;
import static com.example.mynetdiarytest.utils.Constants.RECIPE_PAGE_SIZE;

public class RecipesViewModel extends BaseViewModel {

    private LiveData<PagedList<Recipe>> recipesLiveData;

    public RecipesViewModel() {
        RecipesSourceFactory recipesSourceFactory = new RecipesSourceFactory(new RecipesStorage());
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(RECIPE_PAGE_SIZE)
                .build();

        recipesLiveData = new LivePagedListBuilder<>(recipesSourceFactory, config).build();
    }

    public LiveData<PagedList<Recipe>> getRecipesLiveData() {
        return recipesLiveData;
    }

}
