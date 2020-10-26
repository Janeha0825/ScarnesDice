package com.example.snarnedice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    public static Integer userOverallScore = 0;
    public static Integer userTurnScore = 0;
    public static Integer computerOverallScore = 0;
    public static Integer computerTurnScore = 0;
    protected Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button rollButton = (Button) findViewById(R.id.button);
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int val = rollDice();
                updateStatus(val);
            }
        });

        Button resetButton = findViewById(R.id.button3);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverallScore = 0;
                userTurnScore = 0;
                computerOverallScore = 0;
                computerTurnScore = 0;
                TextView status = (TextView) findViewById(R.id.textView);
                status.setText("Your Score : 0 Computer Score : 0 your turn score : 0");
            }
        });

        Button holdButton = findViewById(R.id.button2);
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverallScore += userTurnScore;
                TextView status = (TextView) findViewById(R.id.textView);
                status.setText("Your Score : "+userOverallScore+" Computer Score : "+computerOverallScore+" your turn score : "+userTurnScore);
                userTurnScore = 0;
                computerTurn();
            }
        });
    }

    protected Integer rollDice() {
        Integer value = random.nextInt(6) + 1;
        if (value == 1) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageResource(R.drawable.dice1);
        } else if (value == 2) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageResource(R.drawable.dice2);
        } else if (value == 3) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageResource(R.drawable.dice3);
        } else if (value == 4) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageResource(R.drawable.dice4);
        } else if (value == 5) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageResource(R.drawable.dice5);
        } else if (value == 6) {
            ImageView i = findViewById(R.id.imageView);
            i.setImageResource(R.drawable.dice6);
        }
        return value;
    }
    protected void updateStatus(Integer value)  {
            TextView status = (TextView) findViewById(R.id.textView);
            if (value != 1) {
                userTurnScore += value;
                status.setText("Your Score : " + userOverallScore+" Computer Score : "+computerOverallScore+" your turn score : " + userTurnScore);
            }
            else {
                status.setText("You roll a one. Your Score : " + userOverallScore + " Computer Score : " + computerOverallScore + " your turn score : 0");
                userTurnScore = 0;
                computerTurn();
            }
    }

    protected void computerTurn() {

        Button rollButton = findViewById(R.id.button);
        Button holdButton = findViewById(R.id.button2);
        rollButton.setEnabled(false);
        holdButton.setEnabled(false);
        int value = rollDice();
        while (value != 1 && computerTurnScore < 20) {
            computerTurnScore += value;
            value = rollDice();
        }
        TextView status = (TextView) findViewById(R.id.textView);
        if (value == 1) {
            status.setText("Computer rolled a one. Your Score : " + userOverallScore + " Computer Score : " + computerOverallScore + " computer turn score : "+computerTurnScore);
        }
        else{
            computerOverallScore += computerTurnScore;
            status.setText("Your Score : " + userOverallScore + " Computer Score : " + computerOverallScore + " computer turn score : "+computerTurnScore);
        }
        computerTurnScore = 0;
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
