package com.zeromass.springbootwebappproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeromass.springbootwebappproject.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
