package com.example.gameconnect;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    // 0= leaf and 1 = flower 2= emptySpace

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = { {0,1,2}, {3,4,5}, {6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;

    public void dropIn(View view){

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive)
        {
        gameState[tappedCounter] = activePlayer;

        counter.setTranslationY(-1500);

        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.image1);
            activePlayer = 1;
        } else{
            counter.setImageResource(R.drawable.image2);
            activePlayer = 0;
        }

        counter.animate().translationYBy(1500).rotationBy(360).setDuration(300);
      for (  int[] winningPosition : winningPositions) {
          if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]]== gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2 ) {
              // someone has won
              gameActive = false;
              String message;
              if (activePlayer == 1) {
                  message = "plantsss have won!";
              } else {
                  message = "flowersss have won!";
              }
              Button button = (Button) findViewById(R.id.playButton);
              TextView textView = (TextView) findViewById(R.id.textView);
              textView.setText(message);
              button.setVisibility(View.VISIBLE);
              textView.setVisibility(View.VISIBLE);

          }
          }

        }
      }
        public void playAgain(View view){
            Button button = (Button) findViewById(R.id.playButton);
            TextView textView =(TextView) findViewById(R.id.textView);
            button.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            for(int i = 0; i<gridLayout.getChildCount();i++){
                ImageView counter = (ImageView) gridLayout.getChildAt(i);
                counter.setImageDrawable(null);
            }

          gameActive = true;
          Arrays.fill(gameState, 2);
           activePlayer = 0;


      }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}