// I am the sole author of the work in this repository
import java.util.*;

/**
* SubsetIterator is like an Iterator. It takes a Vector<E> and traverses through all
* subsets of the elements in that vector (using bitwise operations).
*/
public class SubsetIterator<E> {
  // stores the set of elemnets to generate subsets of
  private Vector<E> originalSet;
  // representation of the maximum number of a subset of the original set (2^n)
  private long maxSubset;
  // the current subset
  private long currentSubset;

  /**
  * Constructor for SubsetIterator
  * @param set a Vector<E> to find subsets of
  * @post originalSet is initialized to set
  * @post maxSubset is initialized to 2^n (where n is the size of set)
  * @post currentSubset is initialized to 0
  */
  public SubsetIterator(Vector<E> set) {
    originalSet = set;
    // maxSubsets is 2^n, where n is the size of the full set
    maxSubset = 1L << set.size();
    currentSubset = 0;
  }

  /**
  * Resets the iterator.
  * @post resets the currentSubset to 0
  */
  public void reset() {
    currentSubset = 0;
  }

  /**
  * @return true if there is still at least 1 subset that has not yet been returned
  */
  public boolean hasNext() {
    return currentSubset < maxSubset;
  }

  /**
  * Returns the current value being considered.
  * @return Vector<E> the next subset
  */
  public Vector<E> get() {
    // create a new vector to store the elements
    Vector<E> subset = new Vector<E>(originalSet.size());
    // maxBit is the maximum number of bits used to store the current subset
    // if n = 0, maxBits = 1
    int maxBits = 1;
    // for all other positive n, maxBits = log(base 2) of n (+ 1 for (int) truncation)
    if (currentSubset > 0) {
      maxBits = (int) (Math.log(currentSubset) / Math.log(2)) + 1;
    }
    // add elements to the vector if the bit at the index is a 1
    for (int i = 0; i < maxBits; i++) {
      if ((currentSubset & (1L << i)) == (1L << i)) {
        subset.add(originalSet.get(i));
      }
    }
    return subset;
  }

  /**
  * Increments the iterator along and returns the next value considered.
  * @return Vector<E> the next subset
  * @post currentSubset is incremented
  */
  public Vector<E> next() {
    // return get() and increment the current subset
    Vector<E> subset = get();
    currentSubset++;
    return subset;
  }

  /**
  * Main method to test SubsetIterator on a vector of integers
  */
  public static void main(String[] args) {
    Vector<Integer> test = new Vector<Integer>(8);
    for (int i = 1; i <= 8; i++) {
      test.add(i);
    }
    SubsetIterator<Integer> testIter = new SubsetIterator<Integer>(test);
    while (testIter.hasNext()) {
      Vector<Integer> set = testIter.next();
      System.out.println(set);
    }
  }

}
