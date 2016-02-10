package com.brianvli.smelliandroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GenerateActivity extends AppCompatActivity implements View.OnClickListener {

    Button bGenerate, bGood, bBad;
    EditText etWord;
    TextView tvSentences;
    private ArrayList<String> data;
    private int intRot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        bGenerate = (Button) findViewById(R.id.bGenerate);
        bGood = (Button) findViewById(R.id.bGood);
        bBad = (Button) findViewById(R.id.bBad);
        etWord = (EditText) findViewById(R.id.etWord);
        tvSentences = (TextView) findViewById(R.id.tvSentences);
        etWord.setHint("Enter any word ");

        bGenerate.setOnClickListener(this);
        bGood.setOnClickListener(this);
        bBad.setOnClickListener(this);
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
                    this.data = new PunTask().execute(etWord.getText().toString()).get();
                    Thread.sleep(1000); //attempt to reduce load on main UI
                    tvSentences.setText(data.get(3));
                    changeButtonColor();

                    bGood.setEnabled(true);
                    bBad.setEnabled(true); //make good and bad buttons available after generate is pressed

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bGood: //if good or bad pressed, disable both good and bad buttons
                bGood.setEnabled(false);
                bBad.setEnabled(false);
                data.add("1"); //"good" binary tag
                new DataSendTask(this).execute(data);
                break;
            case R.id.bBad:
                bGood.setEnabled(false);
                bBad.setEnabled(false);
                data.add("0"); //"bad" binary tag
                new DataSendTask(this).execute(data);
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
        bGenerate.setBackgroundColor(Color.parseColor(strColor));
    }
}
