package com.revature.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Nurse;

import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/nurse")
@CrossOrigin(origins="*")
@NoArgsConstructor
public class NurseController {
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getNurseByUserId(@PathVariable("id") int user_id) {
		return new ResponseEntity<>("getNurseByUserId works! user_id = " + user_id, HttpStatus.OK);
	}
	
	@PostMapping("/update")
	public ResponseEntity<Object> updateNurseProfile(@RequestBody Nurse nurse) {
		return new ResponseEntity<>("updateNurseProfile works! Nurse username = " + nurse.getUserName(), HttpStatus.OK);
	}
	
	//todo: add request body
	@PostMapping("/diagnosis")
	public ResponseEntity<Object> sendPatientDiagnosisForm() {
		return new ResponseEntity<>("sendPatientDiagnosisForm works!", HttpStatus.OK);
	}
	
}
