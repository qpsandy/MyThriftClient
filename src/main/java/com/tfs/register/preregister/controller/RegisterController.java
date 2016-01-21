package com.tfs.register.preregister.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tfs.register.preregister.model.Company;
import com.tfs.register.preregister.service.Model2Jsonstr;
import com.tfs.register.preregister.thriftClient.ThriftClient;

@Controller
public class RegisterController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	private Model2Jsonstr model2Jsonstr;
		
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String registerGet(Company company) {
		
		return "register";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String registerPost(Company company) {
		
		String jsonstr = model2Jsonstr.Company2Jsonstr(company);
		System.out.println(jsonstr);
		
		ThriftClient client = new ThriftClient();   
		client.startClient(jsonstr); 
		
		return "/register";
	}
}
