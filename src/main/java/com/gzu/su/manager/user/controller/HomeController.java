package com.gzu.su.manager.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @Class HomeController    
 * @author mli8  
 * @date Mar 21, 201810:26:39 AM  
 * @Des home页面
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@RequestMapping(value = "/home")
	public String home(){
		return "home/home";
	}
}
