package com.example.mynetdiarytest.ui.screens.recipes;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;
import com.example.mynetdiarytest.R;
import com.example.mynetdiarytest.utils.glide.ProgressTarget;

public class RecipeProgressTarget<Z> extends ProgressTarget<String, Z> {
    private final TextView text;
    private final ProgressBar progress;
    private final ImageView image;

    public RecipeProgressTarget(Target<Z> target, ProgressBar progress, ImageView image, TextView text) {
        super(target);
        this.progress = progress;
        this.image = image;
        this.text = text;
    }

    @Override
    public float getGranualityPercentage() {
        return 1f;
    }

    @Override
    protected void onConnecting() {
        progress.setIndeterminate(true);
        progress.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        text.setText(R.string.connecting);
    }

    @Override
    protected void onDownloading(long bytesRead, long expectedLength) {
        progress.setIndeterminate(false);
        progress.setProgress((int) (100 * bytesRead / expectedLength));
        text.setText("");
    }

    @Override
    protected void onDownloaded() {
        progress.setIndeterminate(true);
        text.setText(R.string.decoding_and_transforming);
    }

    @Override
    protected void onDelivered() {
        progress.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
    }

    @Override
    protected View getView() {
        return image;
    }
}
