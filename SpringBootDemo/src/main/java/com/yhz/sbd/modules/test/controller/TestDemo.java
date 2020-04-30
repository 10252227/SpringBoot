package com.yhz.sbd.modules.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
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
	 * 搭建的springboot框架 http://127.0.0.1/test/Hello?key=fuck
	 */
	@RequestMapping("/Hello")
	public String getName(HttpServletRequest request, @RequestParam String key) {
		String value2 = request.getParameter("value");
		return "Hello world,this is spring boot demo" + key + "===" + value2;
	}

	/**
	 * 文件的下载
	 */

	@RequestMapping("/download")
	public ResponseEntity<Resource> downLoadFile(@RequestParam String fileName) {
		try {
			Resource resource = new UrlResource(Paths.get("D:\\fileupload\\" + fileName).toUri());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION,
							String.format("attachment; filename=\"%s\"", resource.getFilename()))
					.body(resource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/fileupload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int) downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/fileupload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int) downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
						String.format("attachment; filename=\"%s\"", fileName));

				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}

	}
}