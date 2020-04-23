package com.yhz.sbd.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yhz.sbd.modules.test.entity.Country;
import com.yhz.sbd.modules.test.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountryController {
	@Autowired
	private CountryService countryService;
	/*
	 * http://localhost/api/country/522
	 */
	@RequestMapping("/country/{countryId}")
	public Country getCountryByCountryId(@PathVariable int countryId) {
		return countryService.getCountryByCountryId(countryId);
		
	}
	/*
	 * http://localhost/api/country?countryName=china
	 */
	@RequestMapping("/country")
	public Country getCountryByCountryName(@RequestParam String countryName) {
		return countryService.getCountryByCountryName(countryName);
		
	}

}
