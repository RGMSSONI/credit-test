package com.social.credittest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.social.credittest.model.GenericResponse;
import com.social.credittest.model.Profile;
import com.social.credittest.repository.ProfileRepository;
import com.social.credittest.service.ProfileService;

@RunWith(SpringRunner.class)
public class ProfileServiceTest {

	@InjectMocks
	private ProfileService profileService;
	
	@Mock
	private ProfileRepository profileRepo;
	
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void createProfileTest() {
		Profile profile = new Profile();
        profile.setUserId("1");

        Mockito.when(profileRepo.createProfile(Mockito.anyString(),Mockito.any(Profile.class))).thenReturn(profile);
        
        Profile profile1 = profileService.createProfile(profile);
        assertEquals(profile.getUserId(),profile1.getUserId());
        Mockito.verify(profileRepo, Mockito.times(1)).createProfile("1", profile);
	}
	
	@Test
	public void getUserProfileTest() {
		Profile profile = new Profile();
        profile.setUserId("1");
        
		Mockito.when(profileRepo.getProfile(Mockito.anyString())).thenReturn(profile);
		
		Profile profile1 = profileService.fetchProfile("1");
        
		assertEquals(profile.getUserId(),profile1.getUserId());
	}
	
	@Test
	public void addFollowerTest() {
		GenericResponse response = new GenericResponse();
		response.setCode(200);
		response.setStatus("Success");
		
		Mockito.when(profileRepo.checkUser(Mockito.anyString())).thenReturn(true);
		
		Profile profile = new Profile();
        profile.setUserId("1");
        profileService.createProfile(profile);
        
        Profile profile2 = new Profile();
        profile2.setUserId("2");
        profileService.createProfile(profile2);
		
        GenericResponse responseGot = profileService.addFollower("1", "2");
		
		assertEquals(response.getCode(), responseGot);
		
		//Mockito.verify(profileService, Mockito.times(1)).addFollower("1", "2");
	}
	
	@Test
	public void removeFollowerTest() {
		GenericResponse response = new GenericResponse();
		response.setCode(200);
		response.setStatus("Success");
		
		Mockito.when(profileRepo.checkUser(Mockito.anyString())).thenReturn(true);
		
		Profile profile = new Profile();
        profile.setUserId("1");
        profileService.createProfile(profile);
        
        Profile profile2 = new Profile();
        profile2.setUserId("2");
        profileService.createProfile(profile2);
		
        GenericResponse responseGot = profileService.removeFollower("1", "2");
		
		assertEquals(response.getCode(), responseGot);
		
		//Mockito.verify(profileService, Mockito.times(1)).removeFollower("1", "2");
	}
}
