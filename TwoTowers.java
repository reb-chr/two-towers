// I am the sole author of the work in this repository.
import java.util.*;

/**
* TwoTowers takes n blocks with face areas integers between 1 and n. The findBestSets() method
* uses a SubsetIterator to find the first and second-best stackings of the blocks such that the
* heights of the two towers are as close as possible.
*/
public class TwoTowers {
  // the heights of all n blocks
  private Vector<Double> blocks;
  // half of the height of all n blocks
  private double halfHeight;

  // the best sets of blocks and corresponding heights of the sets
  private Vector<Double> bestSet;
  private Vector<Double> secondBestSet;
  private double bestHeight;
  private double secondBestHeight;

  /**
  * Constructor for TwoTowers
  * @param n the total number of blocks to be split into two towers
  * @pre n is not null
  * @post blocks is filled with the sqrt of each integer from 1 to n
  * @post halfHeight, the height of all the blocks, divided by 2, is set
  * @post bestSet and secondBestSet are initialized to empty vectors of size n
  * @post bestHeight and secondBestHeight are initialized to 0.0
  */
  public TwoTowers(int n) {
    blocks = new Vector<Double>(n);
    halfHeight = 0.0;

    // fill vector heights and add to halfHeight
    for (int i = 1; i <= n; i++) {
      blocks.add(Math.sqrt(i));
      halfHeight += Math.sqrt(i);
    }

    halfHeight /= 2;

    // initialize best sets
    bestSet = new Vector<Double>(n);
    secondBestSet = new Vector<Double>(n);
    bestHeight = 0.0;
    secondBestHeight = 0.0;
  }

  /**
  * Finds the best and second-best left stacks of blocks such that the height of the stacks are
  * as close to as possible, but less than, halfHeight.
  * @post bestSet and secondBestSet are the best and second-best stacks with heights
  * as close as possible, but less than, halfHeight
  * @post bestHeight and secondBestHeight are the heights of bestSet and secondBestSet, respectively
  */
  public void findBestSets() {
    // create a new SubsetIterator to iterate through subsets of blocks
    SubsetIterator<Double> subsetIter = new SubsetIterator<Double>(blocks);
    while (subsetIter.hasNext()) {
      Vector<Double> stack = subsetIter.next();
      // calculate the sum of the heights of blocks in the stack
      double sum = 0.0;
      for (int i = 0; i < stack.size(); i++) {
        sum += stack.get(i);
      }
      // only check left stacks, so sum must be <= halfhHeight
      if (sum <= halfHeight) {
        // if sum is better than bestHeight, change references accordingly
        // stacks of equal height are ignored (passed along to secondBestHeight)
        if (sum > bestHeight) {
          secondBestHeight = bestHeight;
          secondBestSet = bestSet;
          bestHeight = sum;
          bestSet = stack;
        // if sum is not better than bestHeight, but is better than secondBestHeight, change references
        } else if (sum > secondBestHeight) {
          secondBestSet = stack;
          secondBestHeight = sum;
        }
      }
    }
  }

  /**
  * Prints out the best left stack.
  * @pre bestSet is not empty
  */
  public void printBestSet() {
    if (bestSet.isEmpty()) {
      System.out.println("Not enough blocks for a best set.");
      System.exit(0);
    }
    System.out.print("[");
    // print first n-1 blocks in the best subset
    for (int i = 0; i < bestSet.size() - 1; i++) {
      System.out.print(Math.round(Math.pow(bestSet.get(i), 2)) + ", ");
    }
    // print last block in the set
    System.out.print(Math.round(Math.pow(bestSet.get(bestSet.size() - 1), 2)) + "]");
  }

  /**
  * Prints out the second-best left stack.
  * @pre secondBestSet is not empty
  */
  public void printSecondBestSet() {
    if (secondBestSet.isEmpty()) {
      System.out.println("Not enough blocks for a best set.");
      System.exit(0);
    }
    System.out.print("[");
    // print first n-1 blocks in the second best subset
    for (int i = 0; i < secondBestSet.size() - 1; i++) {
      System.out.print(Math.round(Math.pow(secondBestSet.get(i), 2)) + ", ");
    }
    // print last block in the set
    System.out.print(Math.round(Math.pow(secondBestSet.get(secondBestSet.size() - 1), 2)) + "]");
  }

  /**
  * @return halfHeight, half the height of all the blocks stacked on top of each other
  */
  public double getHalfHeight() {
    return halfHeight;
  }

  /**
  * @return bestHeight, the height of the best left stack
  */
  public double getBestHeight() {
    return bestHeight;
  }

  /**
  * @return secondBestSet, the height of the second-best left stack
  */
  public double getSecondBestHeight() {
    return secondBestHeight;
  }

  /**
  * Main method reads in the argument to TwoTowers and computes and prints out the best and
  * second-best stackings.
  */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java TwoTowers n");
      System.exit(0);
    }
    int n = Integer.parseInt(args[0]);
    TwoTowers towers = new TwoTowers(n);

    // print out number of total blocks and the halfhHeight
    System.out.println("There are " + n + " total blocks.");
    System.out.println("The half height (h/2) is " + towers.getHalfHeight());

    towers.findBestSets();

    // print out the best set
    System.out.print("The best subset (left stack) is ");
    towers.printBestSet();
    System.out.println(" = " + towers.getBestHeight());

    // print out the second-best set
    System.out.print("The second best subset (left stack) is ");
    towers.printSecondBestSet();
    System.out.println(" = " + towers.getSecondBestHeight());
  }

}
