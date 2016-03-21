package com.example.user.drawingapp.animanagers;

import android.content.Context;
import android.view.View;

/**
 * Created by User on 21/03/2016.
 */
public abstract class AnimationManager {

    protected Context context;

    public AnimationManager(Context context) {
        this.context = context;
    }

    public abstract void addAlpha(View view);

    public abstract void lessAlpha(View view);

    public abstract void addSize(View view);

    public abstract void lessSize(View view);

    public abstract void moveToRight(View view);

    public abstract void moveToLeft(View view);

    public abstract void rotateToRight(View view);

    public abstract void rotateToLeft(View view);
}
