package com.yhz.sbd.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
