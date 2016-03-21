package com.example.user.drawingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.animanagers.AnimationManager;
import com.example.user.drawingapp.animanagers.ProgramAnimationManager;
import com.example.user.drawingapp.animanagers.XMLAnimationManager;

/**
 * Created by User on 21/03/2016.
 */
public class SimpleAnimationFragment extends Fragment implements View.OnClickListener{

    //region Instance Variables
    private RadioGroup modeGroup;

    private AnimationManager current;

    private ProgramAnimationManager programAnimation;

    private XMLAnimationManager xmlAnimation;

    private Button addAlphaBtn;

    private Button lessAlphaBtn;

    private Button addSizeBtn;

    private Button lessSizeBtn;

    private Button moveToLeftBtn;

    private Button moveToRightBtn;

    private Button rotateToRightBtn;

    private Button rotateToLeftBtn;

    private TextView textView;
    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_animation_demo_fragment,container,false);
        initAnimations();
        textView = (TextView)view.findViewById(R.id.animate_result_text_view);
        initButtons(view);
        setModeGroup(view);
        return view;
    }

    private void initButtons(View view) {
        addAlphaBtn = (Button)view.findViewById(R.id.add_alpha_button);
        addAlphaBtn.setOnClickListener(this);

        lessAlphaBtn = (Button)view.findViewById(R.id.less_alpha_button);
        lessAlphaBtn.setOnClickListener(this);

        addSizeBtn = (Button)view.findViewById(R.id.add_scale_button);
        addSizeBtn.setOnClickListener(this);

        lessSizeBtn = (Button)view.findViewById(R.id.minus_scale_button);
        lessSizeBtn.setOnClickListener(this);

        moveToLeftBtn = (Button)view.findViewById(R.id.left_translate_button);
        moveToLeftBtn.setOnClickListener(this);

        moveToRightBtn = (Button)view.findViewById(R.id.right_translate_button);
        moveToRightBtn.setOnClickListener(this);

        rotateToLeftBtn = (Button)view.findViewById(R.id.left_rotate_button);
        rotateToLeftBtn.setOnClickListener(this);

        rotateToRightBtn = (Button)view.findViewById(R.id.right_rotate_button);
        rotateToRightBtn.setOnClickListener(this);
    }

    private void initAnimations() {
        xmlAnimation = new XMLAnimationManager(getContext());
        programAnimation = new ProgramAnimationManager(getContext());
        current = xmlAnimation;
    }

    private void setModeGroup(View view) {
        modeGroup = (RadioGroup)view.findViewById(R.id.radio_group);
        modeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.xml_animation_radio_button:
                        current = xmlAnimation;
                        break;
                    case R.id.program_animation_radio_button:
                        current = programAnimation;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(current != null){
            switch (v.getId()){
                case R.id.add_alpha_button:
                    current.addAlpha(textView);
                    break;
                case R.id.less_alpha_button:
                    current.lessAlpha(textView);
                    break;
                case R.id.add_scale_button:
                    current.addSize(textView);
                    break;
                case R.id.minus_scale_button:
                    current.lessSize(textView);
                    break;
                case R.id.left_rotate_button:
                    current.rotateToLeft(textView);
                    break;
                case R.id.right_rotate_button:
                    current.rotateToRight(textView);
                    break;
                case R.id.left_translate_button:
                    current.moveToLeft(textView);
                    break;
                case R.id.right_translate_button:
                    current.moveToRight(textView);
                    break;

            }
        }

    }
}
