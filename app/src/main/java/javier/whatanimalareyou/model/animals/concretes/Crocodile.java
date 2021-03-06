package javier.whatanimalareyou.model.animals.concretes;

import javier.whatanimalareyou.model.animals.AnimalBase;
import javier.whatanimalareyou.model.choices.ChoicePoints;

import java.util.ArrayList;
/**
 * Created by Javi on 10/10/2016.
 */

public class Crocodile extends AnimalBase {

    public Crocodile(int imageId) {
        super("Crocodile", imageId);

        mPointsPerStatement = new ArrayList<Integer>(){
            {
                add(ChoicePoints.FOUR);
                add(ChoicePoints.FIVE);
                add(ChoicePoints.FIVE);
                add(ChoicePoints.ONE);
                add(ChoicePoints.FIVE);
            }
        };
    }
}
