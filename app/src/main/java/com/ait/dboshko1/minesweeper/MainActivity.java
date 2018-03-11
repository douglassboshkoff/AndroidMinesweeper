package com.ait.dboshko1.minesweeper;

import android.support.design.widget.Snackbar;
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
        Button restartBtn = findViewById(R.id.restartBtn);
        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.restartGame();
            }
        });
    }

    public String getRadioOption() {
        RadioGroup radioGrp = findViewById(R.id.touchType);
        RadioButton radioBtn = findViewById(radioGrp.getCheckedRadioButtonId());
        return radioBtn.getText().toString();
    }

    public void checkWin() {
        if(MSModel.getInstance().getGameStatus() == MSModel.GAME_WIN) {
            Snackbar.make(findViewById(R.id.layoutRoot),
                    R.string.win_message, Snackbar.LENGTH_SHORT).show();
        } else if(MSModel.getInstance().getGameStatus() == MSModel.GAME_LOSE) {
            Snackbar.make(findViewById(R.id.layoutRoot),
                    R.string.lose_message, Snackbar.LENGTH_SHORT).show();
        }
    }

}
