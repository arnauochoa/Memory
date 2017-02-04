package com.ochoa.arnau.memory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnClick;

public class EasyMemory extends AppCompatActivity implements View.OnClickListener{

    int pairs = 8;
    int points;

    boolean win = false;

    Integer cardBack;
    Integer[] drawables = new Integer[pairs];

    CoolImageFlipper flipper;

    ImageView card0, card1, card2, card3, card4, card5, card6, card7, card8, card9, card10,
            card11, card12, card13, card14, card15;

    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_memory);



        card0 = (ImageView) findViewById(R.id.card0);
        card1 = (ImageView) findViewById(R.id.card1);
        card2 = (ImageView) findViewById(R.id.card2);
        card3 = (ImageView) findViewById(R.id.card3);
        card4 = (ImageView) findViewById(R.id.card4);
        card5 = (ImageView) findViewById(R.id.card5);
        card6 = (ImageView) findViewById(R.id.card6);
        card7 = (ImageView) findViewById(R.id.card7);
        card8 = (ImageView) findViewById(R.id.card8);
        card9 = (ImageView) findViewById(R.id.card9);
        card10 = (ImageView) findViewById(R.id.card10);
        card11 = (ImageView) findViewById(R.id.card11);
        card12 = (ImageView) findViewById(R.id.card12);
        card13 = (ImageView) findViewById(R.id.card13);
        card14 = (ImageView) findViewById(R.id.card14);
        card15 = (ImageView) findViewById(R.id.card15);

        card0.setOnClickListener(this);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);

        cardBack = R.drawable.ic_black_jack;

        drawables[0] = R.drawable.ic_adb_black_24dp;
        drawables[1] = R.drawable.ic_android_black_24dp;
        drawables[2] = R.drawable.ic_cake_black_24dp;
        drawables[3] = R.drawable.ic_diamonf;
        drawables[4] = R.drawable.ic_directions_boat_black_24dp;
        drawables[5] = R.drawable.ic_gambler;
        drawables[6] = R.drawable.ic_mood_black_24dp;
        drawables[7] = R.drawable.ic_playing_cards;

        flipper = new CoolImageFlipper(this);

        board = new Board(getResources(), drawables, cardBack, flipper);
    }

    public void onClick (View v) {
        switch (v.getId()){
            case R.id.card0:
                win = board.select(v, 0);
                break;
            case R.id.card1:
                win = board.select(v, 1);
                break;
            case R.id.card2:
                win = board.select(v, 2);
                break;
            case R.id.card3:
                win = board.select(v, 3);
                break;
            case R.id.card4:
                win = board.select(v, 4);
                break;
            case R.id.card5:
                win = board.select(v, 5);
                break;
            case R.id.card6:
                win = board.select(v, 6);
                break;
            case R.id.card7:
                win = board.select(v, 7);
                break;
            case R.id.card8:
                win = board.select(v, 8);
                break;
            case R.id.card9:
                win = board.select(v, 9);
                break;
            case R.id.card10:
                win = board.select(v, 10);
                break;
            case R.id.card11:
                win = board.select(v, 11);
                break;
            case R.id.card12:
                win = board.select(v, 12);
                break;
            case R.id.card13:
                win = board.select(v, 13);
                break;
            case R.id.card14:
                win = board.select(v, 14);
                break;
            case R.id.card15:
                win = board.select(v, 15);
                break;
        }

        if (win){
            points = board.getPoints();
            Toast.makeText(getApplicationContext(), "Congratulations! You did it in " + String.valueOf(points) + " trials", Toast.LENGTH_LONG).show();
            Log.d("WIN","CONGRATULATIONS!!");
        }
    }
}
