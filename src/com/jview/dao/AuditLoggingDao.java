package com.jview.dao;

import java.util.List;
import java.util.Map;

import com.jview.model.AuditLogging;

public interface AuditLoggingDao {

	public List<AuditLogging> searchAuditLogging(Map<String, String> data);
	
}
