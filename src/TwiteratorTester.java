//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Testers
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

import java.util.Calendar;
import java.util.NoSuchElementException;

/**
 * Class containing testers for User, Tweet, TweetNode, TwitterFeed, and the various Twiterators
 *
 * @author akash
 */
public class TwiteratorTester {

  /**
   * Testers to check if the User class works as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testUser() {
    User user1 = new User("Arct8^Pil0t");

    {
      //Test toString() for a verified user and test IsVerified()
      user1.verify();
      String expected = "@Arct8^Pil0t*";
      String actual = user1.toString();

      if (!user1.isVerified())
        return false;

      if (!expected.equals(actual))
        return false;
    }

    {
      //Test RevokeVerification()

      user1.revokeVerification();
      String expected = "@Arct8^Pil0t";
      String actual = user1.toString();

      if (user1.isVerified())
        return false;

      if (!expected.equals(actual))
        return false;
    }

    {
      //Check for errors with illegal usernames
      try {
        User user2 = new User("Q*ue2nt*esi^a");
        return false;
      } catch (IllegalArgumentException ignored) {
      }

    }

    {
      //Test for illegal User
      try {
        User user2 = new User("");
        return false;
      } catch (IllegalArgumentException ignored) {
      }
    }

    return true;
  }


  /**
   * Testers to check if the Tweet class works as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testTweet() {

    User user1 = new User("iMiaSanMia");
    user1.verify();

    {
      //Test date Generator not initialized
      try {
        Tweet tweet1 = new Tweet(user1, "This should not work");
        return false;
      } catch (IllegalStateException ignored) {
      }
    }

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "Bayern have won the Champions League.");
    {
      //Test tweet toString()
      String expected =
          "tweet from @iMiaSanMia* at Sat Jun 10 15:30:05 CDT 2023:\n" + "-- Bayern have won the Champions League.\n" + "-- likes: 0\n" + "-- retweets: 0";
      String actual = tweet1.toString();

      if (!expected.equals(actual))
        return false;
    }

    {
      //Test equals() method
      Tweet tweet2 = new Tweet(user1, "Bayern have won the Champions League.");
      Tweet failTweet = new Tweet(user1, "Bayern have won the Champions league.");

      if (!tweet1.equals(tweet2))
        return false;
      if (tweet1.equals(failTweet))
        return false;
    }

    {
      //Test getLikesRatio()
      int i = 1;
      while (i < 100) {
        tweet1.like();
        if (i % 3 == 0)
          tweet1.retweet();
        i++;
      }

      double expected = 0.75;
      double actual = tweet1.getLikesRatio();

      if (expected != actual)
        return false;
    }

    {
      //Test errors for invalid Tweets
      try {
        Tweet tweet2 = new Tweet(null, null);
        return false;
      } catch (NullPointerException ignored) {
      }

      try {
        Tweet tweet2 = new Tweet(user1,
            "qA1zCiroL1NVjwUG53FxI2aWLky55DWqhnTHnF3ZeA3IOx5SvBr3RkwtHRbE5olskVguRoaXuspGH7" + "D9HVncV0q3J26qdI4soHplKcsHlNDKvLOaFwmH03CwUKHUfYDJBKPRzyjEDSpcQPWv6lH82BaVbn6R1" + "6kDamA4N8e70UaBZTt8eJgCLauKDCFL4efXPfCD4KbSjO4gfYIaKh7scIWCCpGfJ8sq2FyFWHsUY39" + "V063HvAiU3zvkKZKcF6Qzrn65tcx4uqa3wwcnOpmC71N5UD6A69jzEf2TJyKWk");
        return false;
      } catch (IllegalArgumentException ignored) {
      }
    }
    return true;
  }


  /**
   * Testers to check if the TweetNode class works as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testNode() {
    User user1 = new User("iMiaSanMia");
    User user2 = new User("ArcticPil0t");

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "Bayern have won the Champions League.");
    Tweet tweet2 = new Tweet(user2, "Get ratioed.");

    TweetNode node1 = new TweetNode(tweet1);
    TweetNode node2 = new TweetNode(tweet2);
    node1.setNext(node2);

    if (!node1.getTweet().equals(tweet1))
      return false;
    if (node1.getNext() != node2)
      return false;

    {
      TweetNode node3 = new TweetNode(tweet1, node2);
      if (node3.getNext() != node2)
        return false;
      if (node3.getTweet() != tweet1)
        return false;
    }
    return true;
  }


  /**
   * Testers to check if tweets can be added to the feed as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testAddTweet() {
    User user1 = new User("iMiaSanMia");

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "Bayern have won the Champions League.");

    TwitterFeed freshFeed = new TwitterFeed();

    if (!freshFeed.isEmpty())
      return false;
    if (freshFeed.size() != 0)
      return false;

    freshFeed.addFirst(tweet1);

    if (freshFeed.isEmpty())
      return false;
    if (freshFeed.size() == 0)
      return false;
    if (freshFeed.indexOf(tweet1) != 0)
      return false;
    if (!freshFeed.contains(tweet1))
      return false;
    if (freshFeed.get(0) != tweet1)
      return false;
    if (freshFeed.getHead() != tweet1)
      return false;
    if (freshFeed.getHead() != freshFeed.getTail())
      return false;

    return true;
  }


  /**
   * Testers to check if the tweets can be inserted into the feed as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testInsertTweet() {

    User user1 = new User("iMiaSanMia");
    User user2 = new User("ArcticPil0t");
    User user3 = new User("Mr.PotatoHead");
    user3.verify();
    User user4 = new User("JCole No.1 Fan");

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "Brazil");
    Tweet tweet2 = new Tweet(user2, "Creepin'");
    Tweet tweet3 = new Tweet(user3, "All The Small Things");
    Tweet tweet4 = new Tweet(user4, "Dance Now");
    Tweet tweet5 = new Tweet(user1, "EARFQUAKE");

    TwitterFeed freshFeed = new TwitterFeed();
    freshFeed.add(0, tweet1);
    freshFeed.add(1, tweet2);
    freshFeed.add(0, tweet3);

    freshFeed.add(2, tweet4);


    if (freshFeed.indexOf(tweet5) != -1)
      return false;

    if (freshFeed.size() != 4)
      return false;
    if (freshFeed.get(1) != tweet1)
      return false;
    if (freshFeed.indexOf(tweet4) != 2)
      return false;

    //Testing for invalid indexes
    try {
      freshFeed.add(5, tweet5);
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    }

    try {
      freshFeed.add(-2, tweet5);
      return false;
    } catch (IndexOutOfBoundsException ignored) {
    }

    return true;
  }

  /**
   * Testers to check if Tweets can be deleted from the feed as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testDeleteTweet() {

    User user1 = new User("iMiaSanMia");
    User user2 = new User("ArcticPil0t");
    User user3 = new User("Mr.PotatoHead");
    user3.verify();
    User user4 = new User("JCole No.1 Fan");

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "Brazil");
    Tweet tweet2 = new Tweet(user2, "Dance Now");
    Tweet tweet3 = new Tweet(user3, "All The Small Things");
    Tweet tweet4 = new Tweet(user4, "Creepin'");
    Tweet tweet5 = new Tweet(user1, "EARFQUAKE");

    TwitterFeed freshFeed = new TwitterFeed();

    freshFeed.add(0, tweet1);
    freshFeed.add(1, tweet2);
    freshFeed.addFirst(tweet3);
    freshFeed.add(2, tweet4);
    freshFeed.addLast(tweet5);

    //Testing delete() method multiple times
    {
      Tweet actual = freshFeed.delete(4);

      if (actual != tweet5)
        return false;
      if (freshFeed.getTail() != tweet2)
        return false;
      if (freshFeed.size() != 4)
        return false;
    }

    {
      Tweet actual = freshFeed.delete(0);

      if (actual != tweet3)
        return false;
      if (freshFeed.getHead() != tweet1)
        return false;
      if (freshFeed.size() != 3)
        return false;
    }

    {
      Tweet actual = freshFeed.delete(1);

      if (actual != tweet4)
        return false;
      if (freshFeed.get(1) != tweet2)
        return false;
      if (freshFeed.size() != 2)
        return false;
    }

    return true;
  }


  /**
   * Testers to check if the Chronological Iterator works as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testChronoTwiterator() {

    User user1 = new User("iMiaSanMia");
    User user2 = new User("ArcticPil0t");
    User user3 = new User("Mr.PotatoHead");
    user3.verify();
    User user4 = new User("JCole No.1 Fan");
    User user5 = new User("Future");

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user3, "All The Small Things");
    Tweet tweet2 = new Tweet(user1, "Brazil");
    Tweet tweet3 = new Tweet(user2, "Dance Now");
    Tweet tweet4 = new Tweet(user4, "Born Sinner");
    Tweet tweet5 = new Tweet(user5, ("Life is Good"));

    TwitterFeed freshFeed = new TwitterFeed();
    freshFeed.setMode(TimelineMode.CHRONOLOGICAL);

    freshFeed.addFirst(tweet1);
    freshFeed.add(1, tweet2);
    freshFeed.addLast(tweet3);
    freshFeed.addFirst(tweet4);
    freshFeed.add(3, tweet5);

    for (int i = 0; i < 3281; i++) {
      tweet1.like();
      tweet4.like();
      if (i * i > i * 5)
        tweet1.retweet();
      if (i % 2 == 0)
        tweet2.like();
      if (i % 3 == 0)
        tweet2.retweet();
    }

    for (int i = 5; i < 17248; i *= 5) {
      tweet3.like();
      if (i % 10 == 0)
        tweet3.retweet();
      if (i / 15 > 5 * i / 3)
        tweet4.retweet();
    }

    //Printing out the tweets in Chronological order
    System.out.println();
    System.out.println();
    System.out.println("----CHRONOLOGICAL TWEETS----");
    System.out.println();

    for (Tweet tweet : freshFeed) {
      System.out.println(tweet);
    }

    return true;
  }


  /**
   * Testers to check if the Verified Tweet Iterator class works as intended.
   *
   * @return true if tests pass, otherwise false.
   */
  public static boolean testVerifiedTwiterator() {
    User user1 = new User("iMiaSanMia");
    User user2 = new User("ArcticPil0t");
    user2.verify();
    User user3 = new User("Mr.PotatoHead");
    user3.verify();
    User user4 = new User("JCole No.1 Fan");
    user4.verify();
    user4.revokeVerification();
    User user5 = new User("Maruchan Ramen");
    user5.verify();

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "All The Small Things by Blink-182 is a classic");
    Tweet tweet2 = new Tweet(user2, "Brazil by Declan Mckenna is beautiful");
    Tweet tweet3 = new Tweet(user3, "Creepin' is a modern day masterpiece #TheWeeknd");
    Tweet tweet4 = new Tweet(user4, "Dance Now is amazing and JID is underrated");
    Tweet tweet5 = new Tweet(user5, "Carti was spitting in EARFQUAKE #nefunifebUHIEF");
    TwitterFeed freshFeed = new TwitterFeed();
    freshFeed.setMode(TimelineMode.VERIFIED_ONLY);

