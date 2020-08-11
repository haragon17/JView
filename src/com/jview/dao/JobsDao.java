package com.jview.dao;

import java.util.List;
import java.util.Map;

import com.jview.model.Jobs;
import com.jview.model.JobsReference;

public interface JobsDao {

	public int getLastJobId();
	
	public int getLastJobReferenceId();
	
	public List<JobsReference> showJobsReference();
	
	public List<JobsReference> showJobsReferenceByCustomer(int id);
	
	public List<JobsReference> showJobsReferenceByProject(int id);
	
	public List<Jobs> searchJobs(Map<String, String> data);
	
	public Jobs searchJobsByID(int id);
	
	public Jobs findByJobName(String name);
	
	public List<Jobs> searchJobsReference_old(Map<String, String> data);
	
	public List<Jobs> searchTodayJobs(Map<String, String> data);
	
	public List<JobsReference> searchTodayJobsReference(Map<String, String> data);
	
	public List<JobsReference> getItemNameFromJobID(int id);
	
	public List<JobsReference> searchJobsReference(int id, String sort);
	
	public List<Jobs> searchJobForInvoice(int cus_id);
	
	public JobsReference searchJobsReferenceByID(int id);
	
	public JobsReference searchJobReferenceByName(String name);
	
	public void createJob(Jobs job);
	
	public void updateJob(Jobs job);
	
	public void deleteJob(int id);
	
	public void billedJobProjects(int id);
	
	public void createJobReference(JobsReference jobRef);
	
	public void updateJobReference(JobsReference jobRef);
	
	public void updateStatusJobReference(JobsReference jobRef);
	
	public void updateDateJobReference(JobsReference jobRef);
	
	public void deleteJobReference(int id);
	
	public JobsReference getDataFromJson(Object data);
	
	public void updateJobReferenceBatch(List<JobsReference> jobRefLs);
	
	public List<JobsReference> getJobDailyReportList(int job_id);
	
	public List<JobsReference> getJobDailyReportItem(int proj_id);
	
	public List<JobsReference> getListDataFromJson(Object data);
	
	public List<JobsReference> getListDataFromRequest(Object data);
	
	public List<Jobs> radarItem(Map<String, String> data);
	
	public List<Jobs> dailyRadar(Map<String, String> data, String[] dept_list);
	
	public List<Jobs> stackItem(Map<String, String> data);
	
	public List<Jobs> dailyStack(Map<String, String> data, String[] itm_list);
}
