package com.jview.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

	private String name = null;
    private CommonsMultipartFile file = null;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CommonsMultipartFile getFile() {
        return file;
    }
    public void setFile(CommonsMultipartFile file) {
        this.file = file;
//        this.name = file.getOriginalFilename();
    }
	
}