    freshFeed.addFirst(tweet1);
    freshFeed.add(1, tweet2);
    freshFeed.addLast(tweet3);
    freshFeed.addLast(tweet4);
    freshFeed.addLast(tweet5);

    for (int i = 0; i < 7842; i++) {
      tweet2.like();
      tweet3.like();
      if (i * i > i * 5)
        tweet2.retweet();
      if (i % 2 == 0)
        tweet2.like();
      if (i % 3 == 0)
        tweet3.retweet();
    }

    for (int i = 5; i < 327998; i *= 5) {
      tweet5.like();
      if (i % 10 == 0)
        tweet5.retweet();
    }

    //Printing out only verified tweets
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("----VERIFIED TWEETS ONLY----");
    System.out.println();

    for (Tweet tweet : freshFeed) {
      System.out.println(tweet);
    }

    return true;
  }


  /**
   * Testers to check if the High Like Ratio Iterator class works as intended.
   *
   * @return true if tests pass, otherwise false.
   */public static boolean testRatioTwiterator() {
    User user1 = new User("iMiaSanMia");
    User user2 = new User("ArcticPil0t");
    user2.verify();
    User user3 = new User("Mr.PotatoHead");
    user3.verify();
    User user4 = new User("JCole No.1 Fan");
    user4.verify();
    user4.revokeVerification();
    User user5 = new User("Maruchan Ramen");
    user5.verify();

    Calendar c = Calendar.getInstance();
    c.set(2023, Calendar.JUNE, 10, 15, 30, 5);
    Tweet.setCalendar(c);

    Tweet tweet1 = new Tweet(user1, "All The Small Things by Blink-182 is a classic");
    Tweet tweet2 = new Tweet(user2, "Brazil by Declan Mckenna is beautiful");
    Tweet tweet3 = new Tweet(user3, "Creepin' is a modern day masterpiece #TheWeeknd");
    Tweet tweet4 = new Tweet(user4, "Dance Now is amazing and JID is underrated");
    Tweet tweet5 = new Tweet(user5, "Carti was spitting in EARFQUAKE #nefunifebUHIEF");
    TwitterFeed freshFeed = new TwitterFeed();
    freshFeed.setMode(TimelineMode.LIKE_RATIO);

    freshFeed.addFirst(tweet1);
    freshFeed.add(1, tweet2);
    freshFeed.addLast(tweet3);
    freshFeed.addLast(tweet4);
    freshFeed.addLast(tweet5);

    for (int i = 0; i < 7842; i++) {
      tweet2.like();
      tweet3.like();
      if (i * i > i * 5)
        tweet2.retweet();
      if (i % 2 == 0)
        tweet2.like();
      if (i % 3 == 0)
        tweet3.retweet();
    }

    for (int i = 5; i < 327998; i *= 5) {
      tweet5.like();
      if (i % 10 == 0)
        tweet5.retweet();
    }

    for (int i = 0; i < 1000; i++) {
      tweet3.like();
      tweet4.retweet();
    }

    //Printing out only tweets with high ratio
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("----HIGH RATIO TWEETS ONLY----");
    System.out.println();

    for (Tweet tweet : freshFeed) {
      System.out.println(tweet);
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println(testUser());
    System.out.println(testTweet());
    System.out.println(testNode());
    System.out.println(testAddTweet());
    System.out.println(testInsertTweet());
    System.out.println(testDeleteTweet());

    try {
      System.out.println(testChronoTwiterator());
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

    try {
      System.out.println(testVerifiedTwiterator());
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }

    try {
      System.out.println(testRatioTwiterator());
    } catch (NoSuchElementException e) {
      System.out.println(e.getMessage());
    }
  }

}
