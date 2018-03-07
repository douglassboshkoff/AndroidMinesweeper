package com.ait.dboshko1.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ait.dboshko1.minesweeper.model.MSModel;
import com.ait.dboshko1.minesweeper.ui.MSView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MSView view = findViewById(R.id.msBoard);
        Button restartBtn = (Button) findViewById(R.id.restartBtn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.restartGame();
            }
        });
    }

    public String getRadioOption() {
        RadioGroup radioGrp = (RadioGroup) findViewById(R.id.touchType);
        RadioButton radioBtn = (RadioButton) findViewById(radioGrp.getCheckedRadioButtonId());
        return radioBtn.getText().toString();
    }

}
