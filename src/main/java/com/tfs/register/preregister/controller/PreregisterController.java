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
public class PreregisterController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	private Model2Jsonstr model2Jsonstr;
	
	@RequestMapping(value="/showpreregisters",method = RequestMethod.GET)
	public String showPreregisters(Model model) {
		ThriftClient client = new ThriftClient();   
		List<Company> companys = client.showPrereigter(); 
		model.addAttribute("companys", companys);
		return "showpreregisters";
	}
	
	@RequestMapping(value="/preregister",method = RequestMethod.GET)
	public String preregisterGet(Company company) {
		
		return "preregister";
	}
	
	@RequestMapping(value="/preregister",method = RequestMethod.POST)
	public String preregisterPost(Company company) {
		
		String jsonstr = model2Jsonstr.Company2Jsonstr(company);
		System.out.println(jsonstr);
		
		ThriftClient client = new ThriftClient();   
		client.startClient(jsonstr); 
		
		return "preregister";
	}
}
