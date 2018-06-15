package WordScrambler;

/**
 * A ScoreCalculator keeps 
 * track of the score calculation for a 
 * time-based word puzzle solving game.  The score starts out
 * at a maximum value determined by the difficulty of the puzzle
 * and decreases as time passes.  The calculator is initialized
 * for a round of the game by calling <code>start</code>.  
 * Thereafter, the current score can be obtained by calling
 * <code>getPossibleScore(int elapsedMillis)</code>.  Note that this
 * class only performs score calculation and does not do any
 * timekeeping; it is up to the caller to keep track of the 
 * actual time and provide the elapsed time in the 
 * <code>elapsedMillis</code> argument. 
 * <p>
 * For this implementation, the initial maximum score is always
 * the length of the word times the fixed value 
 * <code>millisPerLetter</code>.  The score is reduced by 
 * 1 for each elapsed millisecond. The score may also
 * be reduced by penalties for incorrect guesses or hints.
 */
public class ScoreCalculator
{
	private int millisPerLetter,hintPenalty,rescramblePenalty, incorrectGuessPenalty;
	private int maxScore; 
	private int wordLength;
	private int hintCount = 0;
  
  /**
   * Constructs a ScoreCalculator for which the initial maximum score
   * is given by <code>millisPerLetter</code> times the number of
   * letters in a given word. 
   * @param millisPerLetter
   *   factor for determining initial maximum score
   * @param hintPenalty
   *   score penalty imposed for getting a hint in the game
   * @param incorrectGuessPenalty
   *   score penalty imposed for submitting an incorrect solution in the game
   */
  public ScoreCalculator(int millisPerLetter, int hintPenalty, int rescramblePenalty, int incorrectGuessPenalty)
  {
    this.millisPerLetter = millisPerLetter;
    this.hintPenalty = hintPenalty;
    this.rescramblePenalty = rescramblePenalty;
    this.incorrectGuessPenalty = incorrectGuessPenalty;
    
    maxScore = 0;
    wordLength = 0;
    
  }
  
  /**
   * Initializes this score calculator based on the given word length to start
   * a new round. The maximum possible score is <code>millisPerLetter</code> times
   * the word length.
   * @param wordLength
   *   length of the word for the current round
   */
  public void start(int wordLength, int maxScore)
  {
	  
    this.wordLength = wordLength;
    this.maxScore = maxScore;
    hintCount = 0;
    
  }


  /**
   * Returns the score the player would receive if the puzzle 
   * is solved in the given number of milliseconds.  The returned
   * value is the initial maximum score, less the given number
   * of milliseconds, less any accumulated penalties.  However, the
   * returned value is never less than zero.
   * @param elapsedMillis
   *   number of milliseconds it has taken the player so far 
   * @return
   *   current score for the given milliseconds
   */
  public int getPossibleScore()
  {
	return maxScore;
  }
  
  /**
   * Imposes the penalty for obtaining a hint.  This value will
   * be subtracted from the next call to <code>getScore</code>.
   */
  public void applyHintPenalty()
  {
	  hintCount++;
	  
	  if (hintCount > 3)
		  maxScore -= 5;

  }

  /**
   * Imposes the penalty for rescrambling.  This value will
   * be subtracted from the next call to <code>getScore</code>.
   */
  public void applyRescramblePenalty(int attemptLeft)
  {
		if (attemptLeft == 3)
		{
			maxScore--;
		}
		else if (attemptLeft == 2)
			maxScore = maxScore - 2;
		else if (attemptLeft == 1)
			maxScore = maxScore - 4;
  }

  /**
   * Imposes the penalty for an incorrect guess.  This value will
   * be subtracted from the next call to <code>getScore</code>.
   */
  public void applyIncorrectGuessPenalty()
  {
    maxScore -= incorrectGuessPenalty;
  }

}
