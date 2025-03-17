package net.mirechoi.miflex.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.mirechoi.miflex.dto.Users;
import net.mirechoi.miflex.dto.UsersRole;

@Mapper	
public interface UsersMapper {

	Users getUserForUserId(String userid); //회원정보 하나 가져오기
	
	List<Users> allUsers();
	List<UsersRole>getUserRole(int userid); // userRole 권한정보 가져오기
	void setInsertUser(Users user);
	void setInsertRole(int userid);
    void setUpdateUser(Users user);
    void setDeleteUser(int id);
	
	
}
