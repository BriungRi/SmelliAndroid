package com.brianvli.smelliandroid;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by brian on 2/5/16.
 */
public class PunTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        String word = params[0];
        String output = "";
        try {
            String url = "http://brianvli.com:5000/" + word;
            URLConnection uc = new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            String s = "";
            while ((s = br.readLine()) != null) {
                output += s + "\n";
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("Sentence", output);
        return output;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}