//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    High Like Ratio Iterator
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
 * Iterator class to only iterate through TweetNodes that have a high-like ratio
 *
 * @author akash
 */
public class RatioTwiterator implements Iterator<Tweet> {
  private TweetNode next; //The TweetNode containing the next tweet to be returned in the iteration
  private final double THRESHOLD; //The minimum likeRatio required

  /**
   * Constructor to iterate through the TweetNodes and find the first TweetNode with a high-like
   * ratio.
   *
   * @param startNode the head of the Linked list
   * @param threshold The minimum ratio
   */
  public RatioTwiterator(TweetNode startNode, double threshold) {
    THRESHOLD = threshold;
    while (startNode != null && startNode.getTweet().getLikesRatio() < THRESHOLD) {
      startNode = startNode.getNext();
    }
    next = startNode;
  }

  /**
   * Method to iterate through the list and check whether the List has another Node with a high-like
   * ratio
   *
   * @return true if the List has another Node with a high-like ratio, otherwise False
   */
  public boolean hasNext() {
    if (next == null)
      return false;
    if (next.getTweet().getLikesRatio() >= THRESHOLD)
      return true;
    while (next.getNext() != null) {
      next = next.getNext();
      if (next.getTweet().getLikesRatio() >= THRESHOLD)
        return true;
    }
    return false;
  }

  /**
   * Method to return the next tweet with a high-like ratio
   *
   * @return the next tweet with a high-like ratio
   * @throws NoSuchElementException if there are no more tweets with a high-like ratio
   */
  public Tweet next() throws NoSuchElementException {
    if (!hasNext())
      throw new NoSuchElementException("Out of tweets.");
    Tweet tweet = next.getTweet();
    next = next.getNext();
    return tweet;
  }

}
