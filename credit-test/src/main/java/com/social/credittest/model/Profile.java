package com.social.credittest.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {
    
	@JsonProperty("userId")
   private String userId;
    
	@JsonProperty("followers")
   private Set<String> followers = new HashSet<>();
  
	@JsonProperty("following")
   private Set<String> following = new HashSet<>();

public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}

public Set<String> getFollowers() {
	return followers;
}

public void setFollowers(Set<String> followers) {
	this.followers = followers;
}

public Set<String> getFollowing() {
	return following;
}

public void setFollowing(Set<String> following) {
	this.following = following;
}
  
}