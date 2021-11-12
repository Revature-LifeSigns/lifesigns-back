package com.revature.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Photo {

	  @Id
	    @GeneratedValue
	    private int id;
	    private String imagePath;
	    private String imageFileName;
	    @ManyToOne
	    @JoinColumn(name = "uploader", nullable=false )
	    private int userid;
	   


	    
}