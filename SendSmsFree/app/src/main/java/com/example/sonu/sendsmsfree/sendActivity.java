package com.example.sonu.sendsmsfree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sendActivity extends AppCompatActivity {
    String id ;
    String pass ;
    String mob;
    String mess;
    EditText mobile , message;
    TextView status;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        pass = bundle.getString("pass");
        mobile = (EditText)findViewById(R.id.mobile1);
        message = (EditText)findViewById(R.id.message);
        status = (TextView) findViewById(R.id.status);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });


    }
    public void send() {
        mob = mobile.getText().toString();
        mess = message.getText().toString();
        new dataFetching(this , id , pass, mob , mess , status).execute();
    }
}
