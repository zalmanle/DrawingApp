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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.user.drawingapp.R;

/**
 * Created by User on 22/03/2016.
 */
public class CatAnimationFragment extends Fragment implements View.OnClickListener {

    //region Constants
    private static final int FORWARD_MODE = 200;

    private static final int BACK_MODE = 201;

    private static final int OBJECT_MODE_CODE = 210;

    private static final int XML_MODE_CODE = 230;
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

    private RadioGroup modeGroup;

    private int currentCode;

    private Animation backAnimation;

    private Animation forwardAnimation;

    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cats_dissapear_fragment,container,false);
        setElements(view);
        currentMode = FORWARD_MODE;
        currentCode = OBJECT_MODE_CODE;
        return view;
    }

    private void setElements(View view) {
        animateBtn = (FloatingActionButton)view.findViewById(R.id.animate_cat_button);
        animateBtn.setOnClickListener(this);
        catImageView = (ImageView)view.findViewById(R.id.cat_image_view);
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
                    case R.id.xml_animation_radio_button:
                        currentCode = XML_MODE_CODE;
                        break;
                }
            }
        });
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
                if(currentCode == OBJECT_MODE_CODE){
                    doForwardAnimation();
                }
                else if(currentCode == XML_MODE_CODE){
                    doForwardAnimationWithXML();
                }
                currentMode = BACK_MODE;
                break;
            case BACK_MODE:
                if(currentCode == OBJECT_MODE_CODE){
                    doBackAnimation();
                }
                else if(currentCode == XML_MODE_CODE){
                    doBackAnimationWithXML();
                }

                currentMode = FORWARD_MODE;
                break;
        }
    }

    private void doBackAnimationWithXML() {
        backAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.cat_backward_animation);
        catImageView.startAnimation(backAnimation);
    }

    private void doForwardAnimationWithXML() {
        forwardAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),R.anim.cat_forward_animation);
        catImageView.startAnimation(forwardAnimation);
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
