package com.social.credittest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.social.credittest.model.Post;
import com.social.credittest.repository.PostRepository;
import com.social.credittest.service.PostService;

@RunWith(SpringRunner.class)
public class PostServiceTest {
	
	@InjectMocks
	private PostService postService;
	
	@Mock
	private PostRepository postRepo;
	
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
		
		postService.createPost("1", post);
		Mockito.verify(postRepo, Mockito.times(1)).createPost("1", outputPost);
	}
	
	@Test
	public void newsFeedTest() {
		
		ArrayList<Post> outputPost = new ArrayList<>();
        Post post = new Post();
        post.setPostId("1");
        post.setContent("Hello There!");
        outputPost.add(post);

        Mockito.when(postRepo.getPost(Mockito.anyString())).thenReturn(outputPost);
        
        //test
        List<Post> testPost = postService.newsFeed("1");
        
        assertEquals(1, testPost.size());
        Mockito.verify(postRepo,Mockito.times(1)).createPost("1", outputPost);
	}
}
