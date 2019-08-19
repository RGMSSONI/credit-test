package com.social.credittest.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.repository.PostRepository;
import com.social.credittest.repository.ProfileRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ProfileRepository profileRepo;
	
	private final int NEWS_FEED_LIMIT=20;
	
	public Post createPost(String userId, Post post) {
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		post.setDate(date);

		if (profileRepo.checkUser(userId)) {
			ArrayList<Post> listPost = postRepo.getPost(userId);
			if (listPost == null) {
				listPost = new ArrayList<>();
			}
			listPost.add(post);
			postRepo.createPost(userId, listPost);
			return post;
		}else {
			return null;
		}
	}
	
	public List<Post> newsFeed(String userId) {
		List<Post> outputPost = new ArrayList<>();
		if (profileRepo.checkUser(userId)) {
			List<Post> post = postRepo.getPost(userId);
			if (post != null) {
				outputPost.addAll(post);
			}
			Profile profile = profileRepo.getProfile(userId);
			Set<String> profileSet = profile.getFollowing();
			for (String id : profileSet) {
				List<Post> followiypost = postRepo.getPost(id);
				if (followiypost != null) {
					outputPost.addAll(followiypost);
				}
			}
			//Collections.sort(outputPost);
			if(outputPost.size()<NEWS_FEED_LIMIT) {
				return outputPost.stream().sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());
			}else {
				return outputPost.subList(0, NEWS_FEED_LIMIT).stream().sorted(Comparator.comparing(Post::getDate).reversed()).collect(Collectors.toList());
			}		
		}else {
			return null;
		}
	}
}
