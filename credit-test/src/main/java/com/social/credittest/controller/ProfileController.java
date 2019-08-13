package com.social.credittest.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.repository.ProfileRepository;
import com.social.credittest.service.PostService;

@Controller
public class ProfileController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ProfileRepository profileRepo;

	private final int NEWS_FEED_LIMIT=20;
	
	@RequestMapping(value="{userId}/getNewsFeed", method=RequestMethod.GET)
    public ResponseEntity<Object> getNewsFeed(@PathVariable String userId) {     
		List<Post> outputPost = new ArrayList<>();
		if(postService.checkUser(userId)){
			List<Post> post = postService.getPost(userId);
			if(post!=null) {
				outputPost.addAll(post);
			}	
			Profile profile = profileRepo.getProfile(userId);
			Set<String> profileSet = profile.getFollowing();
			for(String id : profileSet){
				List<Post> followiypost = postService.getPost(id);
				if(followiypost!=null) {
					outputPost.addAll(followiypost);
				}				
			}
			Collections.sort(outputPost);
			if(outputPost.size()<NEWS_FEED_LIMIT) {
				return new ResponseEntity<Object>(outputPost, HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>(outputPost.subList(0, NEWS_FEED_LIMIT), HttpStatus.OK);
			}	
		}
		return new ResponseEntity<Object>("user not found", HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value="{userId}/createPost", method=RequestMethod.POST)
    public ResponseEntity<Object> createPost(@PathVariable String userId,@RequestBody Post post) {
        
		Date date = new Date();
		date.setTime(System.currentTimeMillis());		
		post.setDate(date);
		
		if(postService.checkUser(userId)){
			ArrayList<Post> listPost = postService.getPost(userId);
			if(listPost==null){
				listPost = new ArrayList<Post>();
			}
			listPost.add(post);
			postService.createPost(userId, listPost);	
			return new ResponseEntity<>(HttpStatus.CREATED);
		}		
		return new ResponseEntity<Object>("userId is not present",HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value="/createProfile", method=RequestMethod.POST)
    public ResponseEntity<Object> createUserProfile(@RequestBody Profile profile) {
        
		String userId = profile.getUserId();
		if(userId==null){
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		profileRepo.createProfile(userId,profile);
		return new ResponseEntity<Object>(HttpStatus.CREATED);	
    }
	
	@RequestMapping(value="/getProfile/{userId}", method=RequestMethod.GET)
    public ResponseEntity<Object> getUserProfile(@PathVariable String userId) {
        
		Profile profile = profileRepo.getProfile(userId);
		if(profile!=null){
			return new ResponseEntity<Object>(profile, HttpStatus.OK);
		}
		return new ResponseEntity<Object>("userId is not present",HttpStatus.NOT_FOUND);	
    }

	
	@RequestMapping(value="{userId}/follow", method=RequestMethod.POST)
    public ResponseEntity<Object> follow(@PathVariable String userId,@RequestParam(required=true) String id ) {	
		if(postService.checkUser(userId)){
			if(postService.checkUser(id)) {
				profileRepo.addFollower(userId, id);
				return new ResponseEntity<Object>( HttpStatus.OK);	
			}
			return new ResponseEntity<Object>("Follow person does it not exist",HttpStatus.NOT_FOUND);	
		}else {
			return new ResponseEntity<Object>("user id not exist", HttpStatus.NOT_FOUND);	
		}		
		
    }
	
	@RequestMapping(value="{userId}/unfollow", method=RequestMethod.POST)
    public ResponseEntity<Object> unfollow(@PathVariable String userId,@RequestParam(required=true) String id) {	
		if(postService.checkUser(userId)){
			if(postService.checkUser(id)) {
				profileRepo.removeFollower(userId, id);
				return new ResponseEntity<Object>( HttpStatus.OK);	
			}
			return new ResponseEntity<Object>("Follow person does it not exist",HttpStatus.NOT_FOUND);	
		}else {
			return new ResponseEntity<Object>("user id not exist", HttpStatus.NOT_FOUND);	
		}		
    }
}
