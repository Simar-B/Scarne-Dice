package com.example.scarnedice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private Runnable run = new Runnable() {
        @Override
        public void run() {
            int diceNum = roll();
            Button button = findViewById(R.id.roll);
            Button hold = findViewById(R.id.hold);
            if(diceNum != 1 && ComTurnScore < 20) {
                ComTurnScore = ComTurnScore + diceNum;
                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                handler.postDelayed(run,1000);

            } else if(diceNum == 1){
                ComTurnScore = 0;
                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                button.setEnabled(true);
                hold.setEnabled(true);

            } else {
                ComOverallScore = ComOverallScore + ComTurnScore;
                ComTurnScore = 0;
                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                button.setEnabled(true);
                hold.setEnabled(true);

            }
        }
    };

    int UserOverallScore = 0;
    int UserTurnScore = 0;
    int ComOverallScore = 0;
    int ComTurnScore = 0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.roll);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //ImageView dice = findViewById(R.id.dice);
                int diceNum = roll();
                //String diceRes = "dice" + diceNum;
                //int id = getResources().getIdentifier(diceRes,"drawable",getPackageName());
                //dice.setImageDrawable(getResources().getDrawable(id,null));
                if(diceNum != 1) {
                    UserTurnScore = UserTurnScore + diceNum;
                    TextView text = findViewById(R.id.ScoreView);
                    text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);

                } else{
                    UserTurnScore = 0;
                    TextView text = findViewById(R.id.ScoreView);
                    text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                    computerTurn();
                }
                // Code here executes on main thread after user presses button
            }
        });
        final Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserTurnScore = 0;
                UserOverallScore = 0;
                ComOverallScore = 0;
                ComTurnScore = 0;
            }
        });
        final Button hold = findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                UserOverallScore = UserOverallScore + UserTurnScore;
                UserTurnScore = 0;
                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                computerTurn();
            }
        });

    }
    public void computerTurn(){
        Button button = findViewById(R.id.roll);
        button.setEnabled(false);
        Button hold = findViewById(R.id.hold);
        hold.setEnabled(false);


            int diceNum = roll();
            if(diceNum != 1 && ComTurnScore < 20) {
                ComTurnScore = ComTurnScore + diceNum;

                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                handler.postDelayed(run,1000);


            } else if(diceNum == 1){

                ComTurnScore = 0;
                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                button.setEnabled(true);
                hold.setEnabled(true);

            } else {
                ComOverallScore = ComOverallScore + ComTurnScore;
                TextView text = findViewById(R.id.ScoreView);
                text.setText("Your Score:"+UserOverallScore+ "Computer Score:"+ComOverallScore+ "Your Turn Score:" + UserTurnScore + "Computer Turn Score:" + ComTurnScore);
                button.setEnabled(true);
                hold.setEnabled(true);

            }





    }

    public int roll(){
        ImageView dice = findViewById(R.id.dice);
        int diceNum = random.nextInt(6 ) + 1;
        String diceRes = "dice" + diceNum;
        int id = getResources().getIdentifier(diceRes,"drawable",getPackageName());
        dice.setImageDrawable(getResources().getDrawable(id,null));
        return diceNum;

    }






}
