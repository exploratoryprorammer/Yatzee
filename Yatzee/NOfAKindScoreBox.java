package hw4;

import java.util.Arrays;

import api.ScoreBox;

/**
 * Score box for a given number N of matching dice, where N is specified in the
 * constructor. A Combination satisfies this category only if it has (at least)
 * N dice that are the same. For a Combination that satisfies this category, the
 * score is the sum of the N dice that have the same value. If there are
 * multiple groups of N with the same value, the group with the highest value is
 * used for the score. For example, if N is 3 and the combination is (2, 2, 2,
 * 5, 5, 5, 5, 6), the score is 15.
 * @author Rohan Sah
 */
public class NOfAKindScoreBox extends Yatzeeabstract {

	private int howmany;
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
	 * Constructs a NOfAKindScoreBox with the given display name and score.
	 * 
	 * @param displayName name of this score box
	 * @param howMany     how many dice must match to satisfy this score box
	 */
	public NOfAKindScoreBox(String displayName, int howMany) {
		super(displayName);
		howmany = howMany;
	}
	@Override
	public int getPotentialScore(int[] arr) {
		if(isSatisfiedBy(arr))
		{
			int[] Val = getMatching(arr);
			return Arrays.stream(Val).sum();
		}
		return 0;
	}


	@Override
	public int getScore() {
		if(Filled)
		{
			int[] val = getMatching();
			return Arrays.stream(val).sum();
		}
		return 0;
	}

	@Override
	public boolean isSatisfiedBy(int[] arr) 
	{
		if(howmany>arr.length)
		{
			return false;
		}
		Arrays.sort(arr);
		for (int i = 0; i <= arr.length - howmany; i++) 
	    {
	        if (arr[i] == arr[(i-1) + howmany]) 
	        {
	            return true;
	        }
	    }

	    return false;
	}
		

		
	
	private int[] getMatching() 
	{
	    return getMatching(Dice.getAll());
	}

	private int[] getMatching(int[] arr) 
	{
	    int[] Val = new int[howmany];
	    int maxgroup = Integer.MIN_VALUE;
	    for (int i = 0; i <= arr.length - howmany; i++) {
	        boolean Match = true;

	        for (int j = 1; j < howmany; j++) {
	            if (arr[i] != arr[i + j]) {
	                Match = false;
	                break;
	            }
	        }

	        if (Match && arr[i] > maxgroup) {
	            maxgroup = arr[i];

	            for (int j = 0; j < howmany; j++) {
	                Val[j] = arr[i + j];
	            }
	        }
	    }

	    return Val;
	}
}



