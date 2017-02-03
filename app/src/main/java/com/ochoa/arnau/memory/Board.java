package com.ochoa.arnau.memory;

import android.content.res.Resources;
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

    int points, matchedPairs;
    boolean isFirst = true;

    int pairs;

    Card[] cards;
    Card lastCard = new Card(0);

    CoolImageFlipper flipper;
    Resources resources;

    Integer cardBack;
    Integer[] drawables; //Array with all drawables id
    Integer[] values; //Drawables after duplicate values and shuffle


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
    }

    public boolean select(View v,int pos){
        boolean win = false;
        if (!cards[pos].isSelected()){
            flip(v, cards[pos]);
            win = check(v, cards[pos]);
            points++;
        }
        return win;
    }

    private boolean check(View v, Card thisCard) {
        boolean win = false;
        if (lastCard.getValue() == thisCard.getValue()){
            matchedPairs++;
        }else{
            flip(v, thisCard);
            flip(v, lastCard);
        }
        isFirst = !isFirst;

        if (matchedPairs == (values.length/2)){
            win = true;
        }
        return win;
    }

    private void flip(View v,Card thisCard) {
        if(thisCard.isSelected()){
            flipper.flipImage(resources.getDrawable(cardBack), (ImageView) v);
        }else{
            int val = thisCard.getValue();
            flipper.flipImage(resources.getDrawable(val), (ImageView) v);
        }
        thisCard.setSelected(!thisCard.isSelected());
    }
}
