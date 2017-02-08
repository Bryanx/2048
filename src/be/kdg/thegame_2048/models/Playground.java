package be.kdg.thegame_2048.models;

import java.util.Random;

/**
 * @author Jarne Van Aerde
 * @version 1.0 8/02/2017 17:44
 */
public class Playground {
    //EIGENSCAPPEN
    private static final Random blockGen = new Random();
    private static final int NUMBER_OF_SECTIONS = 16;
    private Section[] sections;
    private Score score;

    //CONSTRUCTORS
    public Playground(Score score) {
        this.score = score;
        this.sections = new Section[NUMBER_OF_SECTIONS];
        initialiseSections();
    }

    //METHODEN
    private void initialiseSections() {
        for (int i = 0; i < NUMBER_OF_SECTIONS; i++) {

        }
    }

}
