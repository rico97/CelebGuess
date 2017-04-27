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

        preferences = getSharedPreferences("choices",MODE_PRIVATE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        names = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        img = (ImageView) findViewById(R.id.imageToShow);
        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(names);
        nameList = new ArrayList<>();
        photos = new ArrayList<>();
        nameForChoose = new ArrayList<>();

        nameMap = new HashMap<String, Integer>();
        nameMap.put("Arnold Schwarzenegger", R.drawable.arnold);
        nameMap.put("Emma Stone",R.drawable.stone);
        nameMap.put("Jessica Alba", R.drawable.alba);
        nameMap.put("Robert Downy Jr.", R.drawable.robert);
        nameMap.put("Kristen Stewart", R.drawable.kristen);


        for ( Map.Entry<String, Integer> entry : nameMap.entrySet() ) {
            String name = entry.getKey();
            nameList.add(name);
            int value = entry.getValue();
            photos.add(value);
        }

        nameList.add("Bobie");
        nameList.add("Daniel");
        nameList.add("Dylan");
        nameList.add("Jack");
        nameList.add("Jill");
        nameList.add("Sino");
        nameList.add("Emily");
        nameList.add("Paul");
        nameList.add("Kevin");
        nameList.add("Roy");


        photoPicker();

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
        int orientation = getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_LANDSCAPE) {
            RadioButton but1 = (RadioButton) findViewById(R.id.radioButton1);
            RadioButton but2 = (RadioButton) findViewById(R.id.radioButton2);
            RadioButton but3 = (RadioButton) findViewById(R.id.radioButton3);
            RadioButton but4 = (RadioButton) findViewById(R.id.radioButton4);
            int checking = preferences.getInt("number",4);
            switch (checking){
                case 2:
                    but1.setChecked(true);
                    break;
                case 4:
                    but2.setChecked(true);
                    break;
                case 6:
                    but3.setChecked(true);
                    break;
                case 8:
                    but4.setChecked(true);
                    break;
            }
        }
        gameSetter();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(nameMap.containsKey(parent.getItemAtPosition(position).toString())){
                    if(img.getDrawable().getConstantState() == getResources().getDrawable(nameMap.get(parent.getItemAtPosition(position).toString())).getConstantState()){
                        if(currentToast != null)
                        {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(MainActivity.this, "Correct", Toast.LENGTH_SHORT);
                        currentToast.show();
                    }
                    else{
                        if(currentToast != null)
                        {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(MainActivity.this, "Wrong" , Toast.LENGTH_SHORT);
                        currentToast.show();
                    }
                    photoPicker();
                    gameSetter();
                } else{
                    if(currentToast != null)
                    {
                        currentToast.cancel();
                    }
                    currentToast = Toast.makeText(MainActivity.this, "Wrong" , Toast.LENGTH_SHORT);
                    currentToast.show();
                    photoPicker();
                    gameSetter();
                }


            }
        });
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

    public void gameSetter() {
        numberOfChoice = preferences.getInt("number",4 );
        names.clear();
        nameForChoose.add(nameList.get(currentPicNumber));
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
        }
    }

    public void choicesButtonClicked(View view){
        RadioButton number = (RadioButton) view;
        String buttonText = (String) number.getText();
        System.out.println(buttonText);
        preferences.edit().putInt("number",Integer.parseInt(buttonText)).apply();
        gameSetter();
    }

    public void photoPicker(){
        int randomiser = (int) (Math.random() * photos.size());
        img.setImageResource(photos.get(randomiser));
        currentPicNumber = randomiser;
    }

}
