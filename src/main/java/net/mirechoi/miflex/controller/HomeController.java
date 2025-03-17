package net.mirechoi.miflex.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private UsersMapper userMapper;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("로그인 정보 : " + auth.getName());
    	
    	if(auth != null) {
    		Users cuser = userMapper.getUserForUserId(auth.getName());    		
    		model.addAttribute("cuser", cuser );
    		System.out.println(cuser);
    	}
    	
		return "main.index";
	}


}

