
/**
 * This class has methods that support and perform calculations.  Which method
 * that is used depends on which options the user chooses in the Driver class.
 * @author Chad Chapman
 * @version 1.0
 */
public class CollatzHelper 
{
	
	/**
	 * In this method we accept the seed as input and perform many passes
	 * of calculations.  If the seed is 6, we perform one pass, decrement
	 * a long that was set to the initial seed and perform another pass,
	 * and so forth until it reaches 0.
	 * the seed.
	 * @param seed The initial seed.
	 * 
	 */
	public void iter(long seed)
	{	
		/*
		 * After we reach 1 we don't let it stop.  We let the loop
		 * go 2 more times for numbers 4, 2, and 1.
		 */
		int test = 0;
		
		Hailstone stone = new Hailstone();
		
		/*
		 *  Time of when we enter method to get how long it takes to complete
		 *  all calculations.  We start counting time right before we enter 
		 *  the while loop where calculations occur.
		 */
		long startTime = System.nanoTime();
		
		/*
		 * We use this value to keep track of how many passes we need to make.
		 * It will get decremented until it reaches 0.
		 */
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
			
			/*
			 * Decrement i, this will be the value of our new seed for
			 * the next pass.
			 */
			newSeed--;
			
			/*
			 * When i is 0 we no longer need to perform calculations.
			 * So we exit the while loop.
			 */
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
		
		/*
		 * This is how long it took to complete all passes from initial seed
		 * down to 1.
		 */
		long estimatedTime = System.nanoTime() - startTime;
		
		// We report how long it took to complete all calculations.
		System.out.println("Starting with an initial seed of " + initialSeed
				+ " and going down to 1,");
		System.out.println("it took " + estimatedTime/1000 + " microseconds");
	}
	
	
	/**
	 * In this method we do one pass based on the seed without decrementing
	 * the seed.
	 * @param seed The initial seed.
	 * @return report The time it takes to calculate in microseconds.
	 */
	public long noIter(long seed)
	{	
		// Count the number of steps.
		int count = 0;
		
		/*
		 * After we reach 1 we don't let it stop.  We let the loop
		 * go 2 more times for numbers 4, 2, and 1.
		 */
		int test = 0;
		
		Hailstone stone = new Hailstone();
		
		/*
		 * Time of when we enter method to get how long it takes to complete
		 * all calculations.  We start counting time right before we
		 * enter the while loop where calculations occur.
		 */
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
			
			/*
			 * Before seed reaches 1 for the first time we increment
			 * the count long variable to get an accurate number of 
			 * steps before we see 1 as an answer for the first time.
			 */
			else if (test == 0) 
			{	
				count++;
			}
			
			//We print the result/new seed for next call of myFunc method.
			System.out.println(seed);
			
			/*
			 * When seed is 1 print some * so we can tell where the
			 * second 4,2,1 group is located.
			 */
			if(seed == 1) 
			{
				System.out.println("********************");
			}
		}
		
		long report = estimatedTime/1000;
		
		// We report the number of steps after we exit the while loop.
		System.out.println("It took " + count + " calculations to complete in "
				+ report + " microseconds.");
		
		return report;
	}
	
	
	/**
	 * In this method we do one pass based on the seed without decrementing
	 * the seed.  This version of the non iteration method also has some
	 * optimization features so that there is not call to the Hailstone class
	 * and the logic is also updated to reduce the amount of time it takes to
	 * perform calculations.
	 * @param seed The initial seed.
	 * @return report The time it takes to calculate in microseconds.
	 */
	public long noIterOptimized(long seed)
	{	
		// Count the number of steps.
		int count = 0;
		
		/*
		 * After we reach 1 we don't let it stop.  We let the loop
		 * go 2 more times for numbers 4, 2, and 1.
		 */
		int test = 0;
		
		/*
		 * Time of when we enter method to get how long it takes to complete
		 * all calculations.  We start counting time right before we
		 * enter the while loop where calculations occur.
		 */
		long startTime = System.nanoTime();
		long estimatedTime = 0;
		
		// While test is less than 2 we stay in this loop.
		while(test < 2)
		{
			
			// Perform optimized calculation.  When odd, we solve 3x+1.
			if(seed%2 == 1)
			{
				seed = (3 * seed + 1)/2;
			}
			// When even, we divide by 2.
			else if(seed%2 == 0)
			{
				seed =  seed / 2;
			}
			
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
			
			/* 
			 * Before seed reaches 1 for the first time we increment
			 * the count long variable to get an accurate number of 
			 * steps before we see 1 as an answer for the first time.
			 */
			else if (test == 0) 
			{	
				count++;
			}
			
			//We print the result/new seed for next call of myFunc method.
			System.out.println(seed);
			
			/*
			 * When seed is 1 print some * so we can tell where the
			 * second 4,2,1 group is located.
			 */
			if(seed == 1) 
			{
				System.out.println("********************");
			}
		}
		
		long report = estimatedTime/1000;
		// We report the number of steps after we exit the while loop.
		System.out.println("It took " + count + " calculations to complete in "
				+ report + " microseconds.");
		
		return report;
	}
}
