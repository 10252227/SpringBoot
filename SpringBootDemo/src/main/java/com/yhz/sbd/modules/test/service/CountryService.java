package com.yhz.sbd.modules.test.service;

import com.yhz.sbd.modules.test.entity.Country;

public interface CountryService {
	Country getCountryByCountryId(int countryId);
	Country getCountryByCountryName(String countryName);
}
