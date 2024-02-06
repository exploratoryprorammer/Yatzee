package hw4;

import java.util.Arrays;

import api.ScoreBox;

/**
 * Score box for a large straight. A Combination
 * with N dice satisfies this category only if it consists of
 * N distinct consecutive values. For a dice group that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author Rohan Sah
 */
public class LargeStraightScoreBox extends Yatzeeabstract {

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
   private int Points;
    /**
     * Constructs a LargeStraightScoreBox with the given display name
     * and score.
     * 
     * @param displayName name of this score box
     * @param points      points awarded for a dice group that satisfies this score box
     */
    public LargeStraightScoreBox(String displayName, int points) {
        super(displayName);
        Points = points;
    }

    @Override
    public int getScore() 
    {
    	if(Filled)
    	{
    		return Points;
    	}
    	return 0;

    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        if (arr.length < 5) 
        {
        	return false;
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) 
        {
            if (arr[i] != arr[i + 1] - 1) 
            {
                return false;
            }
        }
        return true;

    }
}
