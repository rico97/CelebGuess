package com.example.rico.celebguess;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

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

    public MainActivityFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        preferences = getActivity().getSharedPreferences("choices",MODE_PRIVATE);
        names = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1);
        img = (ImageView) v.findViewById(R.id.imageToShow);
        gridView = (GridView) v.findViewById(R.id.gridView);
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
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
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
                        currentToast = Toast.makeText(getActivity(), "Correct", Toast.LENGTH_SHORT);
                        currentToast.show();
                    }
                    else{
                        if(currentToast != null)
                        {
                            currentToast.cancel();
                        }
                        currentToast = Toast.makeText(getActivity(), "Wrong" , Toast.LENGTH_SHORT);
                        currentToast.show();
                    }
                    photoPicker();
                    gameSetter();
                } else{
                    if(currentToast != null)
                    {
                        currentToast.cancel();
                    }
                    currentToast = Toast.makeText(getActivity(), "Wrong" , Toast.LENGTH_SHORT);
                    currentToast.show();
                    photoPicker();
                    gameSetter();
                }


            }
        });
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


    public void photoPicker(){
        int randomiser = (int) (Math.random() * photos.size());
        img.setImageResource(photos.get(randomiser));
        currentPicNumber = randomiser;
    }
}
