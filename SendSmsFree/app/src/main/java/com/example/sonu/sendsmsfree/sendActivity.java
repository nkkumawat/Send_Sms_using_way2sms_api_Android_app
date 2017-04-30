package com.example.sonu.sendsmsfree;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sendActivity extends AppCompatActivity {
    String id ;
    String pass ;
    String mob;
    String mess;
    EditText mobile , message;
    TextView status;
    Button send  ,pick , history;
    DBHandler2 db;

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
        pick = (Button) findViewById(R.id.pick);
        history = (Button) findViewById(R.id.history1);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickContact();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , history.class);
                startActivity(intent);
                finish();
            }
        });
        db = new DBHandler2(this);
       // int num = db.getUser();
        //Toast.makeText(getApplicationContext() , num , Toast.LENGTH_LONG).show();

    }
    public void send() {
        mob = mobile.getText().toString();
        mess = message.getText().toString();
        db.insert(mob, mess);
        new dataFetching(this , id , pass, mob , mess , status).execute();
    }
    private void pickContact() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        startActivityForResult(Intent.createChooser(intent, "Select Contact"), 1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri uri1 = data.getData();
                Cursor c = managedQuery(uri1, null, null, null, null);
                if (c.moveToFirst()) {
                    String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //System.out.println("name is: " + name);
                    status.setText(name);
                }
                Cursor cursor = getContentResolver().query(uri1, null, null, null, null);
                if (null == cursor) return;
                try {
                    while (cursor.moveToNext()) {
                        String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        //System.out.println("number is "+number);
                        mobile.setText(number);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    cursor.close();
                }
            }
        }
    }
}
