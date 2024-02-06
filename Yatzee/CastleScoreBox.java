package hw4;

import java.util.Arrays;

import api.ScoreBox;

/**
 * Score box that is satisfied by a Combination including at least three dice of one value
 * and three of a different value. The score is the sum of all die values.
 * 
 * @author Rohan Sah
 */
public class CastleScoreBox extends Yatzeeabstract {

    /**
     * Constructs a CastleScoreBox with the given display name.
     * 
     * @param displayName name for this score box
     */
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
    public CastleScoreBox(String displayName) {
        super(displayName);
    }

    @Override
    public boolean isSatisfiedBy(int[] arr) {
        if (arr.length >= 6) {
            // Sort the array to simplify the comparison
            Arrays.sort(arr);

            // Check for at least three dice of one value and three of a different value
            int count1 = 1;
            int count2 = 1;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == arr[i - 1]) {
                    count1++;
                } else {
                    count1 = 1;
                }

                if (arr[i] != arr[i - 1]) {
                    count2 = 1;
                } else {
                    count2++;
                }

                if (count1 >= 3 && count2 >= 3) {
                    return true;
                }
            }
        }
        return false;
    }
}


