package com.brianvli.smelliandroid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
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
    private int intRot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        bGenerate = (Button) findViewById(R.id.bGenerate);
        etWord = (EditText) findViewById(R.id.etWord);
        tvSentences = (TextView) findViewById(R.id.tvSentences);
        etWord.setHint("Enter any word ");
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
                    changeButtonColor();
                    tvSentences.setText(new PunTask().execute(etWord.getText().toString()).get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void changeButtonColor() {
        String red = "00";
        String green = "00";
        String blue = "00";
        switch(intRot % 7){
            case 0:
                red = "50";
                break;
            case 1:
                green = "50";
                break;
            case 2:
                blue = "50";
                break;
            case 3:
                red = blue = "50";
                break;
            case 4:
                bGenerate.setTextColor(Color.WHITE);
                red = blue = "50";
                break;
            case 5:
                green = blue = "50";
                break;
            case 6:
                red = blue = green = "50";
                break;
        }
        intRot++;
        String strColor = "#" + red + green + blue;
//        int initColor = ((ColorDrawable)bGenerate.getBackground()).getColor();
//        String hexInitColor = Integer.toHexString(initColor);
        Log.d("Button Color: ", "" + strColor);
        bGenerate.setBackgroundColor(Color.parseColor(strColor));
    }
}
