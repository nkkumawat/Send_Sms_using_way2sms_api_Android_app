package com.example.sonu.sendsmsfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class history extends AppCompatActivity {
    DBHandler db;
    DBHandler2 db2;
    TextView his;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        his = (TextView) findViewById(R.id.history);
        db = new DBHandler(this);
        db2 = new DBHandler2(this);
        int num = db2.getUser() ;
        String data = "";
        data += "UserName : "+ db.getDataMobile(1)+"\n";
        data += "Password : "+ db.getDataPass(1)+"\n\n\n";
        for(int i =0 ; i < num ; i ++) {
            data += "Mobile : "+ db2.getDataMobile(1)+"\n";
            data += "Message : "+ db2.getDataPass(1)+"\n\n";
        }
        his.setText(data);
    }
}
