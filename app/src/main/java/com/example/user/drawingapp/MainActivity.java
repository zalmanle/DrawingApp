package com.example.user.drawingapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.user.drawingapp.views.SmileView;
import com.example.user.drawingapp.views.SmileyView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //region Constants
    private static final int MESSAGE_CODE = 0;
    //endregion
    //region Instance Variables
    private FrameLayout container;

    private LinearLayout buttonsContainer;

    private SmileView smileView;

    private SmileyView smileyView;

    private Button rotateBtn;

    private Button translateBtn;

    private Button scaleBtn;

    private View currentView;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        setDefault();

    }

    private void setDefault() {

        container.removeAllViews();
        container.addView(smileyView);
        currentView = smileyView;
    }

    private void initElements() {
        container = (FrameLayout) findViewById(R.id.smile_container);
        buttonsContainer = (LinearLayout) findViewById(R.id.buttons_container);
        smileView = new SmileView(this);
        smileyView = new SmileyView(this);
        rotateBtn = (Button)findViewById(R.id.rotate_button);
        rotateBtn.setOnClickListener(this);
        translateBtn = (Button)findViewById(R.id.translate_button);
        translateBtn.setOnClickListener(this);
        scaleBtn = (Button)findViewById(R.id.scale_button);
        scaleBtn.setOnClickListener(this);
    }

    // Initiating Menu XML file (menu.xml)
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Event Handling for Individual menu item selected
     * Identify single menu item by it's id
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.first_menu_item:
                setDefault();
                buttonsContainer.setVisibility(View.VISIBLE);
                handler.removeMessages(MESSAGE_CODE);
                return true;

            case R.id.second_menu_item:
                displaySmile();
                buttonsContainer.setVisibility(View.GONE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
      if(currentView instanceof SmileyView) {
          switch(v.getId()){
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



    private void displaySmile() {

        container.removeAllViews();
        container.addView(smileView);
        currentView = smileView;
        handler.sendEmptyMessageDelayed(MESSAGE_CODE,500);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            currentView.invalidate();
            handler.sendEmptyMessageDelayed(MESSAGE_CODE,500);
            return true;
        }
    });
}
