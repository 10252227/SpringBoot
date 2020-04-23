package com.yhz.sbd.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.yhz.sbd.common.vo.Result;
import com.yhz.sbd.modules.test.entity.City;

public interface CityService {
	List<City> getCitiesByCountyId(int countryId);
	PageInfo<City> getCitiesByPage(int currentPage,int pageSize,int countryId);
	Result<City> insertCity(City city);
	
}
