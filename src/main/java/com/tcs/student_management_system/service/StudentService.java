package com.tcs.student_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.student_management_system.entity.Student;
import com.tcs.student_management_system.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	// save student
	public Student saveStudent(Student student) {

	    if (student.getId() != null &&
	        studentRepository.existsById(student.getId())) {

	        throw new RuntimeException("Student already exists with this ID");
	    }

	    return studentRepository.save(student);
	}

	
	// GET STUDENT BY ID
	public Student getStudentById(Long id) {
	    return studentRepository.findById(id).orElse(null);
	}



	// get all students
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}
	
	// UPDATE STUDENT
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}
	

	// delete student by id
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}
}
