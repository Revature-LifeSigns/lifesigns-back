package com.revature.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.revature.model.Photo;
import com.revature.repository.PhotoRepository;
import com.revature.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class PhotoService {
	

	
	private final FileStore fileStore;
	private final PhotoRepository pRepo;
	private final UserRepository uRepo;
	private UserService uServ;
	


	public Photo savePhoto( MultipartFile file, int uploader) {
		 //check if the file is empty
        if (file.isEmpty()) {
        	log.error("savePhoto passed a empty file");
            throw new IllegalStateException("Cannot upload empty file");
        }
       
        //get file metadata
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        //Save Image in S3 and then save Todo in the database

        String path = String.format("%s", "puzzle-alchemy-pieces");

        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
            log.info("savePhoto calls FileStore upload method");
        } catch (IOException e) {
        	log.error("savePhoto failed to upload image");
            throw new IllegalStateException("savePhoto call to FileStore method failed", e);
        }
        Photo photo = Photo.builder()
                .imagePath(path)
                .imageFileName(fileName)
                .build();
        photo.setUploader(uRepo.findById(uploader));
        pRepo.save(photo);
	}



}
