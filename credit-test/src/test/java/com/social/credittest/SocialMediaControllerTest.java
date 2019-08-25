package com.social.credittest;

import com.social.credittest.controller.SocialMediaController;
import com.social.credittest.model.GenericResponse;
import com.social.credittest.model.Post;
import com.social.credittest.model.Profile;
import com.social.credittest.service.PostService;
import com.social.credittest.service.ProfileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SocialMediaController.class)
public class SocialMediaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private ProfileService profileService;

    String strCreatePost = "{\"postId\":\"1\", \"content\":\"Hello There\"}";
    String strCreateProfile = "{ \"userId\":\"1\", \"followers\":[], \"following\":[] }";

    @Test
    public void getNewsFeed() throws Exception {

        List<Post> outputPost = new ArrayList<>();
        Post post = new Post();
        post.setPostId("1");
        post.setContent("Hello There!");
        outputPost.add(post);

        Mockito.when(
                postService.newsFeed(Mockito.anyString())).thenReturn(outputPost);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/1/getNewsFeed")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        MockHttpServletResponse responseMock = result.getResponse();

        assertEquals(HttpStatus.OK.value(), responseMock.getStatus());
    }

    @Test
    public void createPost() throws Exception {

        Post post = new Post();
        post.setPostId("1");
        post.setContent("Hello There");

        Mockito.when(
                postService.createPost(Mockito.anyString(),
                        Mockito.any(Post.class))).thenReturn(post);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/1/createPost")
                .accept(MediaType.APPLICATION_JSON).content(strCreatePost)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void createUserProfile() throws Exception {

        Profile profile = new Profile();
        profile.setUserId("1");

        Mockito.when(profileService.createProfile(Mockito.any(Profile.class))).thenReturn(profile);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/createProfile")
                .accept(MediaType.APPLICATION_JSON).content(strCreateProfile)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void getUserProfile() throws Exception {
    	Profile profile = new Profile();
        profile.setUserId("1");
    	
        Mockito.when(profileService.fetchProfile(Mockito.anyString())).thenReturn(profile);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/getProfile/1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        
        MockHttpServletResponse responseMock = result.getResponse();

        assertEquals(HttpStatus.OK.value(), responseMock.getStatus());      
    }

    @Test
    public void follow() throws Exception {
    	GenericResponse response = new GenericResponse();
    	response.setCode(200);
    	response.setStatus("Success");
    	
    	Mockito.when(profileService.addFollower(Mockito.anyString(), Mockito.anyString())).thenReturn(response);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/1/follow?id=2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{code:200, status:Success}";
        
        MockHttpServletResponse responseMock = result.getResponse();

        assertEquals(HttpStatus.OK.value(), responseMock.getStatus());

        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    	
    }

    @Test
    public void unfollow() throws Exception {

    	GenericResponse response = new GenericResponse();
    	response.setCode(200);
    	response.setStatus("Success");
    	
    	Mockito.when(profileService.removeFollower(Mockito.anyString(), Mockito.anyString())).thenReturn(response);
    	
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/1/unfollow?id=2")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{code:200, status:Success}";
        
        MockHttpServletResponse responseMock = result.getResponse();

        assertEquals(HttpStatus.OK.value(), responseMock.getStatus());

        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }
}