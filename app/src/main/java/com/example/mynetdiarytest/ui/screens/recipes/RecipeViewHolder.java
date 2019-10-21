package com.example.mynetdiarytest.ui.screens.recipes;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.domain.models.Recipe;
import com.example.mynetdiarytest.module.GlideApp;
import com.example.mynetdiarytest.module.MyNetDiaryGlideModule;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    private RecipesAdapter.RecipeListener recipeListener;

    public RecipeViewHolder(@NonNull View itemView, RecipesAdapter.RecipeListener recipeListener) {
        super(itemView);
        this.recipeListener = recipeListener;
    }

    public void bind(final Recipe recipe) {
        final ImageView ivRecipe = itemView.findViewById(R.id.ivRecipe);

        ivRecipe.setTransitionName(recipe.getId() + recipe.getUrl());

        final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
        circularProgressDrawable.setStrokeWidth(12f);
        circularProgressDrawable.setCenterRadius(36f);
        circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(itemView.getContext(), android.R.color.holo_green_dark),
                ContextCompat.getColor(itemView.getContext(), android.R.color.holo_red_dark),
                ContextCompat.getColor(itemView.getContext(), android.R.color.holo_blue_dark),
                ContextCompat.getColor(itemView.getContext(), android.R.color.holo_orange_dark));
        circularProgressDrawable.setArrowEnabled(true);
        circularProgressDrawable.setArrowDimensions(24f, 24f);
        circularProgressDrawable.start();

        MyNetDiaryGlideModule.expect(recipe.getUrl(), new MyNetDiaryGlideModule.UIonProgressListener() {
            @Override
            public void onProgress(long bytesRead, long expectedLength) {
                Log.d("onProgress", bytesRead + " " + expectedLength);
            }

            @Override
            public float getGranualityPercentage() {
                return 1f;
            }
        });

        GlideApp.with(ivRecipe)
                .load(recipe.getUrl())
                //.placeholder(circularProgressDrawable)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        recipeListener.onImageLoaded(getAdapterPosition());
                        MyNetDiaryGlideModule.forget(recipe.getUrl());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        recipeListener.onImageLoaded(getAdapterPosition());
                        MyNetDiaryGlideModule.forget(recipe.getUrl());
                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                recipeListener.onClick(RecipeViewHolder.this, recipe);
                            }
                        });
                        return false;
                    }
                })
                .into(ivRecipe);
    }

}
