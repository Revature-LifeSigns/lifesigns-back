package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Photo;
import com.revature.model.User;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	Photo findByUploader(User user);
	void deleteById(int photoId);


}