package com.example.mynetdiarytest.ui;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.android.mvvm.BaseActivity;
import com.example.mynetdiarytest.ui.recipe.RecipeFragment;
import com.example.mynetdiarytest.ui.recipes.RecipesFragment;

public class MainActivity extends BaseActivity<MainActivityViewModel> {

    private int position;

    private final String POSITION = "POSITION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, (new RecipesFragment()), RecipeFragment.class.getCanonicalName())
                    .commit();
        } else {
            position = savedInstanceState.getInt(POSITION);
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
    }

    @Override
    public MainActivityViewModel viewModel() {
        return (new ViewModelProvider(this).get(MainActivityViewModel.class));
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}