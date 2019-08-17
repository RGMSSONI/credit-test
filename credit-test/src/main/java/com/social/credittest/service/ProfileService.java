package com.social.credittest.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.credittest.model.Profile;
import com.social.credittest.repository.ProfileRepository;

@Service
public class ProfileService {
	@Autowired
	private ProfileRepository profileRepo;

	public Profile createProfile(Profile profile) {
		if (profile != null && profile.getUserId() != null) {
			String userId = profile.getUserId();
			return profileRepo.createProfile(userId, profile);
		} else {
			return null;
		}
	}

	public Profile fetchProfile(String userId) {
		return profileRepo.getProfile(userId);
	}

	public void addFollower(String userId, String id) {
		if (profileRepo.checkUser(userId)) {
			if (profileRepo.checkUser(id)) {
				Profile profile = profileRepo.getProfile(userId);
				Set<String> following = profile.getFollowing();

				following.add(id);
				profile.setFollowing(following);

				Profile follwiyProfile = profileRepo.getProfile(id);
				Set<String> followers = follwiyProfile.getFollowers();
				followers.add(userId);
				follwiyProfile.setFollowers(followers);
			}
		}
	}

	public void removeFollower(String userId, String id) {
		if (profileRepo.checkUser(userId)) {
			if (profileRepo.checkUser(id)) {
				Profile profile = profileRepo.getProfile(userId);
				Set<String> following = profile.getFollowing();

				following.remove(id);
				profile.setFollowing(following);

				Profile followProfile = profileRepo.getProfile(id);
				Set<String> followers = followProfile.getFollowers();
				followers.remove(userId);
				followProfile.setFollowers(followers);
			}
		}
	}
}
