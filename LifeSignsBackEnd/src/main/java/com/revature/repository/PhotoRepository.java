package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
	
	
	void deleteById(int photoId);


}