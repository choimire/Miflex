package net.mirechoi.miflex.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.mirechoi.miflex.dto.Users;
import net.mirechoi.miflex.mapper.UsersMapper;
import net.mirechoi.miflex.service.UserService;
import net.mirechoi.miflex.util.IpUtil;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UsersMapper userMapper;
	
	@GetMapping("/login")
	public String LoginForm(@RequestParam(value="error", required=false) String error, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "아이디 또는 비밀번호가 틀렸습니다.");
		}
		return "main.login";
	}
	
	
	
	@GetMapping("/test")
	public String testForm() {
		return "main.test";
	}
	
	@PostMapping("/test")
	public String test(
	 @RequestParam("test") String test,
	 @RequestParam(value="file", required=false) MultipartFile file			 
	) {
		System.out.println(test +", " + file);
		return null;
	}
	
	
	
	@GetMapping("/signup")
	public String SignUpForm(@RequestParam(value="error", required=false) String error, Model model) {
		if(error != null) {
			model.addAttribute("errorMessage", "업로드중 에러발생.");
		}
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
			@RequestParam("detailAddress") String detailAddress,
			@RequestParam("extraAddress") String extraAddress,
			@RequestParam("userprofile") String userprofile,
			@RequestParam(value="userimg", required=false) MultipartFile userimg,
			HttpServletRequest request,
			Model model
			) {

    	   //이미지 업로드 경로 설정
		   String uploadFolder = request.getServletContext().getRealPath("/res/upload/user/");

		   //아이디 중복검사

		   if(userService.isUseridExists(userid)) {
			  model.addAttribute("error", "이미 사용중인 아이디입니다.");
			  model.addAttribute("userid", userid);
			  return "main.signup";
		   }

		   //1. ip주소 받기
		   String userip = IpUtil.getClientIp(request);
		   
		   Users user = new Users();
		   user.setUserid(userid);
		   user.setUserpass(userpass);
		   user.setUsername(username);
		   user.setUseremail(useremail);
		   user.setUsertel(usertel);
		   if(zipcode != null) { user.setZipcode(zipcode); }else { user.setZipcode(0); }
		   user.setAddress(address);
		   user.setDetailAddress(detailAddress);
		   user.setExtraAddress(extraAddress);
		   user.setUserprofile(userprofile);
		   user.setEditAt(new Timestamp(System.currentTimeMillis()));
		   user.setUserip(userip);
		   	   
		   if(userimg != null && !userimg.isEmpty()) {
			
	              String oFilename = userimg.getOriginalFilename();
	              String ext = oFilename.substring(oFilename.lastIndexOf(".") + 1).toLowerCase();
			      
	              List<String> allowExt = Arrays.asList("jpg", "png", "gif", "web", "webp");
	              if(!allowExt.contains(ext)) {
	            	  model.addAttribute("message", "이미지 파일만 업로드 가능합니다.");
	            	  return "main.signup";
	              }
	              
	              //UUID (임의로 이름을 생성함)
	              String newFilename = UUID.randomUUID().toString() + "." + ext;
	           try {   
	              File saveFile = new File(uploadFolder, newFilename);
	              userimg.transferTo(saveFile);
	              user.setUserimg(newFilename);
	              
			   }catch(Exception e) {
				   e.printStackTrace();
				   model.addAttribute("message", "업로드중 에러 발생" + e.getMessage());
				   return "main.signup";
			   }
		   }
		  //System.out.println(user.toString()); 
		   userService.signupUser(user);
		   
		   //회원가입 처리   
		   
		return "main.login";
	}
	
	//회원목록
	@GetMapping("/member")
	public String MemberView(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println("로그인 정보 : " + auth.getName());
    	
    	if(auth != null) {
    		Users cuser = userMapper.getUserForUserId(auth.getName());    		
    		model.addAttribute("cuser", cuser );
    		System.out.println(cuser);
    	}
		return "main.member";
	}
	
	@PostMapping("/member/useredit")
	public String MemberEdit(
			@RequestParam("userid") String userid,
			@RequestParam("userpass") String userpass,
			@RequestParam("username") String username,
			@RequestParam("useremail") String useremail,
			@RequestParam("usertel") String usertel,
			@RequestParam(value="zipcode", required=false) Integer zipcode,
			@RequestParam("address") String address,
			@RequestParam("detailAddress") String detailAddress,
			@RequestParam("extraAddress") String extraAddress,
			@RequestParam("userprofile") String userprofile,
			@RequestParam(value="userimg", required=false) MultipartFile userimg,
			HttpServletRequest request,
			Model model) {
		
		   //사용자 정보
		   Users user = userService.getUserByUserid(userid);
		
		   //비밀번호를 새로 썼을 경우 변경해줌
		   if(userpass != null && !userpass.trim().isEmpty()) {
			   user.setUserpass(userpass);
		   }
		
		   //이미지를 업로드 했을 경우
		   if(userimg != null && !userimg.isEmpty()) {
			
	    	//이미지 업로드 경로 설정
			String uploadFolder = request.getServletContext().getRealPath("/res/upload/user/");  
			   
            //이전 이미지가 있다면 삭제하기
			if(user.getUserimg() != null) {
			   File oldFile = new File(uploadFolder, user.getUserimg());
			   if(oldFile.exists()) oldFile.delete();
			}
			   
	        String oFilename = userimg.getOriginalFilename();
	        String ext = oFilename.substring(oFilename.lastIndexOf(".") + 1).toLowerCase();
			      
	        List<String> allowExt = Arrays.asList("jpg", "png", "gif", "webp");
	        if(!allowExt.contains(ext)) {
	            model.addAttribute("message", "이미지 파일만 업로드 가능합니다.");
	            return "main.signup";
	        }
	              
	        //UUID (임의로 이름을 생성함)
	        String newFilename = UUID.randomUUID().toString() + "." + ext;
	        try {   
	              File saveFile = new File(uploadFolder, newFilename);
	              userimg.transferTo(saveFile);
	              user.setUserimg(newFilename);
	              
			   }catch(Exception e) {
				   e.printStackTrace();
				   model.addAttribute("message", "업로드중 에러 발생" + e.getMessage());
				   return "main.member";
			   }          
		   }
		   
          String userip = IpUtil.getClientIp(request);
		   
		   user.setUserid(userid);
		   user.setUsername(username);
		   user.setUseremail(useremail);
		   user.setUsertel(usertel);
		   if(zipcode != null) { user.setZipcode(zipcode); }else { user.setZipcode(0); }
		   user.setAddress(address);
		   user.setDetailAddress(detailAddress);
		   user.setExtraAddress(extraAddress);
		   user.setUserprofile(userprofile);
		   user.setUserip(userip);
		   userService.setUpdateUserByUsers(user);
		   
		return "redirect:/member";
	}
	
	@GetMapping("/admin")
	public String adminList(Model model) {
		List<Users> userList = userService.allUsersByAdmin();
		model.addAttribute("userList", userList);
		
		return "main.adminList";
	}
	
}
