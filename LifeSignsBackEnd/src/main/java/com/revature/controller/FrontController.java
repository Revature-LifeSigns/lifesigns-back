package com.revature.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.revature.model.PatientChart;
import com.revature.model.Unit;
import com.revature.model.UnitAssignment;
import com.revature.model.User;
import com.revature.service.PatientChartService;
import com.revature.service.PhotoService;
import com.revature.service.UnitAssignmentService;
import com.revature.service.UnitService;
import com.revature.service.UserService;
import com.revature.util.BcryptPasswordEncoder;

@RestController
@RequestMapping(value = "/LifeSigns")
@CrossOrigin(origins = "*")
public class FrontController {
	private UserService uServ;
	private PatientChartService pcServ;

	private PhotoService pServ;
	private PasswordEncoder passwordEncoder;
	
	private UnitService unitServ;
	private UnitAssignmentService uaServ;
	
    @Autowired
    public FrontController(UserService uServ, PatientChartService pcServ, BcryptPasswordEncoder BCryptHasher, PhotoService pServ, UnitService unitServ, UnitAssignmentService uaServ) {
        super();
        this.uServ = uServ;
        this.pcServ = pcServ;
        this.passwordEncoder = BCryptHasher.getPasswordEncoder();
        this.pServ = pServ;
        this.unitServ = unitServ;
        this.uaServ = uaServ;
    }
    
    //POST: localhost:***/LifeSigns/login
    //Include user in JSON format in the request body
    @PostMapping("/login")
    public ResponseEntity < Object > validateUser(@RequestBody LinkedHashMap < String, String > userMap) {
        // Right now, the user should contain the unhashed password stored in the user object.
        // Then the database should have the BCrypt hashed version of the password and we'll check those.
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser == null) {
            return new ResponseEntity < > ("Invalid login", HttpStatus.FORBIDDEN);
        }
        if (passwordEncoder.matches(userMap.get("password"), returnedUser.getPassword())) {
            return new ResponseEntity < > (uServ.getUserByUsername(userMap.get("username")), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity < > ("Invalid login", HttpStatus.FORBIDDEN);
    }

    //POST: localhost:***/LifeSigns/register
    //Include user in JSON format in the request body	
    @PostMapping(value = "/register")
    public ResponseEntity < Object > newUser(@RequestBody LinkedHashMap < String, String > userMap) {
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser != null)
            return new ResponseEntity < > ("Username is taken", HttpStatus.FORBIDDEN);
        //using the constructor User(int roleID, String username, String password, String email)
        //User newUser = new User(Integer.parseInt(userMap.get("roleID")), userMap.get("username"), passwordEncoder.encode(userMap.get("password")), userMap.get("email"));
        User newUser = new User("nurse", userMap.get("username"), passwordEncoder.encode(userMap.get("password")), userMap.get("email"));
        uServ.insertUser(newUser);
        return new ResponseEntity < > (newUser, HttpStatus.ACCEPTED);
    }
    
