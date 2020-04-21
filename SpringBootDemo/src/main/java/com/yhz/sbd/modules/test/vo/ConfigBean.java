package com.yhz.sbd.modules.test.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component    //注册为spring
@PropertySource("classpath:config/applicationTest.properties")   //读取配置文件，绑定配置文件的位置，全局配置文件无需绑定
@ConfigurationProperties(prefix = "com.yhz") //设置配置文件属性  指定了前缀，无需@Value
public class ConfigBean {

	private int port;
	private String name;
	private int age;
	private String desc;
	private String random;
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}

}
