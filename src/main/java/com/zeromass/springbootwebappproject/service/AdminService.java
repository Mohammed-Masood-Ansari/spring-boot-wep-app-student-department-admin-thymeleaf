package com.zeromass.springbootwebappproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.zeromass.springbootwebappproject.dao.AdminDao;
import com.zeromass.springbootwebappproject.dto.Admin;
import com.zeromass.springbootwebappproject.dto.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private ResponseStructure<Admin> responseStructure;
	
	@Autowired
	private ResponseStructure<List<Admin>> responseStructure2;

	public ResponseStructure<Admin> saveAdmin(Admin admin) {

		if (admin != null) {

			responseStructure.setHttpCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMsg("Data-Added-Successfully");
			responseStructure.setDescription("this is admin method where we can create new username and password");
			responseStructure.setData(admin);
			adminDao.saveAdmin(admin);
			return responseStructure;

		} else {
			responseStructure.setHttpCode(HttpStatus.NOT_ACCEPTABLE.value());
			responseStructure.setMsg("Data-Not-Added-Successfully-Please-Check-Admin");
			responseStructure.setDescription("this is admin method where we can create new username and password");
			responseStructure.setData(null);
			return responseStructure;
		}

	}
}
