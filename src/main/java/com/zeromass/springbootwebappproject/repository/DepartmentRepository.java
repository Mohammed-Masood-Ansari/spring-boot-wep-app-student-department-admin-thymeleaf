package com.zeromass.springbootwebappproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeromass.springbootwebappproject.dto.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
