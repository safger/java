package com.sn.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片上传修改
 * 
 * @author Administrator
 * 
 */
public class Imgedit {

	/**
	 * 
	 * @param reNum 删除图片标识
	 * @param imgNum 修改图片标识
 	 * @param dimg 原图
	 * @param img 修改后图
	 * @param imgFileName 
	 * @return String 组装好的图片名称
	 * @throws IOException
	 */
	public static String modify(String reNum, String imgNum, String dimg,
			MultipartFile[] img, HttpServletRequest request,boolean flag) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		if (dimg != null) {
			String oldList[] = dimg.split(",");
			for (int c = 0; c < oldList.length; c++) {
				al.add(oldList[c]);
			}

		}
		if (reNum != null && reNum.length() > 0) {
			String num[] =common.orderList(reNum.split(","));//排序
			int tt = al.size();
			if (tt == num.length) {
				al.clear();//清空

			} else {
				if (num != null && num.length > 0) {
					for (int c = 0; c < num.length; c++) {
						if (Integer.parseInt(num[c]) < tt) {
							int index=Integer.parseInt(num[c]);
							al.remove(index);
						}
					}
				}
			}
		}
		
		if (imgNum != null && imgNum.length() > 0) {
			String num[] = imgNum.split(",");
			// --------------取出上传图片----
			ArrayList<String> re = FileUpload.uploadFile(img, request,false);
			if (re != null && re.size() > 0) {
				for (int a = 0; a < re.size(); a++) {
					if (al != null && al.size() > 0) {
						// 判断是否有图片新增
						if (Integer.parseInt(num[a]) > al.size() - 1) {
							al.add(re.get(a));
						} else {
							// 无图片新增执行修改
							for (int c = 0; c < al.size(); c++) {
								al.set(Integer.parseInt(num[a]), re.get(a));
							}
						}
					}else{
						al.add(re.get(a));//当图片全部删除，并重新新增的时候进行添加
					}
					
				}
				if(flag){
					//压缩图片
					for(String r:re){
						ImageCompression mypic = new ImageCompression();
						String realPath = request.getSession().getServletContext().getRealPath(FileUpload.FILE_PATH);
						mypic.compressPic(realPath, realPath, r, "B_"+r, 900, 900, true);
						mypic.compressPic(realPath, realPath, r, "L_"+r, 300, 300, true);
					}
				}
			}
		}
		String imgRe = "";
		for (int c = 0; c < al.size(); c++) {
			imgRe += al.get(c) + ",";
		}
		imgRe=imgRe.length()>0?imgRe.substring(0,imgRe.length()-1):"";
		return imgRe;
	}
}
