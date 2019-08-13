package com.social.credittest.repository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.social.credittest.model.Profile;

@Repository
public class ProfileRepository {
	
	private Map<String, Profile> profileByIdMap = new ConcurrentHashMap<>();
	
	public void createProfile(String userId,Profile profile){
		profileByIdMap.put(userId, profile);	
    }
	
	public Profile getProfile(String userId){
		return profileByIdMap.get(userId);
	}
	
	public boolean checkUser(String userId){
        return profileByIdMap.containsKey(userId);
    }
	
	
	public void addFollower(String userId, String id){
		Profile profile = profileByIdMap.get(userId);	
		Set<String> following = profile.getFollowing();
		
		if(profileByIdMap.containsKey(id)){
			following.add(id);
			profile.setFollowing(following);
			
			Profile follwiyProfile = profileByIdMap.get(id);	
			Set<String> followers = follwiyProfile.getFollowers();
			followers.add(userId);
			follwiyProfile.setFollowers(followers);			
		}
	}
	
	public void removeFollower(String userId, String id){
		Profile profile = profileByIdMap.get(userId);	
		Set<String> following = profile.getFollowing();
		
		if(profileByIdMap.containsKey(id)){	
			following.remove(id);
			profile.setFollowing(following);
			
			Profile followProfile = profileByIdMap.get(id);
			Set<String> followers = followProfile.getFollowers();
			followers.remove(userId);
			followProfile.setFollowers(followers);
			
		}
	}
}
