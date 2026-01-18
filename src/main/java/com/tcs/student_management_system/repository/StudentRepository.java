package com.tcs.student_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tcs.student_management_system.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
