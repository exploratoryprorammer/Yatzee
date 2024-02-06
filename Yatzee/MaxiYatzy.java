package hw4;

import static org.junit.Assume.assumeFalse;

import java.util.ArrayList;
import java.util.Random;
import api.GameConfiguration;
import api.ScoreBox;

/**
 * Game state for dice games such as MaxiYatzy. The game includes a
 * <code>GameConfiguration</code> object along with two lists of
 * <code>ScoreBox</code> objects, one for the upper section and one for the lower
 * section.
 * <p>
 * This class is also responsible for maintaining a current Combination (group
 * of dice) and counting the number of rolls. Most of the game state is stored
 * in the associated <code>ScoreBox</code> objects, each of which knows its
 * contribution to the overall score, obtained via its <code>getScore</code>
 * method.
 * 
 * @author Rohan Sah
 */
public class MaxiYatzy {
/**
 * Instance of COnfiguration class
 */
    private GameConfiguration Configuration;
    /**
     * Instance of random class
     */
    private Random random;
    /**
     * ArrayList to store upper scores
     */
    private ArrayList<ScoreBox> upper;
    /**
     * ArrayList to store lower scores
     */
    private ArrayList<ScoreBox> lower;
    /**
     * Instance of combination class
     */
    private Combination currentDice;
    /**
     * player's remaining rolls
     */
    private int remainingRolls;
    /**
     * player's stored rolls
     */
    private int savedrolls;

    /**
     * Constructs a new MaxiYatzy game based on the given configuration. Initially
     * the upper section and lower section lists are empty.
     * 
     * @param config configuration to use for this game
     * @param rand   random number generator to use for rolling dice
     */
    public MaxiYatzy(GameConfiguration config, Random rand) {
        Configuration = config;
        random = rand;
        upper = new ArrayList<ScoreBox>();
        lower = new ArrayList<ScoreBox>();
        currentDice = new Combination(config.getNumDice());
        remainingRolls = config.getMaxRolls();
        savedrolls = 0;
    }

    /**
     * Adds a score box to the lower section of this game.
     * 
     * @param box score box to add
     */
    public void addLowerSectionScoreBox(ScoreBox box) {
        lower.add(box);
    }

    /**
     * Adds a score box to the upper section of this game.
     * 
     * @param box score box to add
     */
    public void addUpperSectionScoreBox(ScoreBox box) {
        upper.add(box);
    }

    /**
     * Returns the list of score boxes in the upper section for this game.
     * 
     * @return list of score boxes in the upper section
     */
    public ArrayList<ScoreBox> getUpperSection() {
        return upper;
    }

    /**
     * Returns the list of score boxes in the lower section for this game.
     * 
     * @return list of score boxes for the lower section
     */
    public ArrayList<ScoreBox> getLowerSection() {
        return lower;
    }

    /**
     * Starts a new turn by creating a new Combination and updating the available
     * rolls according to this game's configuration. If there is already a current
     * Combination that is not complete, this method does nothing.
     */
    public void startTurn() {
        if (currentDice.isComplete() || currentDice.isComplete()) 
        {
        	if(currentDice != null && currentDice.isComplete())
        	{
        		savedrolls = remainingRolls;
        	}
            currentDice = new Combination(Configuration.getNumDice());
            remainingRolls = Configuration.getMaxRolls() + savedrolls; 
            savedrolls = 0;
        }
    }
    /**
     * Returns the remaining number of rolls for this turn. The value returned is
     * always zero if the current Combination is complete, even if the configuration
     * allows unused rolls to be saved for future turns.
     * 
     * @return number of rolls
     */
    public int getRemainingRolls() 
    {
    	if(currentDice.isComplete()&&currentDice!=null)
    	{
    		return 0;
    	}
        return remainingRolls;
    }

    /**
     * Rolls the available dice in the current Combination. That is, the available
     * die values are replaced by randomly generated values in the range 1 through
     * the given maximum (according to this game's configuration). If there are no
     * more remaining rolls, all available dice in the current Combination are
     * moved to the completed state.
     */
    public void rollAvailableDice() {
        if (remainingRolls > 0) {
            currentDice.updateAvailableDice(generateRandomDiceValues());
            remainingRolls--;

            if (remainingRolls == 0) {
                currentDice.chooseAll();
            }
        }
    }
  

    /**
     * Returns the current Combination (group of dice).
     * 
     * @return current group of dice
     */
    public Combination getCurrentDice() {
        return currentDice;
    }

    /**
     * Returns true if the game is over. The game is considered over when all score
     * boxes are filled.
     * 
     * @return true if the game is over, false otherwise
     */
    public boolean isOver() {
        for (ScoreBox box : upper) {
            if (!box.isFilled()) {
                return false;
            }
        }

        for (ScoreBox box : lower) {
            if (!box.isFilled()) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the total score for the filled upper section score boxes (not
     * including the upper section bonus, if any).
     * 
     * @return upper section total score
     */
    public int getUpperSectionTotal() {
        return upper.stream().mapToInt(ScoreBox::getScore).sum();
    }

    /**
     * Returns the total score for the filled lower section score boxes.
     * 
     * @return lower section total score
     */
    public int getLowerSectionTotal() {
        return lower.stream().mapToInt(ScoreBox::getScore).sum();
    }

    /**
     * Returns the total score for all categories including the upper section bonus,
     * if any.
     * 
     * @return total score for all categories
     */
    public int getTotalScore() {
        return getUpperSectionTotal() + getLowerSectionTotal() + getUpperSectionBonus();
    }

    /**
     * Returns the upper section bonus if one is currently being applied, otherwise
     * returns zero.
     * 
     * @return upper section bonus if applicable, otherwise zero
     */
    public int getUpperSectionBonus() {
        if (getUpperSectionTotal() >= Configuration.getUpperSectionBonusCutoff()) {
            return Configuration.getUpperSectionBonus();
        }
        return 0;
    }

    /**
     * Generates an array of random dice values in the range [1, max value].
     * 
     * @return array of random dice values
     */
    private int[] generateRandomDiceValues() {
        int[] values = new int[Configuration.getNumDice()];
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(Configuration.getMaxValue()) + 1;
        }
        return values;
    }
}
