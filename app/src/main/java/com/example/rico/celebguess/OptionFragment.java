package com.example.rico.celebguess;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionFragment extends Fragment {

    private SharedPreferences preferences;
    private RadioButton but1;
    private RadioButton but2;
    private RadioButton but3;
    private RadioButton but4;

    public OptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_option, container, false);

        preferences = getActivity().getSharedPreferences("choices",MODE_PRIVATE);
        but1 = (RadioButton) v.findViewById(R.id.radioButton1);
        but2 = (RadioButton) v.findViewById(R.id.radioButton2);
        but3 = (RadioButton) v.findViewById(R.id.radioButton3);
        but4 = (RadioButton) v.findViewById(R.id.radioButton4);
        int checking = preferences.getInt("number",4);
        System.out.println(checking);
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
        v.findViewById(R.id.radioButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton number = (RadioButton) v;
                String buttonText = (String) number.getText();
                preferences.edit().putInt("number",Integer.parseInt(buttonText)).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        v.findViewById(R.id.radioButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton number = (RadioButton) v;
                String buttonText = (String) number.getText();
                preferences.edit().putInt("number",Integer.parseInt(buttonText)).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        v.findViewById(R.id.radioButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton number = (RadioButton) v;
                String buttonText = (String) number.getText();
                preferences.edit().putInt("number",Integer.parseInt(buttonText)).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });
        v.findViewById(R.id.radioButton4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton number = (RadioButton) v;
                String buttonText = (String) number.getText();
                preferences.edit().putInt("number",Integer.parseInt(buttonText)).apply();
                Intent intent = getActivity().getIntent();
                getActivity().overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                getActivity().finish();
                getActivity().overridePendingTransition(0, 0);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
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
}
