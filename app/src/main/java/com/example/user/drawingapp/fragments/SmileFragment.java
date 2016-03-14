package com.example.user.drawingapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.drawingapp.R;
import com.example.user.drawingapp.views.SmileView;

/**
 * Created by User on 14/03/2016.
 */
public class SmileFragment extends Fragment {

    //region Constants
    private static final int MESSAGE_CODE = 0;

    private static final int DELAY = 500;
    //endregion
    //region Instance Variables
    SmileView smileView;
    //endregion
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.smile_fragment,container,false);
        smileView = (SmileView)view.findViewById(R.id.smile);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.sendEmptyMessageDelayed(MESSAGE_CODE, DELAY);

    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeMessages(MESSAGE_CODE);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            smileView.invalidate();
            handler.sendEmptyMessageDelayed(MESSAGE_CODE,DELAY);
            return true;
        }
    });
}
