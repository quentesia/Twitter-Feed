//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Verified User Iterator
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
public class VerifiedTwiterator implements Iterator<Tweet> {
  private TweetNode next; //The TweetNode containing the next tweet to be returned in the iteration

  /**
   * Constructor to iterate through the TweetNodes and find the first Tweet from a verified user.
   *
   * @param startNode the head of the Linked list
   */

  public VerifiedTwiterator(TweetNode startNode) {
    while (startNode != null && !startNode.getTweet().isUserVerified()) {
      startNode = startNode.getNext();
    }
    next = startNode;
  }

  /**
   * Check if it has a next tweet from a verified user
   *
   * @return true if there is a next tweet, otherwise false.
   */
  public boolean hasNext() {
    if(next==null) return false;
    if(next.getTweet().isUserVerified()) return true;
    while(next.getNext()!=null){
      next = next.getNext();
      if(next.getTweet().isUserVerified()) return true;;
    }
    return false;
  }

  /**
   * Method to return the next tweet chronologically
   *
   * @return the next tweet chronologically
   *
   * @throws NoSuchElementException if the most recent tweet has been returned
   */
  public Tweet next() throws NoSuchElementException {
    if(!hasNext()) throw new NoSuchElementException("Out of tweets.");
    Tweet tweet = next.getTweet();
    next=next.getNext();
    return tweet;
  }
}

