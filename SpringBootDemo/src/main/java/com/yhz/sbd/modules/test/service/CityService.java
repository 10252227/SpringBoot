package com.yhz.sbd.modules.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.modules.test.entity.City;

public interface CityService {
	List<City> getCitiesByCountyId(int countryId);
	
	City getCityByName(String cityName,String localCityName);
	
	/*City getCityByName2(@Param("cityName")String cityName,@Param("localCityName")String localCityName);*/
	
	PageInfo<City> getCitiesByPage(int currentPage,int pageSize,int countryId);
	
	Result<City> insertCity(City city);
	
	Result<City> updateCityByCityId(City city);
	
	Result<Object> deleteCityByCityId(int cityId);
	
}
