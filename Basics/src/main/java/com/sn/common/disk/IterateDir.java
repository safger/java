package com.sn.common.disk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.sn.common.disk.bean.DirBean;
import com.sn.common.disk.bean.FileBean;
import com.sn.common.disk.bean.RootBean;

public class IterateDir {
	/*
	 * 获取根目录信息
	 */
	public static List<RootBean> getDiskInfo() {
		// 获取盘符
		File[] files = File.listRoots();
		List<RootBean> roots = new ArrayList<RootBean>();
		for (File file : files) {
			if (file.getTotalSpace() != 0) {
				RootBean rootBean = new RootBean();
				rootBean.setDiskPath(file.getAbsolutePath());
				rootBean.setDiskName(file.getAbsolutePath().charAt(0) + "");
				rootBean.setDiskSize(FormetFileSize(file.getTotalSpace()));
				rootBean.setAvilableSize(FormetFileSize(file.getFreeSpace()));
				roots.add(rootBean);
			}
		}
		return roots;
	}

	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	public static DirBean getFiles(String dirPath) throws Exception {
		File root = new File(dirPath);
		DirBean dirBean = null;
		if (root.exists()) {
			dirBean = new DirBean();
			String dirSize = "";
			int dirCount = 0;
			List<FileBean> filelist = new LinkedList<FileBean>();
			if (root.isDirectory()) {
				File[] files = root.listFiles();
				for (File file : files) {
					FileBean fileBean = new FileBean();
					String realPath = file.getAbsolutePath();
					fileBean.setFilePath(realPath);
					fileBean.setFileName(getFileName(realPath));
					if (file.isDirectory()) {
						fileBean.setFileType("DIR");
						// fileBean.setFileSize(FormetFileSize(getFileSize(file)));
						fileBean.setFileSize("");
					} else {
						fileBean.setFileType(getFileType(getFileName(realPath)));
						fileBean.setFileSize(FormetFileSize(getFileSizes(file)));
					}
					filelist.add(fileBean);
				}
			} else {
				dirSize = FormetFileSize(getFileSizes(root));
			}
			dirBean.setDirCount(dirCount);
			dirBean.setDirSize(dirSize);
			dirBean.setDirPath(dirPath);
			dirBean.setFiles(filelist);
		} else {
			System.out.println("文件或文件目录不存在");
		}
		return dirBean;
	}

	private static String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase();
	}

	// 获取文件名
	static String getFileName(String filePath) {
		String[] fileItems = filePath.split("\\\\");
		return fileItems[fileItems.length - 1];
	}

	// 取得文件大小
	public static long getFileSizes(File f) {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				return 0;
			}
			try {
				s = fis.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				return 0;
			}
		} else {
			System.out.println("文件不存在");
		}
		return s;
	}

	// 取得文件夹大小
	public static long getFileSize(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	public static String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public static long getlist(File f) {// 递归求取目录文件个数
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getlist(flist[i]);
				size--;
			}
		}
		return size;

	}
}
