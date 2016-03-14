package com.example.user.drawingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.views.SmileyView;

/**
 * Created by User on 14/03/2016.
 */
public class SmileyFragment extends Fragment implements View.OnClickListener {

    //region Instance Variables
    private LinearLayout buttonsContainer;

    private SmileyView smileyView;

    private Button rotateBtn;

    private Button translateBtn;

    private Button scaleBtn;

    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.smiley_fragment, container, false);
        initElements(view);
        return view;
    }

    private void initElements(View view) {
        buttonsContainer = (LinearLayout) view.findViewById(R.id.buttons_container);
        rotateBtn = (Button) view.findViewById(R.id.rotate_button);
        rotateBtn.setOnClickListener(this);
        translateBtn = (Button) view.findViewById(R.id.translate_button);
        translateBtn.setOnClickListener(this);
        scaleBtn = (Button) view.findViewById(R.id.scale_button);
        scaleBtn.setOnClickListener(this);
        smileyView = (SmileyView) view.findViewById(R.id.smiley);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rotate_button:
                smileyView.rotate();
                break;
            case R.id.translate_button:
                smileyView.translate();
                break;
            case R.id.scale_button:
                smileyView.scale();
                break;
        }
    }
}
