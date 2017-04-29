package com.example.sonu.sendsmsfree;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button send;
    EditText mobile , password ;
    TextView nk;
    DBHandler db;
    String mob  , pass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send  = (Button)findViewById(R.id.send);
        mobile = (EditText)findViewById(R.id.mobile);
        password = (EditText)findViewById(R.id.password);
        nk = (TextView) findViewById(R.id.nk);
        db = new DBHandler(this);
        nk.setText(db.getUser() + " ");
        int i = db.getUser();
        if(i > 0) {
            Intent intent = new Intent(getApplicationContext() , sendActivity.class);
            intent.putExtra("id" , db.getDataMobile(1));
            intent.putExtra("pass" , db.getDataPass(1));
            startActivity(intent);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });
    }
    public void insert() {
        db.insert(mobile.getText().toString() , password.getText().toString());
        Intent intent = new Intent(getApplicationContext() , sendActivity.class);
        intent.putExtra("id" , db.getDataMobile(1));
        intent.putExtra("pass" , db.getDataPass(1));
        startActivity(intent);
       // new dataFetching(this , nk).execute();
    }
}
