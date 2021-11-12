//package com.revature.controller;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping(value="/doctors")
//@CrossOrigin(origins="*")
//public class DoctorController {
//
//	private DoctorService doctorService;
//	
//	public DoctorController() {}
//	
//	@Autowired
//	public DoctorController(DoctorService doctorService) {
//		super();
//		this.doctorService = doctorService;
//	}
//	
//	@GetMapping("/initial")
//	public ResponseEntity<List<Doctor>> insertInitialDoctors() {
//		List<Doctor> doctorList = new ArrayList<Doctor>(Arrays.asList(new Doctor()));
//		
//		for(Doctor d: doctorList) {
//			doctorService.insertDoctor(d);
//		}
//		return new ResponseEntity<List<Doctor>>(doctorService.getAllDoctors(), HttpStatus.CREATED);
//	}
//	
//	@GetMapping()
//	public ResponseEntity<List<Doctor>> getAllDoctors() {
//		return new ResponseEntity<List<Doctor>>(doctorService.getAllDoctors(), HttpStatus.OK);
//	}
//	
//	@GetMapping("/{lastname}")
//	public ResponseEntity<Doctor> getDoctorByLastName(@PathVariable("lastname") String lastname) {
//		Doctor doc = doctorService.getDoctorByLastName(lastname);
//		if(doc == null) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<Doctor>(doctorService.getDoctorByLastName(lastname), HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{lastname}")
//	public ResponseEntity<String> deleteDoctor(@PathVariable("lastname") String lastname) {
//		Doctor doc = doctorService.getDoctorByLastName(lastname);
//		if(doc == null) {
//			return new ResponseEntity<String>("No User Of That Name: " +lastname, HttpStatus.NOT_FOUND);
//		}
//		doctorService.deleteDoctor(doc);
//		return new ResponseEntity<String>("Doctor: " + lastname + " was deleted", HttpStatus.ACCEPTED);
//	}
//	
//	// This controller method cannot be implemented until a correction is made in DoctorService.
//	//@PostMapping()
//	//public ResponseEntity<Object> insertDoctor(@RequestBody Doctor doc){
//		// System.out.println("Doctor JSON: " + doc);
//		// Doctor newDoc = doctorService.insertDoctor(doc);
//		// if(newDoc == null) {
//		//	return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
//		// }		
//		// return new ResponseEntity<>(newDoc, HttpStatus.CREATED);
//	//}
//	
//	//Need to write update doctor method
//	@PostMapping("/update")
//	public ResponseEntity<String> updateDoctor(@RequestBody Doctor doctor){
//		doctorService.updateDoctor(doctor);
//		return new ResponseEntity<String>("Profile Info Successfully Updated", HttpStatus.ACCEPTED);
//	}
//	
//	
//}
