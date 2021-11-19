package com.revature.test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

import java.io.File;
import java.io.IOException;
import java.util.zip.DataFormatException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.repository.PhotoRepository;
import com.revature.repository.UserRepository;
import com.revature.service.FileStore;
import com.revature.service.PhotoService;



@SpringBootTest
class PhotoServiceTest {
	@Mock
	private final FileStore fileStore = null;
	@Mock
	private final PhotoRepository pRepo = null;
	@Mock
	private final UserRepository uRepo = null;
			
	@Mock
	PhotoService phs = new PhotoService(null, null, null); 
	
	
	@Test
	public void useridvalid()
	{
		//MultipartFile file= ;
		MockMultipartFile kmlfile = new MockMultipartFile("data", "filename.kml", "text/plain", "some kml".getBytes());
		int userid=-1;
		
		doThrow(new IllegalArgumentException()).when(phs).savePhoto(kmlfile,userid);
		
		//
	}
	
	
	@Test
	public void testfileType() 
	{
		
		File fileObj =new File("filename.txt");
		
		int userid = 1;
		
		 doThrow(new DataFormatException()).when(phs).savePhoto((MultipartFile) fileObj,userid);
		
	}
	
	@Test
	public void usernotfound()
	{
		int userid=-1;
		
		doThrow(new IllegalArgumentException()).when(phs).getProfilePhoto(userid);
	}
	
	
	
}
