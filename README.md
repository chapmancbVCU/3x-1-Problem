# 3x+1 Problem (Collatz conjecture)

This software is intended to demonstrate how the Collatz conjecture
(3x+1) function is calculated.

It is defined as a function where in a case a seed is even, then
you divide it by 2.  If it is odd, you solve 3x+1, where x is the seed.
This will repeat until you begin to observe 4, 2 and 1 being the output
repeatedly.  The function is a follows:

f(x):
If x mod 2 = 0 then x/2

  If x mod 2 = 1 then 3x+1
 
This program has 2 ways of performing this in order to present performance
information with respect to time.  A non iterative version will simply
report how much time has passed when a seed is set and the function 
performs calculations until it begins to repeat 4, 2, and 1.  Hence, one
pass of calculations is performed.  Finally the time taken to reach 4, 2, 1,
is reported in microseconds.

The second method takes the seed and completes a pass, decrements the seed
by 1 and performs it again until it reaches 0 then finally quits.  Then
the time for all of the passes are reported in microseconds.

For results comparing the M1 and Intel checkout the m1_vs_intel.txt file
in the test directory.
