package com.kinshuu.attentify;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference msubjectsDatabaseReferenceWrite,msubjectsDatabaseReferenceRead;
    private ChildEventListener mChildEventListener;

    //managing user data
    String Subject="";
    String LTP="null";
    String Prof="RK";
    String Student="PM";
    String Date="31Aug2019";
    String Rollno="null";
    int clicked=0,b1=0,b2=0,b3=0;
    int RC_USER_PREF=88;

    //student arraylists
    ArrayList<Model> modelArrayList=new ArrayList<>();
    ArrayList<Subjects> subjectsArrayList=new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode!=RESULT_CANCELED) {
            if (requestCode == RC_USER_PREF) {
                if (data != null) {
                    subjectsArrayList = data.getParcelableArrayListExtra("subjects");
                }
                if (subjectsArrayList == null)
                    subjectsArrayList = new ArrayList<>();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Testing Intent start*/
//        Intent intent=new Intent(getApplicationContext(),Atteneder.class);
//        startActivity(intent);
        /*Testing Intent end*/

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        msubjectsDatabaseReferenceRead=mFirebaseDatabase.getReference().child("Students").child(Subject);
        mChildEventListener= new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Model model=dataSnapshot.getValue(Model.class);
                modelArrayList.add(model);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        msubjectsDatabaseReferenceRead.addChildEventListener(mChildEventListener);

        final Button BTNl=findViewById(R.id.BTNl);
        final Button BTNt=findViewById(R.id.BTNt);
        final Button BTNp=findViewById(R.id.BTNp);
        final Button BTNupdate=findViewById(R.id.BTNupdate);
        final Button BTNmanageatt=findViewById(R.id.BTNmanageatt);
        Spinner spinnerBatch=findViewById(R.id.spinnerBatch);

        String[] spinnerBatchlist={"Select Subject","PAS2018","DST2019","POE2019"};
        ArrayAdapter<String> spinnerBatchAdapter= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,spinnerBatchlist);
        spinnerBatchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBatch.setAdapter(spinnerBatchAdapter);
        spinnerBatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).toString().equals("Select Subject")){
                    BTNupdate.setEnabled(false);
                    BTNmanageatt.setEnabled(false);
                }
                else{
                    BTNupdate.setEnabled(true);
                    BTNmanageatt.setEnabled(true);
                    Subject=parent.getItemAtPosition(position).toString();
                    Toast.makeText(MainActivity.this, Subject, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        BTNl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        BTNl.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
        b1=1;
        BTNl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b1==0) {
                    BTNl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                    BTNl.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    LTP = "L";
                    b1=1;
                }
                else if(b1==1){
                    b1=0;
                    BTNl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNl.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                }
                if(b2==1||b3==1){
                    BTNt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                    BTNp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNp.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                    b2=0;
                    b3=0;
                }

            }
        });
        BTNt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b2==0) {
                    BTNt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                    BTNt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    LTP = "T";
                    b2=1;
                }
                else if(b2==1){
                    b2=0;
                    BTNt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                }
                if(b1==1||b3==1){
                    BTNl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNl.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                    BTNp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNp.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                    b1=0;
                    b3=0;
                }
            }
        });
        BTNp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b3==0) {
                    BTNp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
                    BTNp.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    LTP = "P";
                    b3=1;
                }
                else if(b3==1){
                    b3=0;
                    BTNp.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNp.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                }
                if(b2==1||b1==1){
                    BTNt.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNt.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                    BTNl.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorWhite));
                    BTNl.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorText));
                    b2=0;
                    b1=0;
                }
            }
        });
        BTNupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msubjectsDatabaseReferenceWrite=mFirebaseDatabase.getReference().child(Prof).child(Subject).child(LTP).child(Date);
                if(subjectsArrayList.size()==0)
                    Toast.makeText(MainActivity.this, "Take attendance first!", Toast.LENGTH_SHORT).show();
                else {
                    for (int i = 0; i < subjectsArrayList.size(); i++) {
                        msubjectsDatabaseReferenceWrite.push().setValue(subjectsArrayList.get(i));
                    }
                }
            }
        });

        BTNmanageatt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.kinshuu.attentify.Atteneder.class);
                startActivityForResult(intent, RC_USER_PREF);
            }
        });

    }
}
