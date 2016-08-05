package com.sn.controller.system;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("system/JsContext/")
public class JsContext  {

	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private  HttpServletResponse response;
	/**
	 * @return 基础数据字典
	 */
	@RequestMapping("DictionaryData")
	@ResponseBody
	public String DictionaryData() throws IOException {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = (String) request.getSession().getServletContext().getAttribute("baseDatadictionary_list");
		out.print("var basetemp="+data);
		return null;
	}
}
