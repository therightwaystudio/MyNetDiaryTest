package com.example.mynetdiarytest.ui.recipes;

import androidx.recyclerview.widget.DiffUtil;

import com.example.mynetdiarytest.domain.models.Recipe;

import java.util.List;

public class RecipeDiffUtil extends DiffUtil.Callback {

    private List<Recipe> newItems;
    private List<Recipe> oldItems;

    public RecipeDiffUtil(List<Recipe> newItems, List<Recipe> oldItems) {
        this.newItems = newItems;
        this.oldItems = oldItems;
    }

    @Override
    public int getOldListSize() {
        return oldItems.size();
    }

    @Override
    public int getNewListSize() {
        return newItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getId() == newItems.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldItems.get(oldItemPosition).getName().equals(newItems.get(newItemPosition).getName())
                && oldItems.get(oldItemPosition).getUrl().equals(newItems.get(newItemPosition).getUrl());
    }

}
