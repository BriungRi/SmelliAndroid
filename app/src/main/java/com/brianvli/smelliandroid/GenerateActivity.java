package com.brianvli.smelliandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GenerateActivity extends AppCompatActivity implements View.OnClickListener {

    Button bGenerate;
    EditText etWord;
    TextView tvSentences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        bGenerate = (Button) findViewById(R.id.bGenerate);
        etWord = (EditText) findViewById(R.id.etWord);
        tvSentences = (TextView) findViewById(R.id.tvSentences);

        bGenerate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bGenerate:
                try {
                    View view = this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    tvSentences.setText(new PunTask().execute(etWord.getText().toString()).get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
