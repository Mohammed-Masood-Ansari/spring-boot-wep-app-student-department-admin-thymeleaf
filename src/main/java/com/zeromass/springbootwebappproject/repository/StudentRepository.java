package com.zeromass.springbootwebappproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeromass.springbootwebappproject.dto.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
