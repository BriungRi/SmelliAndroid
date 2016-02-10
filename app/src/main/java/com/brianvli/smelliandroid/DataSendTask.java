package com.brianvli.smelliandroid;

import android.os.AsyncTask;
import android.util.Log;

public class DataSendTask extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        Log.d("Check Status", "OK");
        return null;
    }
}
