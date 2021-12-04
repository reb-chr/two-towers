# two-towers

There are n unique cubes each with face area 1 through n. The program finds the optimal stacking of the blocks in two towers such that the difference in heights between the towers is minimized.

This program uses bitwise operations and counts up to 2^n to represent different possible stackings in binary. For example, with an input of 5, a possible stacking of 3 blocks would be 01101, representing the first, third, and fourth blocks. 

The optimal stacking is found by getting the maximum stack height such that the height does not exceed h/2, where h is the height of all n blocks.

Both the best and second-best solutions are presented.
