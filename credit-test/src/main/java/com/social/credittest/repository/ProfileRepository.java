package com.social.credittest.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.social.credittest.model.Profile;

@Repository
public class ProfileRepository {
	
	private Map<String, Profile> profileByIdMap = new ConcurrentHashMap<>();
	
	public Profile createProfile(String userId,Profile profile){
		return profileByIdMap.put(userId, profile);		
    }
	
	public Profile getProfile(String userId){
		return profileByIdMap.get(userId);
	}
	
	public boolean checkUser(String userId){
        return profileByIdMap.containsKey(userId);
    }	
}
