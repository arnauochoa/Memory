package com.ochoa.arnau.memory;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by arnau on 03/02/2017.
 */
public class Board {

    private int points, matchedPairs;
    private boolean isFirst = true;
    private boolean mustWait = true;
    private boolean canClick = true;

    private int pairs;

    private Card[] cards;
    private Card lastCard = new Card(0);
    private Card thisCard = new Card(0);

    private View lastCardView;
    private View thisCardView;

    private CoolImageFlipper flipper;
    private Resources resources;

    private Integer cardBack;
    private Integer[] drawables; //Array with all drawables id
    private Integer[] values; //Drawables after duplicate values and shuffle


    public Board(Resources resources, Integer[] drawables, Integer cardBack, CoolImageFlipper flipper) {
        this.drawables = drawables;
        this.flipper = flipper;
        this.resources = resources;
        this.cardBack = cardBack;

        points = 0;
        matchedPairs = 0;

        pairs = drawables.length;

        values = new Integer[2*pairs];
        cards = new Card[values.length];
        setCards();
    }

    public int getPoints() {
        return points;
    }

    public void setCards() {
        for(int i = 0; i < drawables.length ; i++){
            values[i*2] = drawables[i];
            values[(i*2)+1] = drawables[i];
        }

        List<Integer> valuesList = Arrays.asList(values);
        Collections.shuffle(valuesList);
        valuesList.toArray(values);

        for (int i = 0; i < 2*pairs; i++){
            cards[i] = new Card(values[i]);
        }

        lastCard.setValue(0);
    }

    public boolean select(View v,int pos){
        boolean win = false;
        if(canClick) {
            thisCard = cards[pos];
            thisCardView = v;
            if (!thisCard.isSelected()) {
                flip(thisCardView, thisCard);
                win = check();
                if (!mustWait) {
                    lastCard = thisCard;
                    lastCardView = thisCardView;
                }

                mustWait = true;
                points++;
            }
        }
        return win;
    }

    private boolean check() {
        boolean win = false;
        if(!isFirst) {
            canClick = false;
            if (lastCard.getValue() == thisCard.getValue()) {
                matchedPairs++;
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        flip(thisCardView, thisCard);
                        flip(lastCardView, lastCard);
                        canClick = true;
                        mustWait = false;
                    }
                }, 1000);
            }
            if (matchedPairs == (values.length/2)){
                win = true;
            }
        }else{
            mustWait = false;
        }
        isFirst = !isFirst;
        return win;
    }

    private void flip(View v,Card card) {
        if(card.isSelected()){
            flipper.flipImage(resources.getDrawable(cardBack), (ImageView) v);
        }else{
            flipper.flipImage(resources.getDrawable(card.getValue()), (ImageView) v);
        }
        card.setSelected(!card.isSelected());
    }
}
