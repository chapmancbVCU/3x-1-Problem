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
 * @version 1.1
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
			System.out.println("c - non-iterative average");
			System.out.println("d - non-iterative optimized");
			System.out.println("e - non-iterative average optimized");
			mySelection = sel.next();
			
			// Check if selection is valid.
			if(mySelection.compareTo("a") == 0 || 
					mySelection.compareTo("b") == 0 ||
					mySelection.compareTo("c") == 0 ||
					mySelection.compareTo("d") == 0 ||
					mySelection.compareTo("e") == 0)
			{
				select = true;
			}
		} while(select == false);
		
		// Close input streams.
		input.close();
		sel.close();
		
		CollatzHelper helper = new CollatzHelper();
		// Use mySelection to select which version to use.
		if(mySelection.compareTo("a") == 0)
		{
			helper.iter(seed);
		}
		else if(mySelection.compareTo("b") == 0)
		{
			helper.noIter(seed);
		}
		else if(mySelection.compareTo("c") == 0)
		{
			// Time we will perform calculations before calculating average.
			// We use long in order to not mix primitive data types when we
			// perform sum/max operation.
			long max = 1000;
			
			// The sum we will create to divide by max to get average.
			long sum = 0;
			
			for(long i = 0; i < max; i++)
			{
				sum = sum + helper.noIter(seed);
				System.out.println();
			}
			System.out.println("Average = " + sum/max + " microseconds");
		}
		else if(mySelection.compareTo("d") == 0)
		{
			helper.noIterOptimized(seed);
		}
		else if(mySelection.compareTo("e") == 0)
		{
			// Time we will perform calculations before calculating average.
			// We use long in order to not mix primitive data types when we
			// perform sum/max operation.
			long max = 1000;
			
			// The sum we will create to divide by max to get average.
			long sum = 0;
			
			for(long i = 0; i < max; i++)
			{
				sum = sum + helper.noIterOptimized(seed);
				System.out.println();
			}
			System.out.println("Average = " + sum/max + " microseconds");
		}
	}
}
