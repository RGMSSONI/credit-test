package com.social.credittest.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post implements Comparable<Post>{
	
	@JsonProperty("postId")
    private String postId;
     
	@JsonProperty("content")
    private String content;
     
	@JsonIgnore
    private Date date;

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(Post o) {
		return o.getDate().compareTo(getDate());
	}
    
    
}
