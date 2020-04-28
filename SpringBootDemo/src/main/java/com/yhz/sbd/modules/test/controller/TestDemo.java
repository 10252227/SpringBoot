package com.yhz.sbd.modules.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yhz.sbd.modules.test.vo.ConfigBean;

@Controller
@RequestMapping("/test")
@RestController
public class TestDemo {
	private final static Logger LOGGER = LoggerFactory.getLogger(TestDemo.class);

	@RequestMapping("/log")
	public String logTest() {
		// TRACE<DEBUG<INFO<WARN<ERROR
		LOGGER.trace(" This is TRACE log.");
		LOGGER.debug(" This is DEBUG log.");
		LOGGER.info(" This is INFO log.");
		LOGGER.warn(" This is WARN log.");
		LOGGER.error(" This is ERROR log.2222");
		return "this is log test！";
	}

	/**
	 * 全局配置application.properties 直接以属性的方式写进来
	 */
	@Value("${server.port}") // 读取配置文件的值
	private int port;
	@Value("${com.yhz.name}")
	private String name;
	@Value("${com.yhz.age}")
	private int age;
	@Value("${com.yhz.desc}")
	private String desc;
	@Value("${com.yhz.random}")
	private String random;

	/**
	 * 
	 * 局部配置applicationTest.properties
	 * 
	 */
	@Autowired
	private ConfigBean configBean;

	@RequestMapping("/config")
	public String configTest() {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("===").append(name).append("===").append(age).append("===").append(desc).append("===")
				.append(random).append("===").append("</br>");
		sb.append(configBean.getName()).append("===").append(configBean.getAge()).append("====")
				.append(configBean.getDesc()).append("===").append(configBean.getRandom());

		return sb.toString();
	}

	/**
	 * 
	 * 搭建的springboot框架
	 * http://127.0.0.1/test/Hello?key=fuck
	 */
	@RequestMapping("/Hello")
	public String getName(HttpServletRequest request ,@RequestParam String key) {
		String value2 = request.getParameter("value");
		return "Hello world,this is spring boot demo" + key + "===" + value2;
	}
}
