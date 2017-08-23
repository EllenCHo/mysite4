package com.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.service.fileUploadService;

@Controller
@RequestMapping(value="fileUpload")
public class FileUploadController {
	@Autowired
	fileUploadService fileUploadservice;
	
	@RequestMapping(value="form")
	public String form() {
		return "fileUpload/form";
	}
	
	@RequestMapping(value="result", method=RequestMethod.POST)
	public String result(@RequestParam("email") String email, @RequestParam("file") MultipartFile file, Model model) {
		System.out.println(email);
		System.out.println(file);
		System.out.println(file.getOriginalFilename());
		
		String saveName = fileUploadservice.upload(file);
		model.addAttribute("saveName", saveName);
		return "fileUpload/result";
	}
}
