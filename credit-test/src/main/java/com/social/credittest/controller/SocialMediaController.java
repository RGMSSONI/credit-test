package com.social.credittest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.social.credittest.model.GenericResponse;
import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.service.PostService;
import com.social.credittest.service.ProfileService;

@Controller
public class SocialMediaController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value="{userId}/getNewsFeed", method=RequestMethod.GET)
    public ResponseEntity<List<Post>> getNewsFeed(@PathVariable(required=true) String userId) {     
		List<Post> outputPost = postService.newsFeed(userId);
		if(outputPost!=null) {
			return new ResponseEntity<List<Post>>(outputPost, HttpStatus.OK);
		}
		return new ResponseEntity<List<Post>>(outputPost,HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value="{userId}/createPost", method=RequestMethod.POST)
    public ResponseEntity<Post> createPost(@PathVariable(required=true) String userId,@RequestBody(required=true) Post post) {
		Post postOutput = postService.createPost(userId, post);
		if(postOutput!=null) {
			return new ResponseEntity<Post>(postOutput,HttpStatus.CREATED);
		}
		return new ResponseEntity<Post>(postOutput,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/createProfile", method=RequestMethod.POST)
    public ResponseEntity<Profile> createUserProfile(@RequestBody(required=true) Profile profile) {
		Profile profileOutput = profileService.createProfile(profile);
		if(profileOutput!=null) {
			return new ResponseEntity<Profile>(profileOutput,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Profile>(profileOutput,HttpStatus.BAD_REQUEST);
		}		
    }
	
	@RequestMapping(value="/getProfile/{userId}", method=RequestMethod.GET)
    public ResponseEntity<Profile> getUserProfile(@PathVariable(required=true) String userId) { 
		Profile profile = profileService.fetchProfile(userId);
		if(profile!=null){
			return new ResponseEntity<Profile>(profile, HttpStatus.OK);
		}
		return new ResponseEntity<Profile>(profile,HttpStatus.NOT_FOUND);	
    }

	@RequestMapping(value="{userId}/follow", method=RequestMethod.POST)
    public ResponseEntity<GenericResponse> follow(@PathVariable(required=true) String userId,@RequestParam(required=true) String id ) {	
		GenericResponse response = profileService.addFollower(userId, id);
		return new ResponseEntity<GenericResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value="{userId}/unfollow", method=RequestMethod.POST)
    public ResponseEntity<GenericResponse> unfollow(@PathVariable(required=true) String userId,@RequestParam(required=true) String id) {	
		GenericResponse response = profileService.removeFollower(userId, id);
		return new ResponseEntity<GenericResponse>(response,HttpStatus.OK);
	}
}