    //********************
    //GET Methods
    //********************
    
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.OK);
	}
    
	@GetMapping("/user/id/{id}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("id") int userid) {
		User user = uServ.getUserByUserId(userid);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/user/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		User user = uServ.getUserByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/chart")
	public ResponseEntity<List<PatientChart>> getAllCharts() {
		return new ResponseEntity<List<PatientChart>>(pcServ.getAllCharts(), HttpStatus.OK);
	}
	
	@GetMapping("/chart/id/{id}")
	public ResponseEntity<PatientChart> getChartByChartId(@PathVariable("id") int chartid) {
		PatientChart chart = pcServ.getChartByChartId(chartid);
		if (chart == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(chart, HttpStatus.OK);
	}
	
	@GetMapping("/admin/units")
	public ResponseEntity<List<Unit>> getHospitalUnits(){
		List<Unit> units = unitServ.getAllUnits();
		if(units == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(units, HttpStatus.OK);
	}
	
	//********************
    //POST Methods
    //********************
	
	@PostMapping("/user/insert")
	public ResponseEntity<Object> insertUser(@RequestBody User user) {
		if (uServ.getUserByUserId(user.getUserid()) != null) {
			return new ResponseEntity<>("User with id " + user.getUserid() + " already exists.", HttpStatus.FORBIDDEN);
		}
		else if (uServ.getUserByEmail(user.getEmail()) != null) {
			return new ResponseEntity<>("User with email " + user.getEmail() + " already exists.", HttpStatus.FORBIDDEN);
		}
		else if (uServ.getUserByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>("User with username " + user.getUsername() + " already exists.", HttpStatus.FORBIDDEN);
		}
		String encryptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		uServ.insertUser(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<Object> updateUser(@RequestBody User user) {
		if (uServ.getUserByUserId(user.getUserid()) == null) {
			return new ResponseEntity<>("User with id " + user.getUserid() + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		uServ.insertUser(user);	
		return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
	}
	
	//POST: localhost:***/LifeSigns/photo
    //Include the photo file in the request body.
	 @PostMapping(
	            path = "/photo",
	            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
	            produces = MediaType.APPLICATION_JSON_VALUE
	    )    
	    public ResponseEntity<String> savePhoto(
	                                         @RequestParam("file") MultipartFile file, @RequestParam("uploader") int uploader){
		 pServ.savePhoto(file, uploader);
		 return new ResponseEntity<String>("Profile Photo was uploaded.", HttpStatus.ACCEPTED);
	    }

	@PostMapping("/chart/insert")
	public ResponseEntity<Object> insertChart(@RequestBody PatientChart chart) {
		if (pcServ.getChartByChartId(chart.getChartid()) != null) {
			return new ResponseEntity<>("Chart with id " + chart.getChartid() + " already exists.", HttpStatus.FORBIDDEN);
		}
		pcServ.insertChart(chart);
		return new ResponseEntity<>(chart, HttpStatus.CREATED);
	}
	
	@PostMapping("/chart/update")
	public ResponseEntity<Object> updateChart(@RequestBody PatientChart chart) {
		if (pcServ.getChartByChartId(chart.getChartid()) == null) {
			return new ResponseEntity<>("Chart with id " + chart.getChartid() + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		pcServ.insertChart(chart);
		return new ResponseEntity<>(chart, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/admin/assign-units/{id}")
	public ResponseEntity<Object> insertOrUpdateUnitAssignment(@RequestBody Unit unit, @PathVariable("id") int userid){
		User user = uServ.getUserByUserId(userid);
		if (user == null) 
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		System.out.println(unit);
		Unit foundUnit = unitServ.getUnitByName(unit.getUnit());	
		if(foundUnit == null)
			return new ResponseEntity<>("Unit not found", HttpStatus.NOT_FOUND);
		
		UnitAssignment assignment = uaServ.getAssignedByUser(user);
		if(assignment != null) {
			assignment.setUnit(foundUnit);
			uaServ.updateUnitAssignment(assignment);
		}else {
			UnitAssignment newAssignment = new UnitAssignment(user, foundUnit);
			uaServ.insertUnitAssignment(newAssignment);
		}
		List<UnitAssignment> uaList = uaServ.getAllAssignments();
		return new ResponseEntity<>(uaList, HttpStatus.OK);
			
	}
	
	//********************
    //DELETE Methods
    //********************
	
	@DeleteMapping("/user/id/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int userid) {
		if (uServ.getUserByUserId(userid) == null) {
			return new ResponseEntity<>("User with id " + userid + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		uServ.deleteUser(uServ.getUserByUserId(userid));
		return new ResponseEntity<>("User with id " + userid + " deleted.", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/chart/id/{id}")
	public ResponseEntity<String> deleteChart(@PathVariable("id") int chartid) {
		if (pcServ.getChartByChartId(chartid) == null) {
			return new ResponseEntity<>("Chart with id " + chartid + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		pcServ.deleteChart(pcServ.getChartByChartId(chartid));
		return new ResponseEntity<>("Chart with id " + chartid + " deleted.", HttpStatus.ACCEPTED);
	}
	
	//********************
    //For Initialization Purporses
    //********************
	
	@GetMapping("/units/initialize")
	public ResponseEntity<List<Unit>> insertInitialUnits(){
		List<Unit> unitList = new ArrayList<>(Arrays.asList(new Unit("Main Floor"), new Unit("Trauma"), new Unit("ER"), new Unit("Physical Therapy"), new Unit("ICU"), new Unit("Hospice Care"), new Unit("Surgery"), new Unit("Rehabilitation")));
		for(Unit unit: unitList) {
			unitServ.insertUnit(unit);
		}
		return new ResponseEntity<List<Unit>>(unitList, HttpStatus.CREATED);
	}
	
	//********************
    //for testing purposes
    //********************
	
//	@GetMapping("/user/initial")
//	public ResponseEntity<List<User>> insertInitialUsers() {
//		List<User> userList = new ArrayList<>();
//		userList.add(new User("nurse", "SuperNurseSusan", "", "susansusans@gmail.com", "Susan", "Susans", LocalDate.now(), true));
//		userList.add(new User("doctor", "SuperDoctorSteven", "", "stevenstevens@gmail.com", "Steven", "Stevens", LocalDate.now(), false));
//		for (User user : userList) {
//			uServ.insertUser(user);
//		}
//		return new ResponseEntity<>(userList, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("chart/initial")
//	public ResponseEntity<List<PatientChart>> insertInitialCharts() {
//		User nurse = new User("nurse", "SuperNurseSusan", "", "susansusans@gmail.com", "Susan", "Susans", LocalDate.now(), true);
//		User doctor = new User("doctor", "SuperDoctorSteven", "", "stevenstevens@gmail.com", "Steven", "Stevens", LocalDate.now(), false);
//		List<PatientChart> chartList = new ArrayList<>();
//		chartList.add(new PatientChart(doctor, nurse, "Guy", "Fieri", "guyfieri@flavortown.com", LocalDate.now(), "001 Flavortown Rd.", "", "these are notes", "this is a diagnosis", true, "this is the treatment"));
//		for (PatientChart chart : chartList) {
//			pcServ.insertChart(chart);
//		}
//		return new ResponseEntity<>(chartList, HttpStatus.CREATED);
//	}

}
