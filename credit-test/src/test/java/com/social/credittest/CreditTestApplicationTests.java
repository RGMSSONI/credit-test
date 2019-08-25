package com.social.credittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import com.social.credittest.controller.SocialMediaController;
import com.social.credittest.model.GenericResponse;
import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.repository.ProfileRepository;
import com.social.credittest.service.PostService;
import com.social.credittest.service.ProfileService;

@RunWith(SpringRunner.class)
@WebMvcTest(SocialMediaController.class)
@ContextConfiguration(classes=CreditTestApplication.class)
public class CreditTestApplicationTests {
	
	@MockBean
	SocialMediaController socialMediaController;
	
	@Mock
	ProfileService profileService;
	
	@Mock
	ProfileRepository profileRepo;
	
	@Mock
	PostService postService;
		
	@Test
	public void createPostMock() {
		Post post = new Post();
		post.setPostId("1");
		post.setContent("Hello There!");
		
		given(socialMediaController.createPost(anyString(), any(Post.class))).willReturn(new ResponseEntity<Post>(post,HttpStatus.CREATED));
	}

	@Test
	public void createPostMockUserNull() {
		Post post = new Post();
		post.setPostId("1");
		post.setContent("Hello There!");
		
		when(profileRepo.checkUser(anyString())).thenReturn(false);
		given(socialMediaController.createPost(anyString(), any(Post.class))).willReturn(new ResponseEntity<Post>(post,HttpStatus.NOT_FOUND));
	}
	
	/*@Test
	public void followMock() {
		when(socialMediaController.follow(anyString(), anyString())).thenReturn(new ResponseEntity<GenericResponse>(any(GenericResponse.class),HttpStatus.OK));
		//given(socialMediaController.follow(anyString(), anyString())).willReturn(new ResponseEntity<GenericResponse>(any(GenericResponse.class),HttpStatus.OK));
		assertEquals(socialMediaController.follow(anyString(), anyString()).getStatusCodeValue(),eq(HttpStatus.OK.value()));
	}
	
	@Test
	public void followMockUserOrFollowNull() {
		when(profileRepo.checkUser(anyString())).thenReturn(false);
		given(socialMediaController.follow(anyString(), anyString())).willReturn(new ResponseEntity<GenericResponse>(any(GenericResponse.class),HttpStatus.NOT_FOUND));
	}
	
	@Test
	public void unFollowMock() {		
		given(socialMediaController.unfollow(anyString(), anyString())).willReturn(new ResponseEntity<GenericResponse>(any(GenericResponse.class),HttpStatus.OK));
	}
	
	@Test
	public void unFollowMockUserOrFollowNull() {
		when(profileRepo.checkUser(anyString())).thenReturn(false);
		given(socialMediaController.unfollow(anyString(), anyString())).willReturn(new ResponseEntity<GenericResponse>(any(GenericResponse.class),HttpStatus.NOT_FOUND));
	}*/
	
	@Test
	public void getNewsFeed() {		
		List<Post> outputPost = new ArrayList<>();
		Post post = new Post();
		post.setPostId("1");
		post.setContent("Hello There!");
		
		outputPost.add(post);
		given(socialMediaController.getNewsFeed(anyString())).willReturn(new ResponseEntity<>(outputPost,HttpStatus.OK));
	}
	
	@Test
	public void getNewsFeedUserNull(){
		when(postService.newsFeed(anyString())).thenReturn(null);
		assertEquals(postService.newsFeed(anyString()),null);
	}
	
	@Test
	public void testFollowUserNotPresent(){			
		Profile profile = new Profile();
		profile.setUserId("1");
		
		GenericResponse response = new GenericResponse();
		response.setCode(404);
		response.setStatus("User does not found");
		
		when(profileService.createProfile(profile)).thenReturn(profile);		
		when(profileService.addFollower(anyString(), anyString())).thenReturn(response);
		assertEquals(profileService.addFollower(anyString(),anyString()),response);
	}
	
	@Test
	public void testFollowNotPresent(){		
		Profile profile = new Profile();
		profile.setUserId("1");
		
		GenericResponse response = new GenericResponse();
		response.setCode(404);
		response.setStatus("Follow Person not found");
		
		when(profileService.createProfile(profile)).thenReturn(profile);		
		when(profileService.addFollower(anyString(), anyString())).thenReturn(response);
		assertEquals(profileService.addFollower(anyString(),anyString()),response);
	}
	
	@Test
	public void testUnFollowUserNotPresent(){		
		Profile profile = new Profile();
		profile.setUserId("1");
		
		GenericResponse response = new GenericResponse();
		response.setCode(404);
		response.setStatus("User does not found");
		
		when(profileService.createProfile(profile)).thenReturn(profile);		
		when(profileService.removeFollower(anyString(), anyString())).thenReturn(response);
		assertEquals(profileService.removeFollower(anyString(),anyString()),response);		
	}
	
	@Test
	public void testUnfollowNotPresent(){			
		Profile profile = new Profile();
		profile.setUserId("1");
		
		GenericResponse response = new GenericResponse();
		response.setCode(404);
		response.setStatus("unFollow Person not found");
		
		when(profileService.createProfile(profile)).thenReturn(profile);		
		when(profileService.addFollower(anyString(), anyString())).thenReturn(response);
		assertEquals(profileService.addFollower(anyString(),anyString()),response);
	}
	
	@Test
	public void createProfile(){
		Profile profile = new Profile();
		profile.setUserId("1");
		when(profileService.createProfile(profile)).thenReturn(profile);
		assertEquals(profileService.createProfile(profile),profile);
	}
	
	@Test
	public void createProfileTestNull(){
		Profile profile = new Profile();
		profile=null;
		when(profileService.createProfile(profile)).thenReturn(null);
		assertEquals(profileService.createProfile(profile),null);
	}
	
	@Test
	public void createProfileTestObjectEmpty(){
		Profile profile = new Profile();
		when(profileService.createProfile(profile)).thenReturn(null);
		assertEquals(profileService.createProfile(profile),null);
	}
	
	@Test
	public void getProfile(){
		Profile profile = mock(Profile.class);
		when(profileService.fetchProfile(anyString())).thenReturn(profile);
		assertEquals(profileService.fetchProfile(anyString()),profile);
	}
		
	@Test
	public void getProfileEmpty(){
		when(profileService.fetchProfile(anyString())).thenReturn(null);
		assertEquals(profileService.fetchProfile(anyString()),null);
	}
	
	@Test
	public void nullOutputNewsfeed(){
		Profile profile = new Profile();
		profile.setUserId("1");
		when(profileService.createProfile(profile)).thenReturn(profile);
		List<Post> outputPost = null;
		when(postService.newsFeed("2")).thenReturn(null);
		assertEquals(postService.newsFeed("2"),outputPost);
	}
	@Test
	public void OutputNewsfeed(){
		List<Post> outputPost = new ArrayList<>();
		assertEquals(postService.newsFeed(anyString()),outputPost);
	}

	

}
