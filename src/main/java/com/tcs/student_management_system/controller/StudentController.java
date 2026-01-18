package com.tcs.student_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcs.student_management_system.entity.Student;
import com.tcs.student_management_system.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// ADD STUDENT
	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@RequestBody Student student) {
	    try {
	        return ResponseEntity.ok(studentService.saveStudent(student));
	    } catch (RuntimeException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	// GET STUDENT BY ID
	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
	    return studentService.getStudentById(id);
	}


	// GET ALL STUDENTS
	@GetMapping("/all")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}

	// UPDATE STUDENT
	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student student) {
		return studentService.updateStudent(student);
	}

	// DELETE STUDENT
	@DeleteMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "Student deleted successfully";
	}
}
