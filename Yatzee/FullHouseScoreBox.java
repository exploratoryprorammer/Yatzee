package hw4;

import java.util.Arrays;

import api.ScoreBox;

/**
 * Score box that is satisfied by a Combination including
 * at least three dice of one value and two of a different value.
 * The score is the sum of all die values.
 * 
 * @author Rohan Sah
 */
public class FullHouseScoreBox extends Yatzeeabstract {
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
     * Constructs a FullHouseScoreBox with the given display name.
     * 
     * @param displayName name for this score box
     */
    public FullHouseScoreBox(String displayName) {
        super(displayName);
        Dice = null;
    }
  
    @Override
    public boolean isSatisfiedBy(int[] arr) 
    {
    	if(arr.length<5)
    	{
    		return false;
    	}
    	Arrays.sort(arr);
    	return (arr[0] == arr[1] && arr[1] == arr[2] && arr[3] == arr[4]) || (arr[0] == arr[1] && arr[2] == arr[3] && arr[3] == arr[4]);
        
    }
}
