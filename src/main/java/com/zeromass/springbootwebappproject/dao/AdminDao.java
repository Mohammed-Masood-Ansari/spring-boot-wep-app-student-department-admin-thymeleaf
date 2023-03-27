package com.zeromass.springbootwebappproject.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zeromass.springbootwebappproject.dto.Admin;
import com.zeromass.springbootwebappproject.repository.AdminRepository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepository adminRepository;

	public void saveAdmin(Admin admin) {

		adminRepository.save(admin);
	}

	public Admin getByAdminId(int adminById) {

		Optional<Admin> optional = adminRepository.findById(adminById);

		if (optional.isPresent()) {

			return optional.get();
		}

		return null;
	}

	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}
}
