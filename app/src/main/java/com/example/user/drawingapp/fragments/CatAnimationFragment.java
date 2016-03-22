package com.example.user.drawingapp.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.drawingapp.R;

/**
 * Created by User on 22/03/2016.
 */
public class CatAnimationFragment extends Fragment implements View.OnClickListener {

    //region Constants
    private static final int FORWARD_MODE = 200;

    private static final int BACK_MODE = 201;
    //endregion
    //region Instance Variables
    private FloatingActionButton animateBtn;

    private ImageView catImageView;

    private ObjectAnimator animatorXForward;

    private ObjectAnimator animatorUpY;

    private ObjectAnimator animatorXBack;

    private ObjectAnimator animatorDownY;

    private ObjectAnimator animatorAddScaleX;

    private ObjectAnimator animatorLessScaleX;

    private ObjectAnimator animatorAddScaleY;

    private ObjectAnimator animatorLessScaleY;

    private AnimatorSet set;

    private int currentMode;
    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cats_dissapear_fragment,container,false);
        setElements(view);
        currentMode = FORWARD_MODE;
        return view;
    }

    private void setElements(View view) {
        animateBtn = (FloatingActionButton)view.findViewById(R.id.animate_cat_button);
        animateBtn.setOnClickListener(this);
        catImageView = (ImageView)view.findViewById(R.id.cat_image_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animate_cat_button:
                animate();
                break;
        }
    }

    private void animate() {
        switch (currentMode){
            case FORWARD_MODE:
                doForwardAnimation();
                currentMode = BACK_MODE;
                break;
            case BACK_MODE:
                doBackAnimation();
                currentMode = FORWARD_MODE;
                break;
        }
    }

    private void doBackAnimation() {
        if(animatorXBack == null){
            animatorXBack = ObjectAnimator.ofFloat(catImageView,
                    "translationX",-350,0);
        }

        if(animatorUpY == null){
            animatorUpY = ObjectAnimator.ofFloat(catImageView,
                    "translationY",800,0);
        }

        if(animatorAddScaleX == null){
            animatorAddScaleX = ObjectAnimator.ofFloat(catImageView, "scaleX", 1.0f);
        }

        if(animatorAddScaleY == null){
            animatorAddScaleY = ObjectAnimator.ofFloat(catImageView, "scaleY", 1.0f);
        }

        set = new AnimatorSet();
        set.setDuration(2000);
        set.playTogether(animatorXBack, animatorUpY, animatorAddScaleX, animatorAddScaleY);
        set.start();
    }

    private void doForwardAnimation() {
        if(animatorXForward == null){
            animatorXForward = ObjectAnimator.ofFloat(catImageView,
                    "translationX",0,-350);
        }


        if(animatorDownY == null){
            animatorDownY = ObjectAnimator.ofFloat(catImageView,
                    "translationY",0,800);
        }

        if(animatorLessScaleX == null){
            animatorLessScaleX = ObjectAnimator.ofFloat(catImageView, "scaleX", 0.0f);
        }

        if(animatorLessScaleY == null ){
            animatorLessScaleY = ObjectAnimator.ofFloat(catImageView, "scaleY", 0.0f);
        }




        set = new AnimatorSet();
        set.setDuration(2000);
        set.playTogether(animatorXForward, animatorDownY, animatorLessScaleX, animatorLessScaleY);
        set.start();
    }
}
