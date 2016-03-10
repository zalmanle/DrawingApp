package com.example.user.drawingapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.user.drawingapp.views.SmileView;

public class MainActivity extends AppCompatActivity {

    private SmileView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        displaySmile();

    }

    private void displaySmile() {
        view = new SmileView(this);
        handler.sendEmptyMessageDelayed(0,500);
        setContentView(view);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            view.invalidate();
            handler.sendEmptyMessageDelayed(0,500);
            return true;
        }
    });
}
