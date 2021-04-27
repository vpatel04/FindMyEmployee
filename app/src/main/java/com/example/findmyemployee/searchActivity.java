package com.example.findmyemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class searchActivity extends AppCompatActivity {
    private EditText EName;
    private Button Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);

        EName = (EditText) findViewById(R.id.etName);
        Search = (Button) findViewById(R.id.btnSearch);



        Search.setOnClickListener(new View.OnClickListener(){

                                      @Override
                                      public void onClick(View v){
                                          String name = EName.getText().toString();
                                          SQLiteDatabase DB = getApplicationContext().openOrCreateDatabase("Userdata.db", Context.MODE_PRIVATE, null);
                                          //Cursor c = DB.rawQuery("select * from Userdetails where EName = '"+name+"'", null );
                                          Cursor c = DB.rawQuery("select * from Userdetails where EName = ?", new String[]{name});
                                          if(c.getCount()==0)
                                          {
                                              Toast.makeText(getApplicationContext(),  "NO record found", Toast.LENGTH_LONG);
                                              return;
                                          }
                                          StringBuffer buffer = new StringBuffer();

                                          while (c.moveToNext()){
                                              buffer.append("EName:   \t" +c.getString(0)+"\n");
                                              buffer.append("Id:   \t" +c.getString(1)+"\n");
                                              buffer.append("Email:  \t" +c.getString(2)+"\n\n");

                                          }
                                          Toast.makeText(getApplicationContext(), "Employee Details \n"+buffer.toString(), Toast.LENGTH_SHORT).show();

                                      }
                                  }
        );
    }}



