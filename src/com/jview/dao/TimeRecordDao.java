package com.jview.dao;

import java.util.List;
import java.util.Map;

import com.jview.model.TimeRecord;
import com.jview.model.TimeRecordReference;

public interface TimeRecordDao {

	public List<TimeRecord> searchTimeRecord(Map<String, String> data);
	
	public List<TimeRecordReference> showTimeRecordReference(String tr_ref_kind, String tr_ref_dept);
}
