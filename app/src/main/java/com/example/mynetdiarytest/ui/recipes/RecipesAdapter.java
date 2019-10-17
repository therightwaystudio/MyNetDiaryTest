package com.example.mynetdiarytest.ui.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.domain.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private List<Recipe> items = new ArrayList<>();
    private RecipeListener recipeListener;

    public void replace(List<Recipe> newItems) {
        RecipeDiffUtil diffUtil = new RecipeDiffUtil(newItems, items);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);

        items.clear();
        items.addAll(newItems);

        diffResult.dispatchUpdatesTo(this);
    }

    public RecipesAdapter(RecipeListener recipeListener) {
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
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    interface RecipeListener {
        void onClick(RecipeViewHolder viewHolder, Recipe recipe);
        void onImageLoaded(int position);
    }

}
