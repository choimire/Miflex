package net.mirechoi.miflex.service;

import java.util.List;

import net.mirechoi.miflex.dto.Users;

public interface UserService {
	List<Users> allUsersByAdmin();
	void signupUser(Users users);
	boolean isUseridExists (String userid);
	Users getUserByUserid(String userid);
	void setUpdateUserByUsers(Users user);
}
