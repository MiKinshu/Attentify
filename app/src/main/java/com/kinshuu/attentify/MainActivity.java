package com.kinshuu.attentify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Model> modelArrayList;
    private CustomAdapter customAdapter;
    private Button btnselect, btndeselect, btnnext;
    private  String[] rolllist = new String[]{"IIT2018001", "IIT2018002", "IIT2018003", "IIT2018004", "IIT2018005", "IIT2018006", "IIT2018007", "IIT2018008"};
    private  String[] namelist = new String[]{"q", "w", "e", "r", "t", "y", "u", "i"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        btnselect = findViewById(R.id.select);
        btndeselect = findViewById(R.id.deselect);
        btnnext = findViewById(R.id.next);

        modelArrayList = getModel(false);
        customAdapter = new CustomAdapter(this,modelArrayList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelArrayList = getModel(true);
                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
                recyclerView.setAdapter(customAdapter);
            }
        });
        btndeselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelArrayList = getModel(false);
                customAdapter = new CustomAdapter(MainActivity.this,modelArrayList);
                recyclerView.setAdapter(customAdapter);
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Model> getModel(boolean isSelect){
        ArrayList<Model> list = new ArrayList<>();
        for(int i = 0; i < rolllist.length; i++){

            Model model = new Model();
            model.setSelected(isSelect);
            model.setName(namelist[i]);
            model.setRoll(rolllist[i]);
            list.add(model);
        }
        return list;
    }
}