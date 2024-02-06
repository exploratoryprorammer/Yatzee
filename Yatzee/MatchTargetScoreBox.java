package hw4;

import java.util.Arrays;

import api.ScoreBox;

public class MatchTargetScoreBox extends Yatzeeabstract {

    private int Value;
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

    public MatchTargetScoreBox(String displayName, int whichValue) {
        super(displayName);
        Value = whichValue;
    }
    @Override
    public int getScore()
    {
        if (Filled) 
        {
            return Arrays.stream(Dice.getCompletedDice())
                    .filter(value -> value == Value)
                    .sum();
            
        }
        return 0;
    }

    @Override
    public void fill(Combination dice)
    {
    	// TODO Auto-generated method stub
        if (!dice.isComplete()) 
        {
            throw new IllegalStateException("Combination is not complete.");
        }
        Dice = dice;
        Filled = true;
    }


    @Override
    public int getPotentialScore(int[] arr) {
        int potentialScore = 0;
        for (int value : arr) {
            if (value == Value) {
                potentialScore += value;
            }
        }
        return potentialScore;
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        return Arrays.stream(arr).anyMatch(value -> value == Value);

    }
    
}
