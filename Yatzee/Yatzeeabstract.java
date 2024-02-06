package hw4;

import java.util.Arrays;

import api.ScoreBox;

public abstract class Yatzeeabstract implements ScoreBox {
	/**
	 * @author Rohan Sah
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

    public Yatzeeabstract(String displayName) {
        Filled = false;
        Dice = null;
        Name = displayName;
    }

    @Override
    public boolean isFilled() {
        return Filled;
    }

    @Override
    public int getScore() 
    {
    	if (Filled) 
        {
            return Arrays.stream(Dice.getAll()).sum();
        }
        return 0;
    }

    
    @Override
    public Combination getDice() {
        return Dice;
    }

    @Override
    public String getDisplayName() {
        return Name;
    }

    
    @Override
    public void fill(Combination dice) {
        if(!isSatisfiedBy(Dice.getAll()))
        {
        	Dice = dice;
        	Filled = true;
        }
        else
        {
            throw new IllegalStateException("The condition was not met.");

        }
    }

    @Override
    public abstract boolean isSatisfiedBy(int[] arr);
    @Override
    public int getPotentialScore(int[] arr) {
    	if(isSatisfiedBy(arr))
    	{
    		return Arrays.stream(arr).sum();
    	}
    	return 0;
    }
}
