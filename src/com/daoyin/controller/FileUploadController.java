package com.daoyin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daoyin.service.UserService;
import com.daoyin.util.WebUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("index")
	@ResponseBody
	public Object index(HttpServletRequest request){
		return WebUtil.getMap(new String[]{"isSuccess","user"}, new Object[]{true,userService.getUser(1l)});
	}
	
	
	@RequestMapping(value="/findAll")
	@ResponseBody
	public Object findAll(HttpServletRequest request){
		return WebUtil.getMap(new String[]{"isSuccess","users"}, new Object[]{true,userService.findAll()});
	}
	
}
