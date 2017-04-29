package com.example.sonu.sendsmsfree;

/**
 * Created by sonu on 28/4/17.
 */
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class dataFetching extends AsyncTask  <String, Void, String> {
    private Context context;
    TextView  textView ;
    String id ;
    String pass ;
    String mob;
    String mess;
    public dataFetching(Context context  ,String id , String pass , String mob , String mess , TextView textView) {
        this.context = context;
        this.textView = textView;
        this.id = id;
        this.pass = pass;
        this.mob = mob;
        this.mess = mess;



    }
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        String link;
        String data;
//         id = "9660729583";
//         pass = "ILOVENOKIA";
//         mob = "9024444807";
//         mess = "message from android app";
        BufferedReader bufferedReader;
        String result;
        try {
            data = "?id=" + URLEncoder.encode(id, "UTF-8")+"&pass="+URLEncoder.encode(pass, "UTF-8")+"&mob="+URLEncoder.encode(mob, "UTF-8")+"&mess="+URLEncoder.encode(mess, "UTF-8");
            link = "http://stareducationalgroup.in/a/index.php" + data;
            URL url = new URL(link);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = bufferedReader.readLine();
            return result;
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
    }
    @Override
    protected void onPostExecute(String result) {
        String jsonStr = result;
        this.textView.setText("Sent Successfully");
        if (jsonStr != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(jsonStr);
//                String query_result = jsonObj.getString("query_result");
//                this.textView.setText(query_result);
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Toast.makeText(context, "Check Your Internet Connection .", Toast.LENGTH_SHORT).show();
//            }
        } else {
            Toast.makeText(context, "Check Your Internet Connection .", Toast.LENGTH_SHORT).show();
        }
    }
}