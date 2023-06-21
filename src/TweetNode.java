//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Node of a tweet for Linked list
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

/**
 * A container for a Tweet in a singly-linked list
 *
 * @author akash
 */
public class TweetNode {
  private TweetNode nextTweet; //The next TweetNode in this linked list
  private Tweet tweet; //The tweet contained in this node

  /**
   * Constructs a singly-linked node containing a tweet, with no successor twee
   * @param tweet the tweet to put in this node
   */
  public TweetNode(Tweet tweet){
    this.tweet=tweet;
  }

  /**
   * Constructs a singly-linked node containing a tweet
   * @param tweet the tweet to put in this node
   * @param next the next tweet in the linked list
   */
  public TweetNode(Tweet tweet, TweetNode next){
    this(tweet);
    this.nextTweet = next;
  }

  /**
   * Accesses the tweet in this node
   *
   * @return the tweet in this node
   */
  public Tweet getTweet() {
    return tweet;
  }
  /**
   * Accesses the next TweetNode in the list
   *
   * @return the successor TweetNode
   */
  public TweetNode getNext(){
    return nextTweet;
  }

  /**
   * Links this node to another node
   * @param next the successor TweetNode (can be null)
   */
  public void setNext(TweetNode next){
    nextTweet = next;
  }

}
