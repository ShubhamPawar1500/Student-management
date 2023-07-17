package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Model.Student_data;
import com.example.Service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService ss;
	
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/add")
	public ResponseEntity<List<Student_data>> addStud(@RequestBody Student_data s){
		s.setLastName(encoder.encode(s.getLastName()));
		return ResponseEntity.status(HttpStatus.OK).body(ss.addStud(s));
	}
	
	@GetMapping("/get")
	public List<Student_data> getStud(){
		return ss.getStud();
	}
	
	@PutMapping("/update")
	public ResponseEntity<List<Student_data>> updateStud(@RequestParam("id") int  id,@RequestParam("rollno") int rollNo,@RequestParam("firstname") String firstName,@RequestParam("lastname") String lastName){
		return ResponseEntity.status(HttpStatus.OK).body(ss.updateStud(id, rollNo, firstName, lastName));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<List<Student_data>> deleteStud(@PathVariable("id") int id){
		return ResponseEntity.status(HttpStatus.OK).body(ss.deleteStud(id));
	}
	
	@GetMapping("/admin")
	public String adminAPI() {
		return "Admin API";
	}
	
	@GetMapping("/user")
	public String UserAPI() {
		return "User API";
	}

}
