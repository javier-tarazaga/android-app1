
package com.tinygrip.android.presentation.model;

import java.io.Serializable;

/**
 * Class that represents a user in the presentation layer.
 */
public class UserModel implements Serializable {

  private final String id;

  private String coverUrl;
  private String avatarUrl;
  private String firstName;
  private String lastName;
  private String email;

  public UserModel(String userId) {
    this.id = userId;
  }

  public String getId() {
    return id;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** User Model Details *****\n");
    stringBuilder.append("id=" + this.getId() + "\n");
    stringBuilder.append("avatar url=" + this.getAvatarUrl() + "\n");
    stringBuilder.append("cover url=" + this.getCoverUrl() + "\n");
    stringBuilder.append("first name=" + this.getFirstName() + "\n");
    stringBuilder.append("last name=" + this.getLastName() + "\n");
    stringBuilder.append("email=" + this.getEmail() + "\n");
    stringBuilder.append("*******************************");

    return stringBuilder.toString();
  }
}
