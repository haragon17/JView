package com.jview.dao;

import java.util.List;
import java.util.Map;

import com.jview.model.Report;

public interface ReportDao {

	public List<Report> searchReport(Map<String, String> data);
	
}
