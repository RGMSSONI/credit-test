package com.social.credittest.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.credittest.model.Post;
import com.social.credittest.repository.ProfileRepository;

import java.util.ArrayList;

@Service
public class PostService {
	
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
