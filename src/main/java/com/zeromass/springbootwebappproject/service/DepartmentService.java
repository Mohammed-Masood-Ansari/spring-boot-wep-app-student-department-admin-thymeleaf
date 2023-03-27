package com.zeromass.springbootwebappproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.zeromass.springbootwebappproject.dao.DepartmentDao;
import com.zeromass.springbootwebappproject.dto.Department;
import com.zeromass.springbootwebappproject.dto.ResponseStructure;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private ResponseStructure<Department> responseStructure;

	@Autowired
	private ResponseStructure<List<Department>> responseStructure2;

	/*
	 * save department
	 */
	public ResponseStructure<Department> saveDepartment(Department department) {

		if (department != null) {
			responseStructure.setHttpCode(HttpStatus.CREATED.value());
			responseStructure.setMsg("Data-Stored");
			responseStructure.setDescription("if department is not null then it will saved");
			responseStructure.setData(department);
			departmentDao.saveDepartment(department);
			return responseStructure;
		} else {
			responseStructure.setHttpCode(HttpStatus.NO_CONTENT.value());
			responseStructure.setMsg("Data-not-Stored");
			responseStructure.setDescription("if department is null then it will not saved");
			responseStructure.setData(null);
			return responseStructure;
		}
	}

	/*
	 * display all department Data
	 */
	public ResponseStructure<List<Department>> getAllDepartmentData() {

		List<Department> departments = departmentDao.getAllDepartmentData();

		if (departments != null) {

			responseStructure2.setHttpCode(HttpStatus.ACCEPTED.value());
			responseStructure2.setMsg("data-are-present");
			responseStructure2.setDescription("if department data are present in table then you will get it");
			responseStructure2.setData(departments);
			return responseStructure2;
		} else {
			responseStructure2.setHttpCode(HttpStatus.EXPECTATION_FAILED.value());
			responseStructure2.setMsg("data-are-not-present");
			responseStructure2.setDescription("if department data are not present in table then you will not get it");
			responseStructure2.setData(null);
			return responseStructure2;
		}
	}

}
