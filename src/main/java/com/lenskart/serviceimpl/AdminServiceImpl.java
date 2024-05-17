package com.lenskart.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenskart.dto.UserDTO;
import com.lenskart.entity.AdminEntity;
import com.lenskart.exception.AdminNotFoundException;
import com.lenskart.repository.AdminRepository;
import com.lenskart.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public UserDTO addAdmin(UserDTO userDTO) {
		AdminEntity admin = new AdminEntity();
		admin.setUsername(userDTO.getUsername());
		admin.setAddress(userDTO.getAddress());
		admin.setEmail(userDTO.getEmail());
		admin.setNumber(userDTO.getNumber());
		admin.setPassword(userDTO.getPassword());
		admin.setId(userDTO.getId());

		adminRepository.save(admin);
		return userDTO;
	}

	// Update User
	public String updateAdmin(int id, UserDTO adminData) {

		try {
			AdminEntity admin = adminRepository.findById(id).orElseThrow(() -> new AdminNotFoundException());
			if (admin.getUsername() != null)
				admin.setUsername(adminData.getUsername());
			if (admin.getNumber() != 0)
				admin.setNumber(adminData.getNumber());
			if (admin.getAddress() != null)
				admin.setAddress(adminData.getAddress());
			if (admin.getEmail() != null)
				admin.setEmail(adminData.getEmail());
			adminRepository.save(admin);

		} catch (AdminNotFoundException e) {

			System.out.println(e);
			return "Admin data not updated";
		}
		return "Admin Updated Successfully";
	}

	// Delete user
	public void deleteAdmin(int id) {
		adminRepository.deleteById(id);
	}

	public UserDTO getAdminByEmail(String email) {
		AdminEntity admin = adminRepository.findByEmail(email);
		UserDTO userDTO = new UserDTO();

		userDTO.setAddress(admin.getAddress());
		userDTO.setEmail(admin.getEmail());
		userDTO.setNumber(admin.getNumber());
		userDTO.setUsername(admin.getUsername());
		userDTO.setId(admin.getId());

		return userDTO;
	}

	// Read all Users
	public List<UserDTO> readAllAdmins() {
		List<AdminEntity> admins = adminRepository.findAll();

		System.out.println(admins);
		List<UserDTO> userDTOs = new ArrayList<UserDTO>();
		for (AdminEntity admin : admins) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(admin.getUsername());
			userDTO.setAddress(admin.getAddress());
			userDTO.setEmail(admin.getEmail());
			userDTO.setPassword(admin.getPassword());
			userDTO.setNumber(admin.getNumber());
			userDTO.setId(admin.getId());
			userDTOs.add(userDTO);

		}

		return userDTOs;
	}

}
