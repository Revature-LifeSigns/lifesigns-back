//package com.revature.controller;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value="/doctors")
//@CrossOrigin(origins="*")
//public class DoctorController {
//
//	//private DoctorService dServ
//	
//	public DoctorController() {}
//	
//	
//	//@Autowired
//	/*public DoctorController(DoctorService dServ){
//		super();
//		this.dServ=dServ;
//		}*/
//	
////	@GetMapping("/initial")
////	public ResponseEntity<List<Doctor>> insertInitialDoctors(){
////		List<Doctor> dList = new ArrayList<Doctor>(Arrays.asList(new Doctor()))
////				for(Doctor d: dList) {
////					dServ.insertDoctor(d);
////				}
////		return new ResponseEntity<List<Doctor>>(dServ.getAllDoctors(), HttpStatus.CREATED);
////	}
//	
////	@GetMapping()
////	public ResponseEntity<List<Doctor>> getAllDoctors(){
////		return new ResponseEntity<List<Doctor>>(dServ.getAllDoctors(), HttpStatus.OK);
////	}
//	
////	@GetMapping("/{lastname}")
////	public ResponseEntity<Doctor> getDoctorByLastName(@PathVariable("lastname") String lastname){
////		Doctor doc = dServ.getDoctorByLastName(lastname);
////		if(user==null) {
////			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////		}
////		return new ResponseEntity<Doctor>(dServ.getDoctorByFirstName(), HttpStatus.OK);
////	}
//	
////	@DeleteMapping("/{lastname}")
////	public ResponseEntity<String> delteDoctor(@PathVariable("lastname") String lastname){
////		Doctor doc = dServ.getDoctorByLastName(lastname);
////		if(doc==null) {
////			return new ResponseEntity<String>("No User Of That Name: " +lastname, HttpStatus.NOT_FOUND);
////		}
////		dServ.deleteDoctor(doc);
////		return new Response<String>("Doctor: " + lastname + " was deleted", HttpStatus.ACCEPTED);
////	}
//	
////	@PostMapping()
////	public ResponseEntity<Object> insertDoctor(@RequestBody Doctor doc){
////		System.out.println("Doctor JSON: " + doc);
////		Doctor newDoc = dServ.insertDoctor(doc);
////		
////		if(newDoc == null) {
////			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
////		}
////		
////		return new ResponseEntity<>(newDoc, HttpStatus.CREATED);
////	}
//	
//}
