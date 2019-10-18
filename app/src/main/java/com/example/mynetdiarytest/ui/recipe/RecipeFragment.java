package com.example.mynetdiarytest.ui.recipe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.android.mvvm.BaseFragment;
import com.example.mynetdiarytest.domain.models.Recipe;
import com.example.mynetdiarytest.module.GlideApp;

import java.util.List;
import java.util.Map;

public class RecipeFragment extends BaseFragment<RecipeViewModel> {

    ImageView ivRecipe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        ivRecipe = view.findViewById(R.id.ivRecipe);

        Recipe recipe = getArguments().getParcelable(RECIPE);

        ivRecipe.setTransitionName(recipe.getId() + recipe.getUrl());

        prepareSharedElementTransition();

        if (savedInstanceState == null) {
            postponeEnterTransition();
        }

        GlideApp.with(this)
                .load(recipe.getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        startPostponedEnterTransition();
                        return false;
                    }
                })
                //.onlyRetrieveFromCache(true)
                .into(ivRecipe);

        return view;
    }

    @Override
    public RecipeViewModel viewModel() {
        return (new ViewModelProvider(this).get(RecipeViewModel.class));
    }

    private void prepareSharedElementTransition() {
        setSharedElementEnterTransition(TransitionInflater.from(requireContext()).inflateTransition(R.transition.image_shared_element_transition));
        setEnterSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                sharedElements.put(names.get(0), ivRecipe);
            }
        });
    }

    static final String RECIPE = "RECIPE";

    public static Fragment newInstance(Recipe recipe) {
        Fragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        args.putParcelable(RECIPE, recipe);
        fragment.setArguments(args);
        return fragment;
    }

}
