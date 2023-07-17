package com.example.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Exceptions.UserNotFoundException;
import com.example.Model.Student_data;
import com.example.Repo.StudentRepo;

@Service
public class StudentService implements UserDetailsService{
	
	@Autowired
	StudentRepo sr;
	
	
	public List<Student_data> addStud(Student_data s){
		try {
			sr.save(s);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sr.findAll();
	}
	
	public List<Student_data> getStud(){
		return sr.findAll();
	}
	
	public List<Student_data> updateStud(int id, int rollNo, String firstName, String lastName){
		if(sr.findById(id).isPresent()==false) {
			throw new UserNotFoundException("User Not Found");
		}else {
			Student_data s = sr.findById(id).get();
			s.setRollNo(rollNo);
			s.setFirstName(firstName);
			s.setLastName(lastName);
			sr.save(s);
		}
		return sr.findAll();
	}
	
	public List<Student_data> deleteStud(int id){
		if (sr.findById(id).isPresent()==false) {
			throw new UserNotFoundException("User Not Found");
		}else {
			sr.deleteById(id);
		}
		return sr.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return sr.findByFirstName(username);
	}

}
