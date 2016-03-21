package com.example.user.drawingapp.animanagers;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.user.drawingapp.R;

/**
 * Created by User on 21/03/2016.
 */
public class XMLAnimationManager extends AnimationManager {

    Animation animation;

    public XMLAnimationManager(Context context) {
        super(context);
    }

    @Override
    public void addAlpha(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.add_alpha);
        view.startAnimation(animation);
    }

    @Override
    public void lessAlpha(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.less_alpha);
        view.startAnimation(animation);
    }

    @Override
    public void addSize(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.add_size);
        view.startAnimation(animation);
    }

    @Override
    public void lessSize(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.less_size);
        view.startAnimation(animation);
    }

    @Override
    public void moveToRight(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.right_moving);
        view.startAnimation(animation);
    }

    @Override
    public void moveToLeft(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.left_moving);
        view.startAnimation(animation);
    }

    @Override
    public void rotateToRight(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.right_rotation);
        view.startAnimation(animation);
    }

    @Override
    public void rotateToLeft(View view) {
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.left_rotation);
        view.startAnimation(animation);
    }
}
