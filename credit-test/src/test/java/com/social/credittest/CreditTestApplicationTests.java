package com.social.credittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;

import com.social.credittest.controller.ProfileController;
import com.social.credittest.model.Post;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
@ContextConfiguration(classes=CreditTestApplication.class)
public class CreditTestApplicationTests {

	/*@Autowired
	private MockMvc mvc;*/
	
	@MockBean
	private ProfileController profileController;
	
	@Test
	public void createPostMock() throws Exception {
		String userId= "1";
		Post post = new Post();
		post.setPostId("1");
		post.setContent("Hello There!");
		
		given(profileController.createPost(userId, post)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
	}

	@Test
	public void followMock() throws Exception {
		String userId= "1";
		String id ="2";
		
		given(profileController.follow(userId, id)).willReturn(new ResponseEntity<>(HttpStatus.OK));
	}
	
	@Test
	public void unFollowMock() throws Exception {
		String userId= "1";
		String id ="2";
		
		given(profileController.unfollow(userId, id)).willReturn(new ResponseEntity<>(HttpStatus.OK));
	}
	
	@Test
	public void getNewsFeed() throws Exception {
		String userId= "1";
		
		List<Post> outputPost = new ArrayList<>();
		Post post = new Post();
		post.setPostId("1");
		post.setContent("Hello There!");
		
		outputPost.add(post);
		given(profileController.getNewsFeed(userId)).willReturn(new ResponseEntity<>(outputPost,HttpStatus.OK));
	}
	
}
