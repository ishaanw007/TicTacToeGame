package com.ishaan.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// 0-x
//1-0
public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;
    int activePlayer=0;
    int [] gameState= {2,2,2,2,2,2,2,2,2};
//    State meaning
//    0-x
//    1-0
//    2-null
    int [] [] winPositions= {{0,1,2}, {3,4,5}, {6,7,8},
                             {0,3,6},{1,4,7},{2,5,8},
                               {0,4,8},{2,4,6}};
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
//        if (!gameActive){
//            gameReset(view);
//        }
        if (gameState[tappedImage] == 2 && gameActive) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                activePlayer = 1;
                img.setImageResource(R.drawable.x);
                TextView status = findViewById(R.id.status);
                status.setText("O turn tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X turn tap to play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        //check if any player have won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                //somebody has won
                String winnerStr = null;
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won Press reset";
                } else if (gameState[winPosition[1]] == 1) {
                    winnerStr = "O has won Press reset";
                }

                //update STATUS
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
//            else if (gameState[winPosition[0]] != 2 && gameState[winPosition[1]] != 2 &&
//            gameState[winPosition[2]] != 2) {
//                gameActive = false;
//                String loosestr = "Match draw press reset" ;
//
//                TextView status = findViewById(R.id.status);
//                status.setText(loosestr);
//            }
        }
//            int[] loosePosition : winPositions;
//            if (gameState[loosePosition[0]] != gameState[loosePosition[1]] &&
//                    gameState[loosePosition[1]] != gameState[loosePosition[2]] && gameState[loosePosition[0]] != 2) {
//                gameActive = false;
//
//                TextView status = findViewById(R.id.status);
//                status.setText("Match draw press reset");
//            }

        boolean emptySquare = false;
        for (int squareState : gameState) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }


        if (!emptySquare && gameActive) {
            // Game is a draw
            gameActive = false;
            TextView status = findViewById(R.id.status);
            status.setText("match is draw");

            // Set draw message here...
        }



    }
        public void gameReset (View view){
            TextView status = findViewById(R.id.status);
            status.setText("X turn tap to play");

            gameActive = true;
            activePlayer = 0;
            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }
            ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
