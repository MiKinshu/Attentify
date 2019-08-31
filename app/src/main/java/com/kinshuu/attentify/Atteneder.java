package com.kinshuu.attentify;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Atteneder extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Model> modelArrayList;
    private ArrayList<Subjects> subjectsArrayList;
    private CustomAdapter customAdapter;
    private Button btnselect, btndeselect, btnnext;
    private  String[] rolllist = new String[]{"IIT2018001", "IIT2018002", "IIT2018003", "IIT2018004", "IIT2018005", "IIT2018006", "IIT2018007", "IIT2018008", "IIT2018009", "IIT2018010"};
    private  String[] namelist = new String[]{"Prateek Mishra", "Roshni Prajapati", "Ayush Raj", "Raunak Rathour", "Astha Tiwari", "Sunidhi Kashyap", "Shivansh Tiwari", "Ajay Kudal", "o", "p"};
    int t_switch=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atteneder);

        recyclerView = findViewById(R.id.recycler);
        btnselect = findViewById(R.id.select);
        btnnext = findViewById(R.id.next);

        modelArrayList = getModel(false);
        customAdapter = new CustomAdapter(this,modelArrayList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        btnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(t_switch==0) {
                    modelArrayList = getModel(true);
                    customAdapter = new CustomAdapter(Atteneder.this, modelArrayList);
                    recyclerView.setAdapter(customAdapter);
                    t_switch=1;
                }
                else{
                    modelArrayList = getModel(false);
                    customAdapter = new CustomAdapter(Atteneder.this,modelArrayList);
                    recyclerView.setAdapter(customAdapter);t_switch=0;
                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectsArrayList = getSubjects(modelArrayList);

                //getTheToast(subjectsArrayList);//For testing (by toasting) value of array adapter to be returned
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("subjects",subjectsArrayList);
                setResult(RESULT_OK, intent);
                //getTheToast(subjectsArrayList);//For testing (by toasting) value of array adapter to be returned
                finish();
                /*
                YAAAAAAAAAAAHAAAAAN!!!!!!!

                PRATEEK MISHRA YAHA PE SE "subjectsArrayList" Adapter intent karwa lena
                day and date daal dena aur main activity mein intent kar dena after successful dialog box.
                */

              getTheToast(subjectsArrayList);//For testing (by toasting) value of array adapter to be returned
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
    private ArrayList<Subjects> getSubjects(ArrayList<Model> lis){
        ArrayList<Subjects> list = new ArrayList<>();
        for(int i = 0; i < rolllist.length; i++){
            Subjects sub = new Subjects(0);
            sub.setName(namelist[i]);
            sub.setRoll(rolllist[i]);
            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
            String t1=currentDateTimeString.substring(0,12);//,t2=currentDateTimeString.substring(13);
            sub.setDate(t1);
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            Date d = new Date();
            String dayOfTheWeek = sdf.format(d);
            sub.setDay(dayOfTheWeek);
            if(CustomAdapter.imageModelArrayList.get(i).getSelected()) {
                sub.setPres(1);
//                Toast.makeText(getApplicationContext(),lis.get(i).getName(),Toast.LENGTH_LONG).show();
            }
            else {
                sub.setPres(0);
            }
            list.add(sub);
        }
        return list;
    }
    private void getTheToast(ArrayList<Subjects> sublist){
        String str="";
        for(int i = 0; i < rolllist.length; i++){
           str+="Present?  ";str+=sublist.get(i).getPres();str+= " ";
           str+="Date: ";str+=sublist.get(i).getDate();str+= " ";
           str+="Day: ";str+=sublist.get(i).getDay();str+= " ";
           str+="Name: ";str+=sublist.get(i).getName();str+= " ";
           str+="Roll: ";str+=sublist.get(i).getRoll();str+= " ";
           str+='\n';
        }
        Toast.makeText(getApplicationContext(),str,Toast.LENGTH_LONG).show();
    }
}
