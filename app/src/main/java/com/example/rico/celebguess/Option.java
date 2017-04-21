package com.example.rico.celebguess;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class Option extends AppCompatActivity {

    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        preferences = getSharedPreferences("choices",MODE_PRIVATE);
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

    public void choicesButtonClicked(View view){
        RadioButton number = (RadioButton) view;
        String buttonText = (String) number.getText();
        System.out.println(buttonText);
        preferences.edit().putInt("number",Integer.parseInt(buttonText)).apply();
    }

}
