package com.yhz.sbd.modules.test.dao;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yhz.sbd.modules.test.entity.City;

@Mapper
public interface CityDao {
	@Select("select * from m_city where country_id=#{countryId}")
	List<City> getCitiesByCountyId(int countryId);
	
	List<City> getCitiesByCountyId2(int countryId);
	
	/*
	 * 多参数查询:注解@Param("cityName")绑定
	 * 不用注解为造成ibatis.binding.BindingException	
	 */
	@Select("select * from m_city where city_name = #{cityName} and local_city_name = #{localCityName}")
	City getCityByName(@Param("cityName")String cityName,@Param("localCityName")String localCityName);
	
	/*
	 * 条件查询
	 */
	@Select("<script>"+
			"select * from m_city"
			+"<where>"
			+"<if test='cityName != \"\" and cityName != null'>"
			+"and city_name = #{cityName}"
			+"</if>"
			+"<if test='localCityName != \"\" and localCityName != null'>"
			+"and local_city_name = #{localCityName}"
			+"</if>"
			+"</where>"
			+"limit 1"
			+ "</script>")
	City getCityByName2(@Param("cityName")String cityName,@Param("localCityName")String localCityName);
	
	@Insert("insert into m_city(city_name,local_city_name,country_id,date_created)"+
	"values(#{cityName},#{localCityName},#{countryId},#{dateCreated})")
	@Options(useGeneratedKeys=true,keyColumn="city_id",keyProperty="cityId")
	void insertCity(City city);
	
	@Update("update m_city set city_name = #{cityName} where city_id =#{cityId}")
	void updateCityByCityId(City city);
	
	@Delete("delete from m_city where city_id =#{cityId}")
	void deleteCityByCityId(int cityId);

}
