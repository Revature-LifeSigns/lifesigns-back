package com.revature.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j

@AllArgsConstructor
@Service
public class FileStore {
	

	
    private final AmazonS3 amazonS3; 
  
    

    public void upload(String path,
                       String fileName,
                       Optional<Map<String, String>> optionalMetaData,
                       InputStream inputStream) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        optionalMetaData.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(objectMetadata::addUserMetadata);
            }
        });
        try {
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
        	log.error("Failed to upload the file");
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            log.info("download: returns byte object");
            return IOUtils.toByteArray(objectContent);
        } catch (AmazonServiceException | IOException e) {
        	log.error("download: Failed to download the file");
            throw new IllegalStateException("Failed to download the file", e);
        }
    }
    
    public void deletePhoto(String path, String key) {
    	try {
    		amazonS3.deleteObject(path, key);
    		log.info("deletePhoto: deleted from s3 amazon bucket");
    	}catch (AmazonServiceException e) {
    		log.error("deletePhoto: Failed to delete the file");
    		throw new IllegalStateException("Failed to delete the file", e);
    	}
    }

}