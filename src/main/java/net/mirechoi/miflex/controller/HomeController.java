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
import org.springframework.web.multipart.MultipartFile;

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
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		
		model.addAttribute("serverTime", "몰라");
		
		return "main.index";
	}
	@GetMapping("/test")
	public String testForm() {
		return "main.test";
	}
	@PostMapping("/test")
	public String test(
			@RequestParam("test") String test,
			@RequestParam(value="file",required=false) MultipartFile file
			) {
		return null;
	}
	
}


