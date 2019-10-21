package com.example.mynetdiarytest.ui.screens.recipes;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.android.mvvm.BaseFragment;
import com.example.mynetdiarytest.domain.models.Recipe;
import com.example.mynetdiarytest.ui.screens.main.MainActivity;
import com.example.mynetdiarytest.ui.screens.recipe.RecipeFragment;

import java.util.List;
import java.util.Map;

public class RecipesFragment extends BaseFragment<RecipesViewModel> {

    private RecipesAdapter recipesAdapter;
    private RecyclerView rvRecipes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        recipesAdapter = new RecipesAdapter(new RecipeDiffUtil(),new RecipesAdapter.RecipeListener() {
            @Override
            public void onClick(RecipeViewHolder viewHolder, Recipe recipe) {
                ((MainActivity) requireActivity()).setPosition(viewHolder.getAdapterPosition());
                ((TransitionSet) getExitTransition()).excludeTarget(viewHolder.itemView, true);
                ImageView ivRecipe = viewHolder.itemView.findViewById(R.id.ivRecipe);
                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .addSharedElement(ivRecipe, ivRecipe.getTransitionName())
                        .replace(R.id.container, RecipeFragment.newInstance(recipe), RecipeFragment.class.getCanonicalName())
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onImageLoaded(int position) {
                int currentPosition = ((MainActivity) requireActivity()).getPosition();
                if (currentPosition == position)
                    startPostponedEnterTransition();
            }
        });

        rvRecipes = view.findViewById(R.id.rvRecipes);
        rvRecipes.setAdapter(recipesAdapter);

        prepareTransitions();

        postponeEnterTransition();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel().getRecipesLiveData().observe(getViewLifecycleOwner(), (new Observer<PagedList<Recipe>>() {
            @Override
            public void onChanged(PagedList<Recipe> recipes) {
                recipesAdapter.submitList(recipes);
            }
        }));

        rvRecipes.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                rvRecipes.removeOnLayoutChangeListener(this);
                final RecyclerView.LayoutManager layoutManager = rvRecipes.getLayoutManager();
                final int currentPosition = ((MainActivity) requireActivity()).getPosition();
                View viewAtPosition = layoutManager.findViewByPosition(currentPosition);
                if (viewAtPosition == null || layoutManager
                        .isViewPartiallyVisible(viewAtPosition, false, true)) {
                    rvRecipes.post(new Runnable() {
                        @Override
                        public void run() {
                            layoutManager.scrollToPosition(currentPosition);
                        }
                    });
                }
            }
        });
    }

    @Override
    public RecipesViewModel viewModel() {
        return (new ViewModelProvider(this).get(RecipesViewModel.class));
    }

    private void prepareTransitions() {
        setExitTransition(TransitionInflater.from(requireContext()).inflateTransition(R.transition.grid_exit_transition));
        setExitSharedElementCallback(new SharedElementCallback() {
            @Override
            public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                RecyclerView.ViewHolder selectedViewHolder = rvRecipes
                        .findViewHolderForAdapterPosition(((MainActivity) requireActivity()).getPosition());
                if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
                    return;
                }

                sharedElements.put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.ivRecipe));
            }
        });
    }

}
