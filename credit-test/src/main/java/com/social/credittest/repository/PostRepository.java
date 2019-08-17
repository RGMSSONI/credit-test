package com.social.credittest.repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.social.credittest.model.Post;

@Repository
public class PostRepository {
	
	@Autowired
	private ProfileRepository profile;
	
	private Map<String, ArrayList<Post>> postByIdMap = new ConcurrentHashMap<>();
	
	public void createPost(String userId,ArrayList<Post> post){
        postByIdMap.put(userId, post);	
    }
	
	public ArrayList<Post> getPost(String userId){
		return postByIdMap.get(userId);
	}
	
	public boolean checkUser(String userId){
        return profile.checkUser(userId);
    }
}
