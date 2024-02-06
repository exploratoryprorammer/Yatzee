package hw4;

import api.ScoreBox;

import java.util.Arrays;

/**
 * Score box that is satisfied by a Combination including
 * at least two dice of one value and two of a different value.
 * The score is the sum of all die values.
 * @author Rohan Sah
 */
public class TwoPairsScoreBox extends Yatzeeabstract {

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
     * Constructs a TwoPairsScoreBox with the given display name.
     *
     * @param displayName name for this score box
     */
    public TwoPairsScoreBox(String displayName) {
        super(displayName);
    }



    @Override
    public boolean isFilled() {
        return Filled;
    }

 
    @Override
    public boolean isSatisfiedBy(int[] arr) {
    	if(arr.length<6)
    	
    	return false;
 
    Arrays.sort(arr);
    return(arr[4]==arr[5]&&arr[0]==arr[1]&&arr[1] == arr[2] && arr[2] == arr[3]);
    }
}


