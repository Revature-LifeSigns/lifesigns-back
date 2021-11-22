package com.revature.controller;


import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.revature.model.CovidSurvey;
import com.revature.model.PatientChart;
import com.revature.model.Photo;
import com.revature.model.Unit;
import com.revature.model.UnitAssignment;
import com.revature.model.User;
import com.revature.service.CovidSurveyService;
import com.revature.service.PatientChartService;
import com.revature.service.PhotoService;
import com.revature.service.UnitAssignmentService;
import com.revature.service.UnitService;
import com.revature.service.UserService;
import com.revature.util.BcryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/LifeSigns")
@CrossOrigin(origins = "*")
@Slf4j
public class FrontController {
	private UserService uServ;
	private PatientChartService pcServ;
	private CovidSurveyService csServ;

	private PhotoService pServ;
	private PasswordEncoder passwordEncoder;
	
	private UnitService unitServ;
	private UnitAssignmentService uaServ;
	
@Autowired
    public FrontController(UserService uServ, PatientChartService pcServ, BcryptPasswordEncoder BCryptHasher, PhotoService pServ, CovidSurveyService csServ, UnitService unitServ, UnitAssignmentService uaServ) {
        super();
        this.uServ = uServ;
        this.pcServ = pcServ;
        this.passwordEncoder = BCryptHasher.getPasswordEncoder();
        this.pServ = pServ;
        this.csServ = csServ;
        this.unitServ = unitServ;
        this.uaServ = uaServ;
    }
    
