package com.social.credittest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.repository.PostRepository;
import com.social.credittest.repository.ProfileRepository;
import com.social.credittest.service.PostService;

@RunWith(SpringRunner.class)
public class PostServiceTest {
	
	@InjectMocks
	private PostService postService;
	
	@Mock
	private PostRepository postRepo;
	
	@Mock
	private ProfileRepository profileRepo;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void createPostTest() {
		ArrayList<Post> outputPost = new ArrayList<>();
		Post post = new Post();
		
		post.setPostId("1");
		post.setContent("Hello All");
		
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		post.setDate(date);
		outputPost.add(post);
		
		Mockito.when(postRepo.checkUser("1")).thenReturn(true);
		Mockito.when(postRepo.getPost("1")).thenReturn(outputPost);
		Post outPost = postService.createPost("1", post);
		
		assertEquals(null,outPost);
	}
	
	@Test
	public void newsFeedTest() {
		
		ArrayList<Post> outputPost = new ArrayList<>();
        Post post = new Post();
        post.setPostId("1");
        post.setContent("Hello There!");
        outputPost.add(post);
      
        Set<String> set = new HashSet<>();
        Profile profile = new Profile();
        profile.setUserId("1");
        profile.setFollowers(set);
        profile.setFollowing(set);
        
        Mockito.when(postRepo.checkUser("1")).thenReturn(true);
        Mockito.when(profileRepo.getProfile("1")).thenReturn(profile);
        
        Mockito.when(postRepo.getPost("1")).thenReturn(outputPost);
        
        //test
        List<Post> testPost = postService.newsFeed("1");
        
        assertEquals(null,testPost);
	}
}
