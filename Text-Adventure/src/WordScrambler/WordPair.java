package WordScrambler;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * A WordPair encapsulates two representations
 * of a given word - its "real" form, which does not change,
 * and a "scrambled" form in which the letters are rearranged.
 * The scrambled form of the word may be updated by calls to the
 * methods <code>moveLeft</code>, <code>moveRight</code>,
 * <code>doLetterHint</code>, and <code>rescramble</code>.
 */
public class WordPair
{
	private String realWord;
	private String scrambledWord;
	private int hint;
	
	  /**
	   * Constructs an instance of WordPair with the given strings.
	   * This constructor does
	   * not check whether the given scrambled word is a valid
	   * rearrangement of the given real word, but this can be
	   * checked by a subsequent call to the method
	   * <code>isSolutionPossible</code>.
	   * @param givenRealWord
	   *   the real word
	   * @param givenScrambledWord
	   *   the word with characters rearranged
	   */
	public WordPair(String givenRealWord, String givenScrambledWord)
	{
		int hint = 0;
		realWord = givenRealWord.toUpperCase();
		scrambledWord = givenScrambledWord.toUpperCase();
		
	}
	
	/**
	   * Returns the current scrambled form of the word
	   * in upper case.
	   * @return
	   *   scrambled form of the word.
	   */
	public String getScrambledWord()
	{
		return scrambledWord.toUpperCase();
	}
	
	/**
	   * Returns the real word in upper case.
	   * @return
	   *   the real word
	   */
	public String getRealWord()
	{
		return realWord.toUpperCase();
	}
	
	/**
	   * Modifies the scrambled word by moving the character at position 
	   * <code>index</code> to the right the given number of spaces. 
	   * Intervening characters are shifted left.  For example, if the
	   * scrambled word is "ABCDEFG", then after moveRight(2, 3) the
	   * scrambled word should be "ABDEFCG".  This method should do 
	   * nothing if the index is out of range, if <code>howFar</code>
	   * is negative, if the index plus <code>howFar</code> is out of range,
	   * or if the index is less than <code>getNumLetterHints()</code>.
	   * @param index
	   *   zero-based index of the character to be moved
	   * @param howFar
	   *   number of spaces to the right to move the character
	   */
	public void moveRight(int index,int howFar)
	{
		if (index<0||index>scrambledWord.length()||howFar<0||index+howFar>=scrambledWord.length()||index+howFar<0||index<getNumLetterHints()||howFar+index<getNumLetterHints())
		{
			return;
		}else{
		    StringBuilder sb = new StringBuilder(getScrambledWord());
			for(int i=0;i<howFar;i++)
			{
				char temp = sb.charAt(index);
				char temp2 = sb.charAt(index+1);
				sb.setCharAt(index, temp2);
				sb.setCharAt(index+1, temp);
				index++;
			}
			scrambledWord = sb.toString();
		}
			
		
	}
	
	/**
	   * Modifies the scrambled word by moving the character at position 
	   * <code>index</code> to the left the given number of spaces. 
	   * Intervening characters are shifted right.  For example, if the
	   * scrambled word is "ABCDEFG", then after moveLeft(5, 3) the
	   * scrambled word should be "ABFCDEG".  This method should do 
	   * nothing if the index is out of range, if <code>howFar</code>
	   * is negative, if the index minus <code>howFar</code> is out of range,
	   * or if the index index minus <code>howFar</code> is less than 
	   * <code>getNumLetterHints()</code>.
	   * @param index
	   *   zero-based index of the character to be moved
	   * @param howFar
	   *   number of spaces to the left to move the character
	   */
	  public void moveLeft(int index, int howFar)
	{
		  if(index<0||index>scrambledWord.length()||howFar<0||index-howFar<0||index<getNumLetterHints()||index-howFar<getNumLetterHints())
			{
				return;
			}
		
		else
		{			
				 StringBuilder sb = new StringBuilder(getScrambledWord());
					for(int i=0;i<howFar;i++)
					{
						char temp = sb.charAt(index);
						char temp2 = sb.charAt(index-1);
						sb.setCharAt(index, temp2);
						sb.setCharAt(index-1, temp);
						index--;
					}
					scrambledWord = sb.toString();

		}
		
	}
	

	  /**
	   * Returns the number of letters at the beginning of the scrambled word whose
	   * positions are currently fixed by previous calls to <code>letterHint()</code>.
	   * @return
	   *   number of fixed characters at the beginning of the scrambled word
	   */
	  public int getNumLetterHints()
	  {
	    return hint;
	  }
	  
