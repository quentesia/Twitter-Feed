//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Twitter user data type
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
 * This (very basic) data type models a Twitter user.
 *
 * @author akash
 */
public class User {
  private boolean isVerified; //The verified status of this User (whether they have a blue checkmark or not)
  private String username; //The username this User tweets under

  /**
   * Constructs a new User object with a given username. All Users are unverified by default.
   *
   * @param username the username of this User.
   * @throws IllegalArgumentException if the given username contains an asterisk ("*") character, or
   *                                  is null, or is blank
   */
  public User(String username) throws IllegalArgumentException {
    if (username == null || username.contains("*") || username.isBlank() || username.isEmpty()) {
      throw new IllegalArgumentException("Username error");
    }
    this.username = username;
  }

  /**
   * Accesses the username of this User
   *
   * @return the username this User tweets under
   */
  public String getUsername() {
    return username;
  }

  /**
   * Determines whether the User is a verified user or not
   *
   * @return true if this User is verified
   */
  public boolean isVerified() {
    return isVerified;
  }

  /**
   * Gives this User an important-looking asterisk
   */
  public void verify() {
    isVerified = true;
  }

  /**
   * Takes this User's important-looking asterisk away
   */
  public void revokeVerification() {
    isVerified = false;
  }

  /**
   * Creates a String representation of this User for display. For example, if this User's username
   * is "uwmadison" and they are verified, this method will return "@uwmadison*"; if this User's
   * username is "dril" and they are not verified, this method will return "@dril" (with no
   * asterisk).
   *
   * @return a String representation of this User as described above
   */
  @Override
  public String toString() {
    return String.format("@%s%s", username,isVerified ? "*":"");
  }
}
