package kr.ac.hansung.cse.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hansung.cse.model.Hospital;
import kr.ac.hansung.cse.service.HossService;

@Controller
public class LoginController {
	@Autowired
	private HossService hossService;

	@RequestMapping(value = "/login")
	public String showLogin(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {

		if (error != null) {
			model.addAttribute("errorMsg", "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
		}

		if (logout != null) {
			model.addAttribute("logoutMsg", "로그아웃 되었습니다.");
		}

		return "login";
	}
	

	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signup(Model model) {
		Hospital hospital = new Hospital();
		
		model.addAttribute("hospital", hospital);

		return "signup";
	}

	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public String signupPost(@Valid Hospital hospital, BindingResult result, HttpServletResponse response) throws Exception {
		if (result.hasErrors()) {
			System.out.println("=== Web Form data has some errors ===");
			List<ObjectError> errors = result.getAllErrors();
			
			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}
			return "signup";
		}
		
		if(hossService.checkHospitalExistence(hospital.getHospital_id())) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 존재하는 id 입니다.'); location.href='signup';</script>");
			out.flush();
			return "signup";
		}

		if(!hossService.addHospital(hospital))
			System.out.println("Adding hospital cannot be done");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('계정이 등록 되었습니다'); location.href='login';</script>");
		out.flush();

		return "login";
	}
}
