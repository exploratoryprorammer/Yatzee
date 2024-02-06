package hw4;

import api.ScoreBox;

/**
 * Score box for all dice the same.  A Combination
 * with N dice satisfies this category only if all N
 * values are the same.  For a Combination that satisfies
 * this category, the score is a fixed value specified in the constructor;
 * otherwise, the score is zero.
 * 
 * @author Rohan Sah
 */
// TODO: this class must implement ScoreBox or extend another class that does
public class AllMatchScoreBox extends Yatzeeabstract {

    private int Points;
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
     * Constructs an AllMatchScoreBox with the given display name
     * and score.
     * 
     * @param displayName name of this score box
     * @param points      points awarded for a combination that satisfies this score box
     */
    public AllMatchScoreBox(String displayName, int points) {
        super(displayName);
        Points = points;
    }

    
    @Override
    public int getPotentialScore(int[] arr) {
        if (isSatisfiedBy(arr)) 
        {
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
        if (arr.length == 0) {
            return false;
        }
        int firstValue = arr[0];
        for (int value : arr) {
            if (value != firstValue) {
                return false;
            }
        }
        return true;
    }
}
