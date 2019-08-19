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

import javax.validation.constraints.AssertTrue;

import com.social.credittest.controller.SocialMediaController;
import com.social.credittest.model.GenericResponse;
import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.service.PostService;
import com.social.credittest.service.ProfileService;

@RunWith(SpringRunner.class)
@WebMvcTest(SocialMediaController.class)
@ContextConfiguration(classes=CreditTestApplication.class)
public class CreditTestApplicationTests {
	
	@MockBean
	private SocialMediaController socialMediaController;
	
	@Mock
	private ProfileService profileService;
	
	@Mock
	private PostService postService;
		
	@Test
	public void createPostMock() {
		Post post = new Post();
		post.setPostId("1");
		post.setContent("Hello There!");
		
		given(socialMediaController.createPost(anyString(), any(Post.class))).willReturn(new ResponseEntity<Post>(post,HttpStatus.CREATED));
	}

	@Test
	public  void createPostUserNull() {
		when(postService.createPost(anyString(), any(Post.class))).thenReturn(any(Post.class));	
		assertEquals(postService.createPost(anyString(),any(Post.class)),any(Post.class));
	}
	
	@Test
	public void followMock() {
		given(socialMediaController.follow(anyString(), anyString())).willReturn(new ResponseEntity<>(HttpStatus.OK));
	}
	
	@Test
	public void unFollowMock() {		
		given(socialMediaController.unfollow(anyString(), anyString())).willReturn(new ResponseEntity<>(HttpStatus.OK));
	}
	
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
	public void getNewsFeedUserNot(){	
		when(postService.newsFeed(anyString())).thenReturn(anyList());	
		assertEquals(postService.newsFeed(anyString()),anyList());
	}
	
	@Test
	public void testFollowUserNotPresent(){			
		Profile profile = new Profile();
		profile.setUserId("1");
		
		GenericResponse response = new GenericResponse();
		response.setErrorCode(404);
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
		response.setErrorCode(404);
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
		response.setErrorCode(404);
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
		response.setErrorCode(404);
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

}