	  /**
	   * Applies a hint by placing a correct character at the position
	   * <code>p</code>, where <code>p = getNumLetterHints()</code> is the number
	   * of characters that have already been placed in the correct position
	   * by previous calls to this method.
	   * Specifically, this method searches to the right starting at index <code>p</code>
	   * until it finds the first occurrence of the correct character, 
	   * and then swaps that character into position <code>p</code>.
	   * After this method has been called n times, the first n letters
	   * of the scrambled word are correct and are not moved again by any
	   * subsequent method calls. 
	   * <p>
	   * For example: if the real word is "AARDVARK" and the scrambled word
	   * is "DVRAKARA", then after one call to <code>doLetterHint</code>,
	   * the scrambled word is "AVRDKARA"  (D is swapped with first A).
	   * After two calls to <code>doLetterHint</code>, the scrambled word is "AARDKVRA"
	   * (V is swapped with the second A).  After three calls to <code>doLetterHint</code>,
	   * the scrambled word is still "AARDKVRA" (R is swapped with itself).  However
	   * <code>getNumLetterHints</code> will now return 3, and the characters
	   * at indices 0, 1, and 2 will be not be moved.
	   */
	  public void doLetterHint()
	  {
		  int index = getNumLetterHints();
		  if (index>scrambledWord.length()-1)
		  {
			  return;
		  }
		 
		  StringBuilder tem = new StringBuilder(getScrambledWord());
		  
		  char comp = realWord.charAt(index);
		  for(int i = index; i< scrambledWord.length(); i++)
		  {
			  if (comp == tem.charAt(i))
			  {
				  char temp = tem.charAt(index);
				  char temp2 = tem.charAt(i);
				  tem.setCharAt(index, temp2);
				  tem.setCharAt(i, temp);
				  hint++;
				  break;
			  }
			  
		  }
		  
		  scrambledWord = tem.toString();
		 
	  }
	  
	  /**
	   * Rescrambles the non-fixed letters in the scrambled using the 
	   * given permutation generator.  Letters to the left of the index
	   * <code>getNumLetterHints()</code> are not moved. This method should
	   * update the scrambled word with the value of
	   * <pre>
	   * WordScrambler.scramble(getScrambledWord(), getNumLetterHints(), gen);
	   * </pre>
	   * )
	   * @param gen
	   *   permutation generator for scrambling the non-fixed letters
	   */
	  public void rescramble(PermutationGenerator gen)
	  {
		  scrambledWord = WordScrambler.scramble(getScrambledWord(), getNumLetterHints(), gen);
	  }

	  /**
	   * Determines whether the scrambled word is a valid rearrangement
	   * of the real word (disregarding case).
	   * @return
	   *   true if it is possible to rearrange the characters in the
	   *   scrambled word to obtain the real word
	   */
	  public boolean isSolutionPossible()
	  {
	    StringBuilder tem = new StringBuilder(scrambledWord);
	    if(tem.length()!=realWord.length()){
	    	return false;
	    }
	    
	    for(int i=0;i<scrambledWord.length();i++)
	    {
	    	for (int j=i; j<scrambledWord.length();j++)
	    	{
	    		if (realWord.charAt(i)==tem.charAt(j))
	    		{
	    			char cross = tem.charAt(i);
	    			char temp =  tem.charAt(j);
	    			tem.setCharAt(i,temp);
	    			tem.setCharAt(j,cross);
	    			break;
	    		}
	    	}
	    }
	    
	    String comp = tem.toString();
	    if (comp.equals(realWord))
	    {
	    	return true;
	    }
	    else 
	    	return false;
	  }

	  /**
	   * Determines whether the current scrambled word is 
	   * equal to the real word (disregarding case).
	   * @return
	   *   true if scrambled word and real word are the same,
	   *   false otherwise
	   */
	  public boolean checkSolution()
	  {
	    if (getScrambledWord().equals(getRealWord()))
	    	return true;
	    else
	    	return false;
	  }
	  
	  /**
	   * Determines whether the given string is 
	   * equal to the real word (disregarding case).
	   * Does not modify the scrambled word.
	   * @return
	   *   true if given word and real word are the same,
	   *   false otherwise
	   */
	  public boolean checkSolution(String solution)
	  {
	    if (solution.toUpperCase().equals(getRealWord()))
	    	return true;
	    else
	    	return false;
	  }
	}

