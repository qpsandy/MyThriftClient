package com.tfs.register.preregister.service;

import org.springframework.stereotype.Service;

import com.tfs.register.preregister.model.Company;

import net.sf.json.JSONObject;

@Service
public class Model2Jsonstr {
	
	public String Company2Jsonstr(Company company) {
		JSONObject jsonObject = JSONObject.fromObject(company);
		return jsonObject.toString();
	}
}
