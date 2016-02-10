package com.brianvli.smelliandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import com.loopj.android.http.*;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;


public class DataSendTask extends AsyncTask<ArrayList<String>, Void, Void> {
    private Context mContext;

    public DataSendTask(Context c) {
        mContext = c;
    }

    private static AsyncHttpClient client = new SyncHttpClient();

    @Override
    protected Void doInBackground(ArrayList<String>... params) {
        ArrayList<String> data = params[0];
        StringEntity entity = null;
        try {
            JSONObject jo = new JSONObject();
            jo.put("orig", data.get(0));
            jo.put("new", data.get(1));
            jo.put("sentence", data.get(2));
            jo.put("binary", data.get(4));
            entity = new StringEntity(jo.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        AsyncHttpResponseHandler ahrh = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("status: ", "success");
                Log.d("status code", statusCode + "");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("status: ", "failure");
                Log.d("status code", statusCode + "");
            }
        };

        post("http://brianvli.com:5000/punengine/api/v1.0/data", entity, ahrh);
        return null;
    }

    //TODO: should this be a static method?
    public void post(String url,
                     HttpEntity entity,
                     ResponseHandlerInterface responseHandler) {
        client.post(mContext, url, entity, "application/json", responseHandler);
    }
}
