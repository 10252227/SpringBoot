package com.yhz.sbd.modules.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhz.sbd.modules.test.entity.City;
import com.yhz.sbd.modules.test.entity.Country;
import com.yhz.sbd.modules.test.service.CityService;
import com.yhz.sbd.modules.test.service.CountryService;

@Controller
@RequestMapping("/test")
public class testPageController {
	
	@Autowired 
	private CountryService countryService;
	@Autowired 
	private CityService cityService;
	
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		int countryId = 522;
		Country country = countryService.getCountryByCountryId(countryId);
		List<City> cities = cityService.getCitiesByCountyId(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		City city = cities.get(0);

		modelMap.addAttribute("thymeleafTitle", "thymeleaf Title");
		modelMap.addAttribute("checked", true);
		modelMap.addAttribute("currentNumber", 99);
		modelMap.addAttribute("changeType", "checkbox");
		modelMap.addAttribute("baiduUrl", "http://www.baidu.com");
		modelMap.addAttribute("shopLogo", "http://cdn.duitang.com/uploads"
				+ "/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelMap.addAttribute("country", country);
		modelMap.addAttribute("city", city);
		modelMap.addAttribute("updateCityUri", "/api/city");
		modelMap.addAttribute("cities", cities);
		//modelMap.addAttribute("template", "test/index");
		return "index";	
	}
	
	@RequestMapping("/indexSimple")
	public String testIndexSimplePage(ModelMap modelMap) {
		return "indexSimple";
		
		
	}
	
	/**
	 * 单个文件的上传
	 */
	@PostMapping(value="/upload", consumes = "multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, 
			RedirectAttributes redirectAttributes){
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
			return "redirect:/test/index";
		}

		try {
			String fileName = file.getOriginalFilename();
			String destFilePath = "D:\\fileupload\\" + fileName;
			File destFile = new File(destFilePath);
			file.transferTo(destFile);

			// 使用工具类Files来上传文件
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(destFileName);
//			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message", "Upload file success.");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Upload file failed.");
			return "redirect:/test/index";
		}

		return "redirect:/test/index";
		
	}
	
	/**
	 * 上传多个文件
	 */
	@PostMapping(value="/uploadfiles", consumes="multipart/form-data")
	public String uploadFiles(@RequestParam MultipartFile[] files, 
			RedirectAttributes redirectAttributes) {
		boolean isEmpty = true;

		try {
			for (MultipartFile file : files) {
				if (file.isEmpty()) {
//				break;
					continue;
				}

				String fileName = file.getOriginalFilename();
				String destFilePath = "D:\\fileupload\\" + fileName;
				File destFile = new File(destFilePath);
				file.transferTo(destFile);

				isEmpty = false;
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Upload file failed.");
			return "redirect:/test/index";
		}

		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "Please select file.");
		} else {
			redirectAttributes.addFlashAttribute("message", "Upload file success.");
		}
		return "redirect:/test/index";
	}
}
