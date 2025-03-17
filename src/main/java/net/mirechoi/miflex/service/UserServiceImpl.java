package net.mirechoi.miflex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.mirechoi.miflex.dto.Users;
import net.mirechoi.miflex.dto.UsersRole;
import net.mirechoi.miflex.mapper.UsersMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired   
	private UsersMapper usersMapper;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional  //트랜스액션으로 롤백
	public void signupUser(Users user) {
		
		//비밀번호 암호화
		user.setUserpass(passwordEncoder.encode(user.getUserpass()));
        usersMapper.setInsertUser(user);
        int userid = user.getId();
        usersMapper.setInsertRole(userid);
        
	}

	@Override
	public boolean isUseridExists(String userid) {
		Users user = usersMapper.getUserForUserId(userid);
		return user != null;  //null이 아니면(아이디가 있으면) true, 없으면 false; 
	}

	@Override
	public Users getUserByUserid(String userid) {
		// TODO Auto-generated method stub
		return usersMapper.getUserForUserId(userid);
	}

	@Override
	public void setUpdateUserByUsers(Users user) {
		// TODO Auto-generated method stub
		usersMapper.setUpdateUser(user);
	}

	@Override
	public List<Users> allUsersByAdmin() {
		return usersMapper.allUsers();
	}

}