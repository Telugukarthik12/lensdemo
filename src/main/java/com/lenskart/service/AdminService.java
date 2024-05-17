package com.lenskart.service;


import com.lenskart.dto.UserDTO;

public interface AdminService {
	
	public UserDTO addAdmin(UserDTO adminDTO);
	public String updateAdmin(int id,UserDTO adminDTO);
	public UserDTO getAdminByEmail(String email);
	

}
