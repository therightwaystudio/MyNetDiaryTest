package com.example.mynetdiarytest.ui.screens.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.domain.models.Recipe;
public class RecipesAdapter extends PagedListAdapter<Recipe, RecipeViewHolder> {

    private RecipeListener recipeListener;

    public RecipesAdapter(DiffUtil.ItemCallback<Recipe> diffUtilCallback, RecipeListener recipeListener) {
        super(diffUtilCallback);
        this.recipeListener = recipeListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view, recipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    interface RecipeListener {
        void onClick(RecipeViewHolder viewHolder, Recipe recipe);
        void onImageLoaded(int position);
    }

}
