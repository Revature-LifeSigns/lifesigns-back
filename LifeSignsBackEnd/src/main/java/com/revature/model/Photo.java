package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
	    @OneToOne
	    @JoinColumn(name = "uploader", nullable=false )
	    private User uploader;
	   


	    
}