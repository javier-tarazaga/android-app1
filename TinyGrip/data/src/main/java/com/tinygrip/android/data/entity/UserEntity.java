
package com.tinygrip.android.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User Entity used in the data layer.
 */
public class UserEntity {

  private String id;
  private String coverUrl;
  private String avatarUrl;
  private String firstName;
  private String lastName;
  private String email;

  public UserEntity() {
    //empty
  }

  public String getId() {
    return id;
  }

  @JsonProperty("Id")
  public void setId(String id) {
    this.id = id;
  }

  public String getCoverUrl() {
    return coverUrl;
  }

  @JsonProperty("CoverUrl")
  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  @JsonProperty("AvatarUrl")
  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getFirstName() {
    return firstName;
  }

  @JsonProperty("FirstName")
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @JsonProperty("LastName")
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  @JsonProperty("Email")
  public void setEmail(String email) {
    this.email = email;
  }

  @Override public String toString() {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append("***** User Entity Details *****\n");
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
