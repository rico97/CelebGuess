package com.example.rico.celebguess;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Range;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private SharedPreferences preferences;
    private ArrayAdapter<String> names;
    private Toast currentToast;
    private ImageView img;
    private int numberOfChoice;
    private ArrayList<String> nameList;
    private ArrayList<Integer> photos;
    private ArrayList<String> nameForChoose;
    private Map<String,Integer> nameMap;
    private int currentPicNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        /*int randomiser = (int) (Math.random() * photos.size());
        img.setImageResource(photos.get(randomiser));
        nameForChoose.add(nameList.get(randomiser));

        while(nameForChoose.size()<numberOfChoice){
            int randomNameGenerator = (int) (Math.random()*nameList.size());
            String nameToPut = nameList.get(randomNameGenerator);
            if(nameForChoose.contains(nameToPut)){
                System.out.println("Name is already inside");
            }
            else {
                nameForChoose.add(nameToPut);
            }
        }

        for(int i = 0; i < numberOfChoice; i++){
            int randomNamePutter = (int) (Math.random()*nameForChoose.size());
            names.add(nameForChoose.get(randomNamePutter));
            nameForChoose.remove(randomNamePutter);
        }*/

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int orientation = getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_PORTRAIT) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this,Option.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
