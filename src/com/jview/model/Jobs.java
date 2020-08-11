package com.jview.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Jobs {

	private int job_id;
	private String job_name;
	private int proj_ref_id;
	private float amount;
	private String dept;
	private String job_status;
	private String job_in;
	private String job_out;
	private String job_dtl;
	private String job_number;
	private int cretd_usr;
	private String cretd_date;
	private String update_date;
	private Timestamp job_in_ts;
	private Timestamp job_out_ts;
	private String cus_name;
	private String cus_code;
	private String proj_name;
	private String itm_name;
	private int cus_id;
	private int proj_id;
	private int itm_id;
	private BigDecimal total_amount;
	private int remain_job;
	private int remain_item;
	private int payment_terms;
	
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public int getProj_ref_id() {
		return proj_ref_id;
	}
	public void setProj_ref_id(int proj_ref_id) {
		this.proj_ref_id = proj_ref_id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public String getJob_in() {
		return job_in;
	}
	public void setJob_in(String job_in) {
		this.job_in = job_in;
	}
	public String getJob_out() {
		return job_out;
	}
	public void setJob_out(String job_out) {
		this.job_out = job_out;
	}
	public String getJob_dtl() {
		return job_dtl;
	}
	public void setJob_dtl(String job_dtl) {
		this.job_dtl = job_dtl;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public int getCretd_usr() {
		return cretd_usr;
	}
	public void setCretd_usr(int cretd_usr) {
		this.cretd_usr = cretd_usr;
	}
	public String getCretd_date() {
		return cretd_date;
	}
	public void setCretd_date(String cretd_date) {
		this.cretd_date = cretd_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public Timestamp getJob_in_ts() {
		return job_in_ts;
	}
	public void setJob_in_ts(Timestamp job_in_ts) {
		this.job_in_ts = job_in_ts;
	}
	public Timestamp getJob_out_ts() {
		return job_out_ts;
	}
	public void setJob_out_ts(Timestamp job_out_ts) {
		this.job_out_ts = job_out_ts;
	}
	public String getCus_name() {
		return cus_name;
	}
	public void setCus_name(String cus_name) {
		this.cus_name = cus_name;
	}
	public String getCus_code() {
		return cus_code;
	}
	public void setCus_code(String cus_code) {
		this.cus_code = cus_code;
	}
	public String getProj_name() {
		return proj_name;
	}
	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}
	public String getItm_name() {
		return itm_name;
	}
	public void setItm_name(String itm_name) {
		this.itm_name = itm_name;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public int getProj_id() {
		return proj_id;
	}
	public void setProj_id(int proj_id) {
		this.proj_id = proj_id;
	}
	public int getItm_id() {
		return itm_id;
	}
	public void setItm_id(int itm_id) {
		this.itm_id = itm_id;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public int getRemain_job() {
		return remain_job;
	}
	public void setRemain_job(int remain_job) {
		this.remain_job = remain_job;
	}
	public int getRemain_item() {
		return remain_item;
	}
	public void setRemain_item(int remain_item) {
		this.remain_item = remain_item;
	}
	public int getPayment_terms() {
		return payment_terms;
	}
	public void setPayment_terms(int payment_terms) {
		this.payment_terms = payment_terms;
	}
	
}
