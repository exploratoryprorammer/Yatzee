package hw4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Group of dice for a dice game such as MaxiYatzy in which 
 * multiple rolls per turn are allowed.  The dice are partitioned
 * into "available" dice and "complete" dice.  The available
 * dice can be re-rolled (that is, they can get new values via the
 * <code>update</code> method). The client can select which dice 
 * to move from available to complete.
 * Once all dice are complete, as determined
 * by the <code>isComplete()</code> method, the <code>getAvailable()</code>
 * method always returns an empty array, and the state of the
 * combination can no longer be modified.
 * 
 * @author Rohan Sah
 */
public class Combination
{  
  /**
   * Constructs a new Combination in which each die initially has 
   * the (invalid) value zero and all dice are available. In normal usage, 
   * a client would replace the available die values with randomly generated
   * numbers in an appropriate range, to simulate rolling the dice.
   * @param numDice
   *   number of dice in this group
   *   @author Rohan Sah
   */
	/**
	 * Status of available dice 
	 */
	private boolean[] Available;
	/*
	 * Values of dice
	 */
	private int[] dice;
	
  public Combination(int numDice)
  {

	  dice = new int[numDice];
      Available = new boolean[numDice];
      Arrays.fill(Available, true);
  
  }   
  /**
   * Constructs a new Combination in which each die initially has 
   * the value given by the initialValues array.
   * All dice are initially available. The number of dice is determined
   * by the size of the given array.
   * This version of the constructor is primarily intended for testing.
   * @param initialValues
   *   initial values for the dice
   */
  public Combination(int[] initialValues)
  {
      dice = Arrays.copyOf(initialValues, initialValues.length);
      Available = new boolean[dice.length];
      Arrays.fill(Available, true);

  }  
  
  /**
   * Returns the number of dice in this group.
   * @return
   *   number of dice in this group
   */
  public int getNumDice()
  {
	  return dice.length;
  }

  /**
   * Selects a die value to be moved from the available dice to the
   * completed dice. Has no effect if the given value is 
   * not among the values in the available dice. 
   * @param value
   *   die value to be moved
   */
  public void choose(int value)
  {
      for (int i = 0; i < dice.length; i++) 
      {
          if (Available[i] && dice[i] == value) 
          {
              Available[i] = false;
              return;
          }
      }
    
  }

  /**
   * Causes all die values be moved from the available dice to the
   * completed dice. After this method is called, <code>getAvailable</code>
   * always returns an empty array, and <code>isComplete</code> returns true.
   */
  public void chooseAll()
  {
      Arrays.fill(Available, false);
  }
  
  /**
   * Determines whether there are any dice available to be 
   * rolled in this combination.
   * @return
   *   true if there are no available dice, false otherwise
   */
  public boolean isComplete()
  {
	  for(boolean val: Available)
	  {
		  if(val)
		  {
			  return false;
		  }
	  }
	  return true;
  }
  /**
   * Returns the values of the dice that are not available
   * to be re-rolled, in ascending order.
   * @return
   *   values of the dice that are not available to be re-rolled
   */
  public int[] getCompletedDice()
  {
      int[] completed = new int[dice.length - countAvailableDice()];
      int index = 0;
      for (int i = 0; i < dice.length; i++) 
      {
          if (!Available[i]) 
          {
              completed[index] = dice[i];
              index++;
          }
      }
      Arrays.sort(completed);
      return completed;

  }
 
  /**
   * Returns all die values in this combination, in ascending order.
   * @return
   *   all die values in this group
   */
  public int[] getAll()
  {
      int[] Val = Arrays.copyOf(dice, dice.length);
      Arrays.sort(Val);
      return Val;
  }
  
  /**
   * Replaces the available dice with the given values.
   * Throws an IllegalStateException if the length of
   * the given array does not match the number of available
   * dice in this Combination.
   * @param newValues 
   *   array of new die values for available dice
   * @throws IllegalStateException
   *   if the given array has the wrong length
   */
  public void updateAvailableDice(int[] newValues)
  {
      if (newValues.length != countAvailableDice()) 
      {
          throw new IllegalStateException("Invalid array length");
      }
      int in = 0;
      for (int i = 0; i < dice.length; i++) 
      {
          if (Available[i]) 
          {
              dice[i] = newValues[in];
              in++;
          }
      }

  }
  /**
   * Returns the values of the dice that are available to
   * be re-rolled, in ascending order.
   * @return
   *   dice that are available to be re-rolled
   */
  public int[] getAvailableDice()
  {
      int[] a = new int[countAvailableDice()];
      int index = 0;
      for (int i = 0; i < dice.length; i++) 
      {
          if (Available[i]) 
          {
              a[index] = dice[i];
              index++;
          }
      }
      Arrays.sort(a);
      return a;

  }
  /**
   * 
   */
  protected int countAvailableDice() 
  {
	    int c = 0;
	    for (boolean avail : Available) {
	        if (avail) 
	        {
	            c++;
	        }
	    }
	    return c;
      
  }

  /**
   * Returns a string representation of the die values in
   * this combination, in the form <em>available</em>(<em>complete</em>).
   * (For example, if there are two completed dice with values 2
   * and 4, and there are 3 available dice with values 1, 1, and 6,
   * then the method returns the string
   * <pre>
   * 1 1 6 (2 4)
   * </pre>
   * @return 
   *   string representation of the available and completed dice,
   *   with the completed values in parentheses
   */
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    int[] avail = getAvailableDice();
    int[] completed = getCompletedDice();
    if (avail.length > 0)
    {
      for (int value : avail)
      {
        sb.append(value + " ");
      }
    }
    sb.append("(");
    if (completed.length > 0)
    {
      // use an index so we can add the first one without a leading space
      sb.append(completed[0]);
      for (int i = 1; i < completed.length; ++i)
      {
        sb.append(" " + completed[i]);
      }
    }
    sb.append(")");
    return sb.toString();
  }
  
}

