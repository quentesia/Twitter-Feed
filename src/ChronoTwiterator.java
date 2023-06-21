//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Chronological Iterator
// Course:   CS 300 Spring 2023
//
// Author:   Akash Bhat
// Email:    aabhat@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator class to chronologically iterate through all the TweetNodes
 *
 * @author akash
 */
public class ChronoTwiterator implements Iterator<Tweet> {
  private TweetNode next; //The TweetNode containing the next tweet to be returned in the iteration

  /**
   * Constructor to iterate through the TweetNodes and find the next tweet chronologically.
   *
   * @param startingNode the head of the Linked list
   */
  public ChronoTwiterator(TweetNode startingNode) {
    next = startingNode;
  }

  /**
   * Check if it has a next tweet
   * @return true if there is a next tweet, otherwise false.
   */
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Method to return the next tweet chronologically
   *
   * @return the next tweet chronologically
   * @throws NoSuchElementException if the most recent tweet has been returned
   */
  public Tweet next() throws NoSuchElementException {
    if (!hasNext())
      throw new NoSuchElementException("No more tweets to show.");
    Tweet tweet = next.getTweet();
    next = next.getNext();
    return tweet;
  }
}
