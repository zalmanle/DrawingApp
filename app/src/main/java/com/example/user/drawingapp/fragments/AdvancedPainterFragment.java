package com.example.user.drawingapp.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.views.MultiColorsPainterView;

/**
 * Created by User on 14/03/2016.
 */
public class AdvancedPainterFragment extends Fragment implements View.OnClickListener{

    //region Instance Variables
    private SeekBar seekBar;

    private FloatingActionButton clearBtn;

    private FloatingActionButton undoBtn;

    private FloatingActionButton blueBtn;

    private FloatingActionButton greenBtn;

    private FloatingActionButton redBtn;

    private FloatingActionButton blackBtn;

    private FloatingActionButton orangeBtn;

    private MultiColorsPainterView painterView;
    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.advanced_painter_fragment,container,false);
        initElements(view);
        return view;
    }

    private void initElements(View view) {
        clearBtn = (FloatingActionButton)view.findViewById(R.id.clear_button);
        clearBtn.setOnClickListener(this);
        undoBtn = (FloatingActionButton)view.findViewById(R.id.undo_button);
        undoBtn.setOnClickListener(this);
        blueBtn = (FloatingActionButton)view.findViewById(R.id.blue_button);
        blueBtn.setOnClickListener(this);
        greenBtn = (FloatingActionButton)view.findViewById(R.id.green_button);
        greenBtn.setOnClickListener(this);
        redBtn = (FloatingActionButton)view.findViewById(R.id.red_button);
        redBtn.setOnClickListener(this);
        blackBtn = (FloatingActionButton)view.findViewById(R.id.black_button);
        blackBtn.setOnClickListener(this);
        orangeBtn = (FloatingActionButton)view.findViewById(R.id.orange_button);
        orangeBtn.setOnClickListener(this);
        painterView = (MultiColorsPainterView)view.findViewById(R.id.advanced_painter);
        seekBar = (SeekBar) view.findViewById(R.id.size_seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                painterView.setWeight(progressValue/3);

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.clear_button:
                painterView.clear();
                break;
            case R.id.undo_button:
                painterView.undo();
                break;
            case R.id.blue_button:
                painterView.setColor(Color.BLUE);
                break;
            case R.id.green_button:
                painterView.setColor(Color.GREEN);
                break;
            case R.id.red_button:
                painterView.setColor(Color.RED);
                break;
            case R.id.black_button:
                painterView.setColor(Color.BLACK);
                break;
            case R.id.orange_button:
                int color = getContext().getResources().getColor(android.R.color.holo_orange_dark);
                painterView.setColor(color);
                break;
        }
    }
}
