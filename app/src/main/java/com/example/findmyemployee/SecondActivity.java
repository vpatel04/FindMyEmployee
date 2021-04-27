package com.example.findmyemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {
    private TextView Tittle;
    private EditText EName;
    private EditText Id;
    private EditText Email;

    private Button View;
    private Button Add;
    private Button Delete;
    private Button Update;
    private Button Search;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Tittle = (TextView) findViewById(R.id.tvTittle);
        EName = (EditText) findViewById(R.id.etEName);
        Id = (EditText) findViewById(R.id.etId);
        Email = (EditText) findViewById(R.id.etEmail);

        View = (Button) findViewById(R.id.btnView);
        Add = (Button) findViewById(R.id.btnAdd);
        Delete = (Button) findViewById(R.id.btnDelete);
        Update = (Button) findViewById(R.id.btUpdate);
        Search = (Button) findViewById(R.id.btnSearch);

        DB = new DBHelper(this);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = EName.getText().toString();
                String contactTXT = Id.getText().toString();
                String dobTXT = Email.getText().toString();

                Boolean checkinsertdata = DB.adddata(nameTXT, contactTXT, dobTXT);
                if (checkinsertdata == true)
                    Toast.makeText(SecondActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = EName.getText().toString();
                String contactTXT = Id.getText().toString();
                String dobTXT = Email.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if (checkupdatedata == true)
                    Toast.makeText(SecondActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = EName.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if (checkdeletedata)
                    Toast.makeText(SecondActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SecondActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(SecondActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("EName:" + res.getString(0) + "\n");
                    buffer.append("Id:" + res.getString(1) + "\n");
                    buffer.append("Email:" + res.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Employee Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
  Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             Intent Intent = new Intent(view.getContext(), searchActivity.class);
              startActivityForResult(Intent, 0);
          }
       });
    }
}