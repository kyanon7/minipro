package com.model2.mvc.web.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//==> 공통 Controller
//@Controller
@RequestMapping("/common")
public class CommonController {
	
	
	private static final String FILE_PATH = "C:\\Users\\Kiros Seagil\\Desktop\\사진";
	//setter Method 구현 않음
	
	public CommonController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping("/uploadAddProduct")
	public String uploadAddProduct(@RequestParam("fileName") MultipartFile file) throws Exception{
		
		if(!file.getOriginalFilename().isEmpty()) {
			file.transferTo(new File(FILE_PATH, file.getOriginalFilename()));
		}
		
		return "/product/addProduct";
	}
	
	@RequestMapping("uploadUpdateProduct")
	public String uploadUpdateProduct(@RequestParam("fileName") MultipartFile file) throws Exception{
		
		File saveFile = new File(FILE_PATH, file.getOriginalFilename());
		file.transferTo(saveFile);
		
//		if(!file.getOriginalFilename().isEmpty()) {
//			file.transferTo(new File(FILE_PATH, file.getOriginalFilename()));
//		}
		
		return "/product/updateProduct";
	}
}