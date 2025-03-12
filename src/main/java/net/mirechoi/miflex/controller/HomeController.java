package net.mirechoi.miflex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.mirechoi.miflex.dto.Users;
import net.mirechoi.miflex.mapper.UsersMapper;
import net.mirechoi.miflex.service.UserService;
import net.mirechoi.miflex.util.IpUtil;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		
		model.addAttribute("serverTime", "몰라");
		
		return "main.index";
	}
	@GetMapping("/login")
	public String LoginForm(@RequestParam(value="error", required=false)String error, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀렸습니다.");
		}
		return "main.login";
}
	
	@GetMapping("/signup")
	public String signupForm() {
		
		return "main.signup";
}
	@PostMapping("/signup")
	public String SignUp(
		@RequestParam("userid") String userid,
		@RequestParam("userpass") String userpass,
		@RequestParam("username") String username,
		@RequestParam("useremail") String useremail,
		@RequestParam("usertel") String usertel,
		@RequestParam(value="zipcode", required=false) Integer zipcode,
		@RequestParam("address") String address,
		@RequestParam("detail_address") String detail_address,
		@RequestParam("extra_address") String extra_address,
		@RequestParam("userprofile") String userprofile,
		HttpServletRequest request,
		RedirectAttributes redirectAttributes,
		Model model
	) {
		
		//아이디 중복검사 
		if(userService.isUseridExists(userid)) {
			model.addAttribute("error","중복된 아이디 입니다.");
			model.addAttribute("userid",userid);
			return "main.signup";
		}
		//1. ip주소 받기
			String userip = IpUtil.getClientIp(request);
			
			//user dto 값 입력 작업 해야 함
			Users users = new Users();
			userService.signupUser(users);
		//회원가입 처리
		
		return null;
	}
}
