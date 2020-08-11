package com.jview.dao;

import java.util.List;

import com.jview.model.Reference;

public interface ReferenceDao {
	
	public List<Reference> showDBReference(String kind, String dept);

	public List<Reference> showDepartmentReference(int level);
	
	public List<Reference> showJobStatus();
	
	public List<Reference> showJobReference(String kind, String dept);
}
