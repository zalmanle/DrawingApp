package com.example.user.drawingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.user.drawingapp.fragments.PainterFragment;
import com.example.user.drawingapp.fragments.SmileFragment;
import com.example.user.drawingapp.fragments.SmileyFragment;

public class MainActivity extends AppCompatActivity {

    //region Instance Variables
    private FrameLayout container;

    private FragmentsLoader loader;
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initElements();
        loader = new FragmentsLoader();

    }


    private void initElements() {
        container = (FrameLayout) findViewById(R.id.container);


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
            case R.id.smile_fragment_item:
                loader.loadSmileFragment();
                return true;

            case R.id.smiley_fragment_item:
                loader.loadSmileyFragment();
                return true;
            case R.id.painter_fragment_item:
                loader.loadPainterFragment();
                return true;

            case R.id.advanced_painter_fragment_item:

                return true;
            case R.id.exit_item:
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class FragmentsLoader {

        //region Instance Variables
        private FragmentManager manager;

        private FragmentTransaction transaction;
        //endregion
        private FragmentsLoader(){
            manager = getSupportFragmentManager();
        }

        private void loadSmileFragment(){
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container,new SmileFragment());
            transaction.commit();
        }

        private void loadSmileyFragment(){
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container,new SmileyFragment());
            transaction.commit();
        }

        private void loadPainterFragment(){
            transaction = manager.beginTransaction();
            transaction.replace(R.id.container,new PainterFragment());
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
