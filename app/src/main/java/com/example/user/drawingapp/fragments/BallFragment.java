package com.example.user.drawingapp.fragments;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.customanimations.ArcTranslateAnimation;

/**
 * Created by User on 21/03/2016.
 */
public class BallFragment extends Fragment
        implements View.OnClickListener,
        Animator.AnimatorListener{

    //region Constants
    private static final int OBJECT_MODE_CODE = 210;

    private static final int CUSTOM_MODE_CODE = 220;
    //endregion
    //region Instance Variables
    private RadioGroup modeGroup;

    private ImageView imageView;

    private int currentCode;

    private FloatingActionButton animateBtn;

    private ArcTranslateAnimation translate;

    private float startX;

    private float startY;

    private ObjectAnimator animatorX1;

    private ObjectAnimator animatorUpY;

    private ObjectAnimator animatorX2;

    private ObjectAnimator animatorDownY;

    private AnimatorSet set;

    private AnimatorSet setBack;

    private int height;

    private ObjectAnimator posterSliderX;

    private ObjectAnimator posterSliderY;

    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ball_fragment,container,false);
        setModeGroup(view);
        initElements(view);
        currentCode = OBJECT_MODE_CODE;
        return view;
    }

    private void initElements(View view) {
        imageView = (ImageView)view.findViewById(R.id.ball_image);
        height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        animateBtn = (FloatingActionButton)view.findViewById(R.id.animate_button);
        animateBtn.setOnClickListener(this);
    }


    private void setModeGroup(View view) {
        modeGroup = (RadioGroup)view.findViewById(R.id.options_radio_group);
        modeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.object_animation_radio_button:
                        currentCode = OBJECT_MODE_CODE;
                        break;
                    case R.id.custom_animation_radio_button:
                        currentCode = CUSTOM_MODE_CODE;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animate_button:
                animate();
                break;
        }
    }

    private void animate() {
        if(currentCode == OBJECT_MODE_CODE){
            animateWithObject();
        }
        else if(currentCode == CUSTOM_MODE_CODE){
            animateWithCustomAnimation();
        }
    }

    private void animateWithObject() {

        if (animatorX1 == null){
            animatorX1 = ObjectAnimator.ofFloat(imageView,
                    "translationX",0,350);

        }

        if(animatorUpY == null){
            animatorUpY = ObjectAnimator.ofFloat(imageView,
                    "translationY",0,-1100);
        }

        if(animatorX2 == null){
            animatorX2 = ObjectAnimator.ofFloat(imageView,
                    "translationX",350,700);
        }

        if(animatorDownY == null){
            animatorDownY = ObjectAnimator.ofFloat(imageView,
                    "translationY",-1100,0);

        }


        set = new AnimatorSet();
        set.setDuration(2000);
        set.playTogether(animatorX1,animatorUpY);
        set.start();
        set.addListener(this);
        set.play(animatorX2).with(animatorDownY).after(2000);
        set.start();
    }

    private void animateWithCustomAnimation() {
        if(translate == null) {
             translate = new ArcTranslateAnimation(2000, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 700, Animation.ABSOLUTE, -2500);
        }
        imageView.startAnimation(translate);
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

        setBack = new AnimatorSet();
        posterSliderX = ObjectAnimator.ofFloat(imageView, "translationX", 0);

        posterSliderY = ObjectAnimator.ofFloat(imageView, "translationY", 0);
        setBack.playTogether(posterSliderX,posterSliderY);
        setBack.start();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
