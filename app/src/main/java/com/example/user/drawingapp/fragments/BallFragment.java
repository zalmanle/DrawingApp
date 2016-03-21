package com.example.user.drawingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.customanimations.ArcTranslateAnimation;

/**
 * Created by User on 21/03/2016.
 */
public class BallFragment extends Fragment {

    //region Instance Variables
    private ImageView imageView;
    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ball_fragment,container,false);
        initElements(view);
        return view;
    }

    private void initElements(View view) {
        imageView = (ImageView)view.findViewById(R.id.ball_image);
        ArcTranslateAnimation translate = new ArcTranslateAnimation(2000, Animation.ABSOLUTE,0,Animation.ABSOLUTE,700,Animation.ABSOLUTE,-2500);
        imageView.startAnimation(translate);


    }

}
