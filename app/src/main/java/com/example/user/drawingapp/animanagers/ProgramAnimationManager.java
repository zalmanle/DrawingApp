package com.example.user.drawingapp.animanagers;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

/**
 * Created by User on 21/03/2016.
 */
public class ProgramAnimationManager extends AnimationManager {

    ObjectAnimator animator;

    private ObjectAnimator scaleY;

    private ObjectAnimator scaleX;

    public ProgramAnimationManager(Context context) {
        super(context);
    }

    @Override
    public void addAlpha(View view) {
        animator = ObjectAnimator.ofFloat(view, "alpha",
                1f);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void lessAlpha(View view) {
        animator = ObjectAnimator.ofFloat(view, "alpha",
                0f);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void addSize(View view) {
        scaleX = ObjectAnimator.ofFloat(view, "scaleX", 2.0f);
        scaleY = ObjectAnimator.ofFloat(view, "scaleY", 2.0f);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleX).with(scaleY);
        scaleDown.start();
    }

    @Override
    public void lessSize(View view) {
        scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f);
        scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f);
        scaleX.setDuration(1000);
        scaleY.setDuration(1000);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleX).with(scaleY);
        scaleDown.start();
    }

    @Override
    public void moveToRight(View view) {
        animator = ObjectAnimator.ofFloat(view,
                "translationX", 0f, 500f);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void moveToLeft(View view) {
        animator = ObjectAnimator.ofFloat(view,
                "translationX", 500f, 0f);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void rotateToRight(View view) {

        animator = ObjectAnimator.ofFloat(view,
                "rotation",0,360);
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    public void rotateToLeft(View view) {
        animator = ObjectAnimator.ofFloat(view,
                "rotation",360,0);
        animator.setDuration(2000);
        animator.start();
    }
}
