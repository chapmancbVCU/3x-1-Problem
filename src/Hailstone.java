/**
 * This class contains one method that implements the function
 * of the 3x+1 (hail stone) problem.
 * @author Chad Chapman
 * @version 1.1
 * 
 */
public class Hailstone 
{
	/**
	 * Default constructor
	 */
	public Hailstone()
	{
		
	}
	
	/**
	 * The main implementation of the 3x+1 problem.
	 * @param seed The value that is given to the function.
	 * @return The answer for the 3x+1 problem.
	 */
	public long myFunc(long seed)
	{
		/*
		 * We need this value otherwise things in the Driver class break
		 * and keep printing 0 after we reach certain conditions.
		 */
		long answer = 0;
		
		
		// When odd, we solve 3x+1.
		if(seed%2 == 1)
		{
			answer = 3 * seed + 1;
		}
		// When even, we divide by 2.
		else if(seed%2 == 0)
		{
			answer =  seed / 2;
		}
		
		return answer;
	}
}
