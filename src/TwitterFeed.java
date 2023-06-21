//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Feed for the Tweets to be printed on
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
 * This class models a reverse-chronological Twitter feed using a singly-linked list. By default,
 * new tweets are added at the head of the list. Note that while we CALL this "reverse
 * chronological" order, this is NOT enforced. You can add Tweets anywhere in the list relative to
 * each other, regardless of their respective timestamps.
 *
 * @author akash
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {

  private TweetNode head; //The node containing the most recent tweet
  private TimelineMode mode; //The iteration mode for the timeline display
  private double ratio; //The ratio of likes to total engagement that we want to see; set to .5 by default
  private int size; //The number of tweets in this linked list
  private TweetNode tail; //The node containing the oldest tweet

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to their default
   * values, and the timeline mode to CHRONOLOGICAL.
   */
  public TwitterFeed() {
    head = null;
    tail = null;
    size = 0;
    mode = TimelineMode.CHRONOLOGICAL;
    ratio = 0.5;
  }

  /**
   * Accessor for the size of the feed
   *
   * @return the number of TweetNodes in this TwitterFeed
   */
  public int size() {
    return size;
  }

  /**
   * Determines whether this feed is empty. Recommend checking MORE than just the size field to get
   * this information, though if all methods are implemented correctly the size field's value will
   * be sufficient.
   *
   * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Determines whether a given Tweet is present in the TwitterFeed. Use Tweet's equals() method!
   *
   * @param findObject the Tweet to search for
   * @return true if the Tweet is present, false otherwise
   */
  public boolean contains(Tweet findObject) {
    return indexOf(findObject) >= 0;
  }

  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed.
   *
   * @param findObject the Tweet to search for
   * @return the index of the Tweet in the TwitterFeed if present, -1 if not
   */
  public int indexOf(Tweet findObject) {
    int index = 0;
    if (findObject == null || size==0)
      return -1;

    for (TweetNode x = head; x != null; x = x.getNext()) {
      if (findObject.equals(x.getTweet()))
        return index;
      index++;
    }

    return-1;
}

  /**
   * Accessor method for the Tweet at a given index
   *
   * @param index the index of the Tweet in question
   * @return the Tweet object at that index (NOT its TweetNode!)
   * @throws IndexOutOfBoundsException if the index is negative or greater than the largest index of
   *                                   the TwitterFeed
   */
  public Tweet get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Index is invalid");

    if (index == 0)
      return getHead();
    if (index == size - 1)
      return getTail();

    TweetNode curNode = head;
    for (int i = 0; i < index; i++)
      curNode = curNode.getNext();
    return curNode.getTweet();

  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   *
   * @return the Tweet object at the head of the linked list
   * @throws NoSuchElementException if the TwitterFeed is empty
   */
  public Tweet getHead() throws NoSuchElementException {
    if (head == null)
      throw new NoSuchElementException("Feed is empty.");
    return head.getTweet();
  }

  /**
   * Accessor method for the last Tweet in the TwitterFeed
   *
   * @return the Tweet object at the tail of the linked list
   * @throws NoSuchElementException if the TwitterFeed is empty
   */
  public Tweet getTail() throws NoSuchElementException {
    if (tail == null)
      throw new NoSuchElementException("Feed is empty.");
    return tail.getTweet();
  }

  /**
   * Adds the given Tweet to the tail of the linked list
   *
   * @param newObject the Tweet to add
   */
  public void addLast(Tweet newObject) {

    TweetNode newNode = new TweetNode(newObject);
    if (tail == null && head == null) {
      head = newNode;
      tail = head;
    } else {
      TweetNode succ = new TweetNode(newObject);
      TweetNode oldTail = tail;
      oldTail.setNext(succ);
      tail = succ;
    }
    size++;
  }

  /**
   * Adds the given Tweet to the head of the linked list
   *
   * @param newObject the Tweet to add
   */
  public void addFirst(Tweet newObject) {

    TweetNode newNode = new TweetNode(newObject);
    if (head == null) {
      head = newNode;
      if (tail == null)
        tail = head;
    } else {
      TweetNode prev = head;
      head = newNode;
      head.setNext(prev);
    }
    size++;
  }

  /**
   * Adds the given Tweet to a specified position in the linked list
   *
   * @param index     the position at which to add the new Tweet
   * @param newObject the Tweet to add
   * @throws IndexOutOfBoundsException if the index is negative or greater than the size of the
   *                                   linked list
   */
  public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
    if (index < 0 || index > size)
      throw new IndexOutOfBoundsException("Invalid index.");

    if (index == size)
      addLast(newObject);
    else if (index == 0)
      addFirst(newObject);
    else {
      TweetNode curNode = head;
      for (int i = 1; i < index; i++) {
        curNode = curNode.getNext();
      }
      TweetNode nextNode = curNode.getNext();
      TweetNode newNode = new TweetNode(newObject, nextNode);
      curNode.setNext(newNode);
      size++;
    }
  }

  /**
   * Removes and returns the Tweet at the given index
   *
   * @param index the position of the Tweet to remove
   * @return the Tweet object that was removed from the list
   * @throws IndexOutOfBoundsException if the index is negative or greater than the largest index
   *                                   currently present in the linked list
   */
  public Tweet delete(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Invalid Index.");

    Tweet deleted = null;

    if (head == null)
      return deleted;
    else if (index == 0) {
      deleted = head.getTweet();
      if (tail == head) {
        head = head.getNext();
        tail = head;
      } else
        head = head.getNext();
    } else {
      TweetNode curNode = head;
      for (int i = 1; i < index; i++) {
        curNode = curNode.getNext();
      }
      TweetNode nextNode = curNode.getNext().getNext();
      deleted = curNode.getNext().getTweet();
      curNode.setNext(nextNode);
      if (nextNode == null)
        tail = curNode;
    }
    size--;
    return deleted;
  }

  /**
   * Sets the iteration mode of this TwitterFeed
   *
   * @param m the iteration mode; any value from the TimelineMode enum
   */
  public void setMode(TimelineMode m) {
    mode = m;
  }

  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   *
   * @return public Iterator<Tweet> iterator() any of ChronoTwiterator, VerifiedTwiterator, or
   * RatioTwiterator, initialized to the head of this TwitterFeed list
   */
  public Iterator<Tweet> iterator() {
    if (mode == TimelineMode.CHRONOLOGICAL)
      return new ChronoTwiterator(this.head);
    else if (mode == TimelineMode.VERIFIED_ONLY)
      return new VerifiedTwiterator(this.head);
    else if (mode == TimelineMode.LIKE_RATIO)
      return new RatioTwiterator(this.head, ratio);
    else
      return null;
  }

}
