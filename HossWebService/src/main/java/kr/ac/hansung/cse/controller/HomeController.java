package kr.ac.hansung.cse.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Locale locale, Model model) {
		
		logger.info("info level: Welcome home! The client locale is {}.", locale);
		
		String url = request.getRequestURL().toString();
		String clientIPaddress = request.getRemoteAddr();
		
		logger.info("Request URL: {}, client IP: {}", url, clientIPaddress );
        
		return "home";
	}
	
}
