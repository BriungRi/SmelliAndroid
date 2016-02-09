package com.brianvli.smelliandroid;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PunTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String word = params[0];
        String output = "";
        try {
            String url = "http://brianvli.com:5000/" + word;
            URLConnection uc = new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            String s;
            while ((s = br.readLine()) != null) {
                output += s + "\n";
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clean(output);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }

    private String clean(String s){
        String[] elements = s.split("PUNNIFIED: ");
        int beg = elements[0].indexOf("<em>");
        int end = elements[0].indexOf("</em>");
        String wordAt = elements[0].substring(beg + 4, end).toUpperCase();
        elements[0] = elements[0].substring(0, beg) + wordAt + elements[0].substring(end + 5); //removes <em> tags and capitalizes word
        String output = "";
        output += elements[0] + "\n" + "PUNNIFIED: " + elements[1]; //adds space between sentences
        return output;
    }
}