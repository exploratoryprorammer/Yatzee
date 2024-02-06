package hw4;

import api.ScoreBox;

import java.util.Arrays;

/**
 * Score box for a short straight. A Combination
 * with N dice satisfies this category only if it includes
 * N - 1 distinct consecutive values. For a dice group that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 */
/**
 * @author Rohan Sah
 */
public class ShortStraightScoreBox extends Yatzeeabstract {

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
/*
 * points collcted
 */
    private int Points;

    /**
     * Constructs a ShortStraightScoreBox with the given display name
     * and score.
     *
     * @param displayName name of this score box
     * @param points      points awarded for a dice group that satisfies this score box
     */
    public ShortStraightScoreBox(String displayName, int points) {
        super(displayName);
        Points = points;
    }


    @Override
    public int getPotentialScore(int[] arr) {
        if (isSatisfiedBy(arr)) {
            return Points;
        }
        return 0;
    }

    @Override
    public int getScore() {
        if(Filled)
        {
        	return Points;
        }
        return 0;
        
    }

 
    @Override
    public boolean isSatisfiedBy(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] != 1) 
            {
            	return false;
                
            }
        }
        return true;
    }
}


