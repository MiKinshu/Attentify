package com.kinshuu.attentify;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        tv = (TextView) findViewById(R.id.tv);

        for (int i = 0; i < CustomAdapter.imageModelArrayList.size(); i++){
            if(CustomAdapter.imageModelArrayList.get(i).getSelected()) {
                tv.setText(tv.getText() + " " + CustomAdapter.imageModelArrayList.get(i).getName());
            }
        }
    }
}