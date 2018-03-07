package com.ait.dboshko1.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getRadioOption() {
        RadioGroup radioGrp = (RadioGroup) findViewById(R.id.touchType);
        RadioButton radioBtn = (RadioButton) findViewById(radioGrp.getCheckedRadioButtonId());
        return radioBtn.getText().toString();
    }
}
