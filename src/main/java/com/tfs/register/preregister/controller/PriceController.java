package com.tfs.register.preregister.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

@Controller
public class PriceController {
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

	@RequestMapping("/price")
	public String price(@RequestParam(value="code") String code, Model model) {
		logger.info("Request /price?code={}", code);
		model.addAttribute("code", code);
		model.addAttribute("transactionTime", now().format(ofPattern("yyyy/MM/dd HH:mm:ss")));
		switch (code.length()){
			case 4:
				return "price1";
			case 5:
			case 9:
				return "price2";
			default:
				return "price1";
		}
	}
}
