//package com.revature.controller;
//
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
//<<<<<<< HEAD
//import com.revature.model.Nurse;
//import com.revature.service.NurseService;
//
//=======
//>>>>>>> d3c075316ca6174b5081c1c66da07982fed0cd57
//import lombok.NoArgsConstructor;
//
////@RestController
////@RequestMapping(value="/nurse")
////@CrossOrigin(origins="*")
////@NoArgsConstructor
//public class NurseController {
//	private NurseService nServ;
//	
//	@Autowired
//	public NurseController(NurseService nServ) {
//		super();
//		this.nServ = nServ;
//	}
//	
//	@GetMapping()
//	public ResponseEntity<List<Nurse>> getAllNurses() {
//		return new ResponseEntity<List<Nurse>>(nServ.getAllNurse(), HttpStatus.OK);
//	}
//	
//
//	@GetMapping("/id/{id}")
//	public ResponseEntity<Nurse> getNurseByUserId(@PathVariable("id") int user_id) {
//		Nurse nurse = nServ.getNurseById(user_id);
//		if (nurse == null) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(nurse, HttpStatus.OK);
//	}
//	
//	@GetMapping("username/{username}")
//	public ResponseEntity<Nurse> getNurseByUsername(@PathVariable("username") String username) {
//		Nurse nurse = nServ.getNurseByUsername(username);
//		if (nurse == null) {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(nurse, HttpStatus.OK);
//	}
//	
//	@PostMapping("/insert")
//	public ResponseEntity<Object> insertNurse(@RequestBody Nurse nurse) {
//		if (nServ.getNurseById(nurse.getUserId()) != null) {
//			return new ResponseEntity<>("Nurse with id " + nurse.getUserId() + " already exists.", HttpStatus.FORBIDDEN);
//		}
//		nServ.insertNurse(nurse);
//		return new ResponseEntity<>(nurse, HttpStatus.CREATED);
//	}
//	
//	@PostMapping("/update")
//	public ResponseEntity<Object> updateNurse(@RequestBody Nurse nurse) {
//		if (nServ.getNurseById(nurse.getUserId()) == null) {
//			return new ResponseEntity<>("Nurse with id " + nurse.getUserId() + " doesn't exist.", HttpStatus.FORBIDDEN);
//		}
//		nServ.insertNurse(nurse);
//		return new ResponseEntity<>(nurse, HttpStatus.ACCEPTED);
//	}
//	
//	@DeleteMapping("/id/{id}")
//	public ResponseEntity<String> deleteNurse(@PathVariable("id") int user_id) {
//		if (nServ.getNurseById(user_id) == null) {
//			return new ResponseEntity<>("Nurse with id " + user_id + " doesn't exist.", HttpStatus.FORBIDDEN);
//		}
//		nServ.deleteNurse(nServ.getNurseById(user_id));
//		return new ResponseEntity<>("Nurse with id " + user_id + " deleted.", HttpStatus.ACCEPTED);
//	}
//
//}
