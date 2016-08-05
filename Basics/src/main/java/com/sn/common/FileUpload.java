package com.sn.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

	public static final String FILE_PATH = "/upload/";

	// 文件上传
	public static ArrayList<String> uploadFile(MultipartFile[] file,
			HttpServletRequest request,Boolean isCompress) throws IOException {
		ArrayList<String> al = new ArrayList<String>();
		if (file != null && file.length > 0) {
			for (MultipartFile f : file) {
				if (!f.isEmpty()) {
					String realPath = request.getSession().getServletContext()
							.getRealPath(FILE_PATH);
					String fileName=f.getOriginalFilename();
					 String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
					String newFileName=new Date().getTime()+"."+prefix;
					File tempFile = new File(realPath, newFileName);
					if (!tempFile.getParentFile().exists()) {
						tempFile.getParentFile().mkdir();
					}
					if (!tempFile.exists()) { 
						tempFile.createNewFile();
					}
					f.transferTo(tempFile);
					//进行图片压缩
					if(isCompress){
						String path=realPath;
						ImageCompression imageCompression=new ImageCompression();
						imageCompression.compressPic(path, path, newFileName, "B_"+newFileName,900,900,true);
						imageCompression.compressPic(path, path, newFileName, "L_"+newFileName,300,300,true);
					}
					al.add(tempFile.getName());
				}

			}
		}
		return al;
	}

		
	public static File getFile(String fileName) {
		return new File(FILE_PATH, fileName);
	}
}