    //POST: localhost:***/LifeSigns/login
    //Include user in JSON format in the request body
    @PostMapping("/login")
    public ResponseEntity < Object > validateUser(@RequestBody LinkedHashMap < String, String > userMap) {
    	log.info("Login request was made.");
        // Right now, the user should contain the unhashed password stored in the user object.
        // Then the database should have the BCrypt hashed version of the password and we'll check those.
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser == null) {
            return new ResponseEntity < > ("No valid user with username", HttpStatus.UNAUTHORIZED);
        }
        if (passwordEncoder.matches(userMap.get("password"), returnedUser.getPassword())) {
            return new ResponseEntity < > (uServ.getUserByUsername(userMap.get("username")), HttpStatus.OK);
        }
        return new ResponseEntity < > ("Invalid login", HttpStatus.UNAUTHORIZED);
    }

    //POST: localhost:***/LifeSigns/register
    //Include user in JSON format in the request body	
    @PostMapping(value = "/register")
    public ResponseEntity < Object > newUser(@RequestBody LinkedHashMap < String, String > userMap) {
    	log.info("Register request was made.");
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser != null)
            return new ResponseEntity < > ("Username is taken", HttpStatus.CONFLICT); //409, conflict because already exists
        returnedUser = uServ.getUserByEmail(userMap.get("email"));
        if (returnedUser != null)
            return new ResponseEntity < > ("Email is taken", HttpStatus.CONFLICT); //409, conflict because already exists  
        User newUser = new User();
        newUser.setRole(userMap.get("role"));
        newUser.setUsername(userMap.get("username"));
        newUser.setPassword(passwordEncoder.encode(userMap.get("password")));
        newUser.setEmail(userMap.get("email"));
        newUser.setFirstName(userMap.get("firstname"));
        newUser.setLastName(userMap.get("lastname"));
        newUser.setDob(LocalDate.parse(userMap.get("dob")));
        newUser.setAddress(userMap.get("address"));
        newUser.setAboutMe(userMap.get("aboutMe"));
        newUser.setViewPreference(Boolean.valueOf(userMap.getOrDefault("viewPreference","false")));
        newUser.setCovidStatus(userMap.get("covid_status"));
        
        uServ.insertUser(newUser);
        return new ResponseEntity < > (newUser, HttpStatus.CREATED); //201, created because user created
    }

    //POST: localhost:***/LifeSigns/changePassword
    //Include user in JSON format in the request body, expecting username, currentPassword, and newPassword.
    @PostMapping(value = "/changePassword")
    public ResponseEntity < Object > changePassword(@RequestBody LinkedHashMap < String, String > userMap) {
    	log.info("Reset password request was made.");
        User returnedUser = uServ.getUserByUsername(userMap.get("username"));
        if (returnedUser == null)
            return new ResponseEntity < > ("Username doesn't exist", HttpStatus.CONFLICT); //409, conflict, how did we get here with invalid username?
        //using the constructor User(int roleID, String username, String password, String email)
        if (!passwordEncoder.matches(userMap.get("currentPassword"), returnedUser.getPassword())) {
            return new ResponseEntity < > ("Username doesn't exist", HttpStatus.UNAUTHORIZED); //401, because user+currentpw is incorrect
        }
        returnedUser.setPassword(passwordEncoder.encode(userMap.get("newPassword")));
        uServ.insertUser(returnedUser); //says insertUser but it will update the current user
        return new ResponseEntity < > (returnedUser, HttpStatus.OK); //200, OK
    }
    
	//********************
	//GET Methods
	//********************
   
	//GET: localhost:***/LifeSigns/user
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("Request to get all users was made.");
		return new ResponseEntity<List<User>>(uServ.getAllUsers(), HttpStatus.OK);
	}
    
	//GET: localhost:***/LifeSigns/user/id/{id}
	@GetMapping("/user/id/{id}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("id") int userid) {
		log.info("Request to get user by id was made.");
		User user = uServ.getUserByUserId(userid);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	//GET: localhost:***/LifeSigns/user/username/{username}
	@GetMapping("/user/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		log.info("Request to get user by username was made.");
		User user = uServ.getUserByUsername(username);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping("/photo/{id}")
	public ResponseEntity<Photo> getProfilePhoto(@PathVariable("id")String id) {
		log.info("Request to get profile photo was made.");
		return new ResponseEntity<Photo>(pServ.getProfilePhoto(Integer.parseInt(id)), HttpStatus.OK);
	}
	
  //GET: localhost:***/LifeSigns/chart
	@GetMapping("/chart")
	public ResponseEntity<List<PatientChart>> getAllCharts() {
		log.info("Request to get all patient charts was made.");
		return new ResponseEntity<List<PatientChart>>(pcServ.getAllCharts(), HttpStatus.OK);
	}
	
	//GET: localhost:***/LifeSigns/chart/id/{id}
	@GetMapping("/chart/id/{id}")
	public ResponseEntity<PatientChart> getChartByChartId(@PathVariable("id") int chartid) {
		log.info("Request to get patient chart by chart id was made.");
		PatientChart chart = pcServ.getChartByChartId(chartid);
		if (chart == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(chart, HttpStatus.OK);
	}
	
	@GetMapping("/survey")
	public ResponseEntity<List<CovidSurvey>> getAllSurveys() {
		log.info("Request to get all COVID surveys was made.");
		return new ResponseEntity<List<CovidSurvey>>(csServ.getAllSurveys(), HttpStatus.OK);
	}
	
	@GetMapping("/survey/id/{id}")
	public ResponseEntity<CovidSurvey> getSurveyBySurveyId(@PathVariable("id") int surveyId) {
		log.info("Request to get COVID survey by survey id was made.");
		CovidSurvey survey = csServ.getSurveyBySurveyId(surveyId);
		if(survey == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(survey, HttpStatus.OK);
	}
	
	@GetMapping("/survey/userid/{userid}")
	public ResponseEntity<List<CovidSurvey>> getSurveysByUserId(@PathVariable("userid") int userId) {
		log.info("Request to get COVID surveys by user id was made.");
		List<CovidSurvey> surveyList = csServ.getSurveysByUserId(userId);
		if(surveyList == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<CovidSurvey>>(surveyList, HttpStatus.OK);
	}
	
	
	@GetMapping("/admin/units")
	public ResponseEntity<List<Unit>> getHospitalUnits(){
		log.info("Request to get hospital units was made.");
		List<Unit> units = unitServ.getAllUnits();
		if(units == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(units, HttpStatus.OK);
	}
	
	@GetMapping("/admin/assigned-unit/{id}")
	public ResponseEntity<Object> getAssignedUnitByUserId(@PathVariable("id") int userId){
		log.info("Request to get unit assignment by user id was made.");
		User user = uServ.getUserByUserId(userId);
		if (user == null) 
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		UnitAssignment assignment = uaServ.getAssignedByUser(user);
		if(assignment == null)
			return new ResponseEntity<>(null, HttpStatus.OK);
		return new ResponseEntity<>(assignment.getUnit(), HttpStatus.OK);
	}
	
	//********************
	//POST Methods
	//********************
	
	//POST: localhost:***/LifeSigns/user/insert
	//Include user in the request body
	@PostMapping("/user/insert")
	public ResponseEntity<Object> insertUser(@RequestBody User user) {
		log.info("Request to insert a user was made.");
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
	
	//POST: localhost:***/LifeSigns/user/update
	//Deprecated, use patch method instead
	@PostMapping("/user/update")
	public ResponseEntity<Object> updateUser(@RequestBody User user) {
		log.info("Request to update a user was made.");
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
				@RequestParam("file") MultipartFile file, @RequestParam("uploader") String uploader){
			log.info("Request to save photo was made.");
			pServ.savePhoto(file, Integer.parseInt(uploader));
			return new ResponseEntity<String>("Profile Photo was uploaded.", HttpStatus.ACCEPTED);
	}

	//POST: localhost:***/LifeSigns/chart/insert
	//Include chart in the request body
	@PostMapping("/chart/insert")
	public ResponseEntity<Object> insertChart(@RequestBody PatientChart chart) throws JsonMappingException, JsonProcessingException {
		log.info("Request to insert a chart was made.");
		if (pcServ.getChartByChartId(chart.getChartid()) != null) {
			return new ResponseEntity<>("Chart with id " + chart.getChartid() + " already exists.", HttpStatus.FORBIDDEN);
		}
		pcServ.insertChart(chart);
		return new ResponseEntity<>(chart, HttpStatus.CREATED);
	}
	
//	//POST: localhost:***/LifeSigns/chart/update
//	//Deprecated, use patch method instead
//	@PostMapping("/chart/update")
//	public ResponseEntity<Object> updateChart(@RequestBody PatientChart chart) {
//		if (pcServ.getChartByChartId(chart.getChartid()) == null) {
//			return new ResponseEntity<>("Chart with id " + chart.getChartid() + " doesn't exist.", HttpStatus.FORBIDDEN);
//		}
//		pcServ.insertChart(chart);
//		return new ResponseEntity<>(chart, HttpStatus.ACCEPTED);
//	}
	

	@PostMapping("/survey/insert")
	public ResponseEntity<Object> insertSurvey(@RequestBody CovidSurvey survey) {
		log.info("Request to get insert a COVID survey was made.");
		if(csServ.getSurveyBySurveyId(survey.getSurveyId()) != null) {
			return new ResponseEntity<>("Survey with id " + survey.getSurveyId() + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		csServ.insertSurvey(survey);
		return new ResponseEntity<>(survey, HttpStatus.ACCEPTED);
	}

	@PostMapping("/admin/assign-units/{id}")
	public ResponseEntity<Object> insertOrUpdateUnitAssignment(@RequestBody Unit unit, @PathVariable("id") int userId){
		log.info("Request to insert/update unit assignment was made.");
		User user = uServ.getUserByUserId(userId);
		if (user == null) 
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);

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
	//PATCH Methods
	//********************
	
	//PATCH: localhost:***/LifeSigns/user/update
	//Include changes in the request body as a User object
	//User id and updated fields must be included, other fields can be left blank or null
	@PatchMapping("/user/update/{id}")
	public ResponseEntity<Object> patchUser(@RequestBody User user, @PathVariable int id) {
		log.info("Request to patch a user was made.");
		if (uServ.getUserByUserId(id) == null) {
			return new ResponseEntity<>("User with id " + user.getUserid() + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		User oldUser = uServ.getUserByUserId(id);
		uServ.updateUser(oldUser, user);
		return new ResponseEntity<>(uServ.getUserByUserId(id), HttpStatus.ACCEPTED);
	}
	
	//PATCH: localhost:***/LifeSigns/chart/update
	//Include changes in the request body as a PatientChart object
	//Chart id and updated fields must be included, other fields can be left blank or null
	@PatchMapping("chart/update")
	public ResponseEntity<Object> patchChart(@RequestBody PatientChart chart) {
		log.info("Request to patch a patient chart was made.");
		if (pcServ.getChartByChartId(chart.getChartid()) == null) {
			return new ResponseEntity<>("Chart with id " + chart.getChartid() + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		PatientChart oldChart = pcServ.getChartByChartId(chart.getChartid());
		pcServ.updateChart(oldChart, chart);
		return new ResponseEntity<>(pcServ.getChartByChartId(chart.getChartid()), HttpStatus.ACCEPTED);
	}
	
	//********************
	//DELETE Methods
	//********************
	
	@DeleteMapping("/user/id/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int userid) {
		log.info("Request to delete a user was made.");
		if (uServ.getUserByUserId(userid) == null) {
			return new ResponseEntity<>("User with id " + userid + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		uServ.deleteUser(uServ.getUserByUserId(userid));
		return new ResponseEntity<>("User with id " + userid + " deleted.", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/chart/id/{id}")
	public ResponseEntity<String> deleteChart(@PathVariable("id") int chartid) {
		log.info("Request to delete a patient chart was made.");
		if (pcServ.getChartByChartId(chartid) == null) {
			return new ResponseEntity<>("Chart with id " + chartid + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		pcServ.deleteChart(pcServ.getChartByChartId(chartid));
		return new ResponseEntity<>("Chart with id " + chartid + " deleted.", HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/survey/id/{id}")
	public ResponseEntity<String> deleteSurvey(@PathVariable("id") int surveyId) {
		log.info("Request to delete a COVID survey was made.");
		CovidSurvey survey = csServ.getSurveyBySurveyId(surveyId);
		if(survey == null) {
			return new ResponseEntity<>("Survey with id " + surveyId + " doesn't exist.", HttpStatus.FORBIDDEN);
		}
		csServ.deleteSurvey(survey);
		return new ResponseEntity<>("Survey with id " + surveyId + " deleted.", HttpStatus.ACCEPTED);
	}

	//********************
    //For Initialization Purporses
    //********************
	
	@GetMapping("/units/initialize")
	public ResponseEntity<List<Unit>> insertInitialUnits(){
		log.info("Request to insert initial units was made.");
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


