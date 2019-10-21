package com.example.mynetdiarytest.ui.screens.recipes;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.domain.models.Recipe;
import com.example.mynetdiarytest.module.GlideApp;
import com.example.mynetdiarytest.module.MyNetDiaryGlideModule;
import com.example.mynetdiarytest.utils.glide.ProgressTarget;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private RecipesAdapter.RecipeListener recipeListener;

    public RecipeViewHolder(@NonNull View itemView, RecipesAdapter.RecipeListener recipeListener) {
        super(itemView);
        this.recipeListener = recipeListener;
    }

    public void bind(final Recipe recipe) {
        final ImageView ivRecipe = itemView.findViewById(R.id.ivRecipe);
        final ProgressBar progressBar = itemView.findViewById(R.id.progressBar);
        final TextView tvProgress = itemView.findViewById(R.id.tvProgress);

        ivRecipe.setTransitionName(recipe.getId() + recipe.getUrl());

        ProgressTarget<String, Bitmap> progressTarget = new RecipeProgressTarget<>(new BitmapImageViewTarget(ivRecipe), progressBar, ivRecipe, tvProgress);
        progressTarget.setModel(recipe.getUrl());
        GlideApp.with(ivRecipe)
                .asBitmap()
                .load(recipe.getUrl())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        recipeListener.onImageLoaded(getAdapterPosition());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        recipeListener.onImageLoaded(getAdapterPosition());
                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                recipeListener.onClick(RecipeViewHolder.this, recipe);
                            }
                        });
                        return false;
                    }
                })
                .centerCrop()
                .into(progressTarget);
    }

}
