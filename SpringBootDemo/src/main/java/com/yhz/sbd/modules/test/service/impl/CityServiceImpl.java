package com.yhz.sbd.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.common.vo.Result.ResultEnum;
import com.yhz.sbd.modules.test.dao.CityDao;
import com.yhz.sbd.modules.test.entity.City;
import com.yhz.sbd.modules.test.service.CityService;
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;
	
	@Override
	public List<City> getCitiesByCountyId(int countryId) {
		
		return cityDao.getCitiesByCountyId2(countryId);
	}
	
	@Override
	public City getCityByName(String cityName, String localCityName) {
		return cityDao.getCityByName2(cityName, localCityName);
	}
	
	
	/*@Override
	public City getCityByName2(String cityName, String localCityName) {
		return cityDao.getCityByName2(cityName, localCityName);
	}*/

	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = cityDao.getCitiesByCountyId(countryId);
		return new PageInfo<>(cities);
	}
	
	
	@Override
	public Result<City> insertCity(City city) {
		Result<City> result = new Result<City>(ResultEnum.SUCCESS.status,"Insert success!");
		try {
			cityDao.insertCity(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
			
		}
		return result;
	}
	
	@Override
	@Transactional	//事务注解：默认自动回滚
	//@Transactional(noRollbackFor=ArithmeticException.class)
	public Result<City> updateCityByCityId(City city) {
		Result<City> result = new Result<City>(ResultEnum.SUCCESS.status,"update success!");
		/*try {
			cityDao.updateCityByCityId(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
			
		}*/
		
		cityDao.updateCityByCityId(city);
		result.setObject(city);
		int i = 1/0;
		return result;
	}
	
	@Override
	public Result<Object> deleteCityByCityId(int cityId) {
		Result<City> result = new Result<City>(ResultEnum.SUCCESS.status,"update success!");
		try {
			cityDao.deleteCityByCityId(cityId);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
			
		}
		return null;
	}
	
	

}
