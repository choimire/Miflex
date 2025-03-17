package net.mirechoi.miflex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.mirechoi.miflex.dto.Users;
import net.mirechoi.miflex.dto.UsersRole;
import net.mirechoi.miflex.mapper.UsersMapper;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
private final UsersMapper userMapper;
	
	//생성자로 직접 주입
	public CustomUserDetailService (UsersMapper usersMapper) {
		this.userMapper = usersMapper;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
	
		System.out.println("회원이름" + userid);
		
		Users user = userMapper.getUserForUserId(userid);

        
		//1.정보가 있는지 확인하기
		if(user == null) {
			throw new UsernameNotFoundException("회원 정보가 없습니다.");
		}
		
		//2. 롤 가져오기
		List<UsersRole> roles = userMapper.getUserRole(user.getId());

		//3.권한 만들기
		List<SimpleGrantedAuthority> authorities = 
				roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.getUserRole()))
		        .collect(Collectors.toList());
		
		//4. 객체 반환하기
		System.out.println(user.getUserpass());
		return new User(user.getUserid(), user.getUserpass(), authorities);
	}
}
