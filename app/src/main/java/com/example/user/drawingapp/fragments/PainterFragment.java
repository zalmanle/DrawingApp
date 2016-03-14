package com.example.user.drawingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.views.PainterView;

/**
 * Created by User on 14/03/2016.
 */
public class PainterFragment extends Fragment implements View.OnClickListener{

    //region Instance Variable
    private PainterView painter;

    private FloatingActionButton clearBtn;
    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.painter_fragment,container,false);
        initFields(view);
        return view;
    }

    private void initFields(View view) {
        painter = (PainterView)view.findViewById(R.id.painter);
        clearBtn = (FloatingActionButton)view.findViewById(R.id.clear_button);
        clearBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.clear_button:
                painter.clear();
                break;
        }
    }
}
