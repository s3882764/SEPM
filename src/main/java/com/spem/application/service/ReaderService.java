package com.spem.application.service;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spem.application.pojo.Reader;

public interface ReaderService {

	public List<Reader> readExcel(InputStream inputStream);
	
	public boolean isCSV(MultipartFile file);
	
	public boolean isExcel(MultipartFile file);
	
	public List<Reader> readCSV(InputStream inputStream);

}
