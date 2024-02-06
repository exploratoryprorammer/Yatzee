package hw4;

import java.util.Arrays;

import api.ScoreBox;

/**
 * Score box that is satisfied by any Combination.
 * The score is the sum of all die values.
 * 
 * @author Rohan Sah
 */
public class ChanceScoreBox extends Yatzeeabstract {

	/*
	 *  Keeps track of if the score box is filled or not
	 */
    private boolean Filled;
    /*
     * Keeps track of Score 
     */
    private Combination Dice;
    /*
     * Name of scoring combination
     */
   private String Name;
    /**
     * Constructs a ChanceScoreBox with the given display name.
     * 
     * @param displayName name for this score box
     */
    public ChanceScoreBox(String displayName) {
        super(displayName);
    }

    @Override
    public void fill(Combination dice) {
    	Dice = dice;
    	Filled = true;
    }
    

    @Override
    public int getPotentialScore(int[] arr) {
        return Arrays.stream(arr).sum();
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        // Chance score box is always satisfied by any combination
        return true;
    }
}
