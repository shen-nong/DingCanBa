package com.dingcan.util;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传的表单
 * @author Administrator
 *
 */
public class FileUploadForm {

	private List<MultipartFile> files;
	
	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
