package com.example.javier.whatanimalareyou.model;

import java.util.HashMap;

/**
 * Created by Javier on 10/4/2016.
 */

public class Statement {

    private String mText;
    private HashMap<Integer, AnimalBase> mChoiceAnimalMap;

    public Statement(String text){

        mText = text;
        mChoiceAnimalMap = new HashMap<>();
    }

    public String getText() {
        return mText;
    }
}
