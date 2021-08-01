import java.util.Scanner;

/**
 * This software is intended to demonstrate the 3x+1 problem.
 * 
 * It is defined as a function where in a case a seed is even, then
 * you divide it by 2.  If it is odd, you solve 3x+1, where x is the seed.
 * This will repeat until you begin to observe 4, 2 and 1 being the output
 * repeatedly.
 * 
 * This program has 2 ways of performing this in order to present performance
 * information with respect to time.  A non iterative version will simply
 * report how much time has passed when a seed is set and the function 
 * performs calculations until it begins to repeat 4, 2, and 1.  Hence, one
 * pass of calculations is performed.  Finally the time taken to reach 4, 2, 1,
 * is reported in microseconds.
 * 
 * The second method takes the seed and completes a pass, decrements the seed
 * by 1 and performs it again until it reaches 0 then finally quits.  Then
 * the time for all of the passes are reported in microseconds.
 * @author Chad Chapman
 * @version 1.0
 *
 */
public class Driver 
{
	public static void main(String[] args)
	{
		// Ask user to enter value of seed.
		System.out.print("Enter an Integer: ");
		Scanner input = new Scanner(System.in);
		long seed = input.nextLong();
		System.out.println("");
		
		// Variable to setup feature for allowing user to choose between
		// iterative and non-iterative methods.
		boolean select = false;
		Scanner sel = new Scanner(System.in);
		String mySelection = null;
		
		do 
		{
			// Prompt user to select between iterative version and
			// non-iterative methods.
			System.out.println("Select iterative or non-iterative version.");
			System.out.println("a - iterative");
			System.out.println("b - non-iterative");
			mySelection = sel.next();
			
			// Check if selection is valid.
			if(mySelection.compareTo("a") == 0 || 
					mySelection.compareTo("b") == 0)
			{
				select = true;
			}
		} while(select == false);
		
		// Close input streams.
		input.close();
		sel.close();
		
		// Use mySelection to select which version to use.
		if(mySelection.compareTo("a") == 0)
		{
			iter(seed);
		}
		else if(mySelection.compareTo("b") == 0)
		{
			no_iter(seed);
		}	
	}
	
	
	/**
	 * In this method we do one pass based on the seed without decrementing
	 * the seed.
	 * @param seed The initial seed.
	 */
	public static void no_iter(long seed)
	{	
		// Count the number of steps.
		int count = 0;
		
		// After we reach 1 we don't let it stop.  We let the loop
		// go 2 more times for numbers 4, 2, and 1.
		int test = 0;
		
		Hailstone stone = new Hailstone();
		
		// Time of when we enter method to get how long it takes to complete
		// all calculations.  We start counting time right before we
		// enter the while loop where calculations occur.
		long startTime = System.nanoTime();
		long estimatedTime = 0;
		
		// While test is less than 2 we stay in this loop.
		while(test < 2)
		{
			// Call the method that calculates the 3x+1 function.
			seed = stone.myFunc(seed);
			
			// We begin incrementing when seed = 1.
			if(seed == 1 && test == 0)
			{
				// The first time we reach 1 we grab the system time.
				estimatedTime = System.nanoTime() - startTime;
				count++;
				test++;
			}
			else if(seed == 1 && test > 0)
			{
				test++;
			}
			
			// Before seed reaches 1 for the first time we increment
			// the count long variable to get an accurate number of 
			// steps before we see 1 as an answer for the first time.
			else if (test == 0) 
			{	
				count++;
			}
			
			//We print the result/new seed for next call of myFunc method.
			System.out.println(seed);
			
			// When seed is 1 print some * so we can tell where the
			// second 4,2,1 group is located.
			if(seed == 1) 
			{
				System.out.println("********************");
			}
		}
		
		// We report the number of steps after we exit the while loop.
		System.out.println("It took " + count + " calculations to complete in "
				+ estimatedTime/1000 + " microseconds.");
	}
	
	
	/**
	 * In this method we accept the seed as input and perform many passes
	 * of calculations.  If the seed is 6, we perform one pass, decrement
	 * a long that was set to the initial seed and perform another pass,
	 * and so forth until it reaches 0.
	 * the seed.
	 * @param seed The initial seed.
	 */
	public static void iter(long seed)
	{	
		// After we reach 1 we don't let it stop.  We let the loop
		// go 2 more times for numbers 4, 2, and 1.
		int test = 0;
		
		Hailstone stone = new Hailstone();
		
		// Time of when we enter method to get how long it takes to complete
		// all calculations.  We start counting time right before we
		// enter the while loop where calculations occur.
		long startTime = System.nanoTime();
		
		// We use this value to keep track of how many passes we need to make.
		// It get decremented until it reaches 0.
		long newSeed = seed;
		
		// We use this value to report the original seed in our report.
		long initialSeed = seed;
		
		while(true)
		{
			// While test is less than 2 we stay in this loop.
			while(test < 2)
			{
				seed = stone.myFunc(seed);
				
				// We begin incrementing when seed = 1.
				if(seed == 1)
				{
					test++;
				}
			}
			
			// Decrement i, this will be the value of our new seed for
			// the next pass.
			newSeed--;
			
			// When i is 0 we no longer need to perform calculations.
			// So we exit the while loop.
			if(newSeed == 0)
			{
				break;
			}
			else 
			{
				// Setup the value for the seed for the next pass.
				seed = newSeed;
				
				// We reset this value for the new seed value.
				test = 0;
				System.out.println("New seed:" + seed);
			}
		}
		
		// This is how long it took to complete all passes from initial seed
		// down to 1.
		long estimatedTime = System.nanoTime() - startTime;
		
		// We report how long it took to complete all calculations.
		System.out.println("Starting with an initial seed of " + initialSeed
				+ " and going down to 0,");
		System.out.println("it took " + estimatedTime/1000 + " microseconds");
	}
}
