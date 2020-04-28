package com.yhz.sbd.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.modules.test.entity.City;
import com.yhz.sbd.modules.test.service.CityService;

@RestController
@RequestMapping("/api")
public class CityController {
	@Autowired
	private CityService cityService;
	
	/*
	 * http://localhost/api/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountyId(@PathVariable int countryId){
		return cityService.getCitiesByCountyId(countryId);
	}
	
	/*
	 * http://localhost/api/city?cityName=testName1&localCityName=bbb
	 */
	@RequestMapping("/city")
	public City getCityByName(@RequestParam(required=false) String cityName, 
			@RequestParam(required=false) String localCityName) {
		return cityService.getCityByName(cityName, localCityName);
		
	}
	
	/*
	 * http://localhost/api/cities?currentPage=1&pageSize=10&countryId=522
	 */
	@RequestMapping("/cities")
	public PageInfo<City> getCitiesByPage(@RequestParam int currentPage, 
			@RequestParam int pageSize,@RequestParam int countryId) {
		return cityService.getCitiesByPage(currentPage, pageSize, countryId);
	}
	
	@PostMapping(value="/city",consumes="application/json")
	public Result<City> insertCity(@RequestBody City city) {
		return cityService.insertCity(city);
		
	}
	
	/*
	 * delete请求：@PutMapping  并且consumes="application/x-www-form-urlencoded
	 * 参数注解：@ModelAttribute
	 * 
	 */
	@PutMapping(value="/city", consumes="application/x-www-form-urlencoded")
	public Result<City> updateCityByCityId(@ModelAttribute City city) {
		return cityService.updateCityByCityId(city);
	}
	
	/*
	 * delete:@DeleteMapping("/city/{cityId}")
	 * 请求路径上的参数：@PathVariable
	 */
	@DeleteMapping("/city/{cityId}")
	public Result<Object> deleteCityByCityId(@PathVariable int cityId) {
		return cityService.deleteCityByCityId(cityId);
		
	}
}
