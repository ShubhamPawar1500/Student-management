package com.example.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.Model.Student_data;

@Repository
public interface StudentRepo extends JpaRepository<Student_data, Integer>{
	
	public UserDetails findByFirstName(String firstName);

}
