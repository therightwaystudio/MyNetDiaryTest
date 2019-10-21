package com.example.mynetdiarytest.ui.customs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

public class PercentProgressBar extends ProgressBar {

    public PercentProgressBar(Context context) {
        super(context);
    }

    public PercentProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PercentProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    private float textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
                18F,
            getResources().getDisplayMetrics()
        );

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isIndeterminate()) {
            textPaint.setARGB(255, 0, 153, 204);
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setTextSize(textSize);
            textPaint.setTypeface(Typeface.SANS_SERIF);

            String percents = getProgress() + "%";

            canvas.drawText(percents, getWidth() / 2F, (getHeight() / 2F) - ((textPaint.ascent() + textPaint.descent()) / 2), textPaint);
        }
    }
}
