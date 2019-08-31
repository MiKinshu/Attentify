package com.kinshuu.attentify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ViewAttend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attend);
        Spinner spinnerDate=findViewById(R.id.spinnerDate);
        String[] spinnerDatelist={"Select Date","15Aug2018","DST2019","POE2019"};
        ArrayAdapter<String> spinnerDateAdapter= new ArrayAdapter<>(ViewAttend.this, android.R.layout.simple_list_item_1,spinnerDatelist);
        spinnerDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(spinnerDateAdapter);
        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Select Subject")){

                }
                else{
                    //Assume you got an ArrayLIst from here.
//                   Write your code here
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
