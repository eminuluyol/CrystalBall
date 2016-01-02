package com.taurus.crystalball;

import java.util.Random;

/**
 * Created by Emin on 12/1/2015.
 */
public class CrystalBall {
    // Member variables (properties about the object)
    String[] mAnswers = {
            "It is certain",
            "It is decidedly so",
            "All signs say yes",
            "The stars are not aligned",
            "My reply is no",
            "It is doubtful",
            "Better not tell you know",
            "Concentrate and ask again",
            "Unable to answer now" };

    public String getAnAnswer(){

        //The button was clicked so update the answer label with an answer
        String answer = "";
        //Randomly selects one of three answers. Yes, No or Maybe
        Random randomGenerator = new Random();//Construct a new random generator
        int randomNumber = randomGenerator.nextInt(mAnswers.length);

        answer =  mAnswers[randomNumber];

        return  answer;

    }
}
