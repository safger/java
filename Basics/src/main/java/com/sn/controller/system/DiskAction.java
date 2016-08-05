package com.sn.controller.system;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sn.common.disk.IterateDir;
import com.sn.common.disk.bean.DirBean;
import com.sn.common.disk.bean.RootBean;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

@Controller
@RequestMapping("system/disk/")
public class DiskAction {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	/**
	 * @see 根据路径显示文件
	 * @author xiao
	 * @param requestPath
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("showDir")
	@ResponseBody
	public String showDir(String path, Map<String, Object> model) throws Exception {
		if (path != null && path.length() > 1) {
			String nowPath = path;
			if (nowPath.endsWith("/")) {
				nowPath = nowPath.substring(0, path.length() - 1);
			}
			DirBean dirBean = IterateDir.getFiles(nowPath);
			String jsonArray = JSONArray.toJSONString(dirBean);
			response.getWriter().print(jsonArray);
		}
		return null;
	}

	/**
	 * @see 显示系统根目录
	 * @author xiao
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("showRoot")
	@ResponseBody
	public String showRoot(Map<String, Object> model) throws IOException {
		List<RootBean> roots = IterateDir.getDiskInfo();
		if (roots != null) {
			String jsonArray = JSON.toJSONString(roots);
			System.out.println(jsonArray);
			response.getWriter().print(jsonArray);
		}
		return null;
	}
}
