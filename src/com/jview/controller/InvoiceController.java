package com.jview.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.DocumentException;
import com.jview.dao.InvoiceDao;
import com.jview.dao.JobsDao;
import com.jview.model.Invoice;
import com.jview.model.InvoiceCompany;
import com.jview.model.InvoiceReference;
import com.jview.model.Topix;
import com.jview.model.TopixConfig;
import com.jview.model.TopixReference;
import com.jview.report.PrintInvoice_iText;
import com.jview.security.UserDetailsApp;
import com.jview.security.UserLoginDetail;

import net.sf.json.JSONObject;

@Controller
public class InvoiceController {

	private ApplicationContext context;
	private InvoiceDao invoiceDao;
	private JobsDao jobsDao;
	
	private static final Logger logger = Logger.getLogger(InvoiceController.class);
	
	public InvoiceController() {
		this.context = new ClassPathXmlApplicationContext("META-INF/jview-context.xml");
		this.invoiceDao = (InvoiceDao) this.context.getBean("InvoiceDao");
		this.jobsDao = (JobsDao) this.context.getBean("JobsDao");
	}
	
	
	@RequestMapping(value = "/invoice")
	public ModelAndView viewInvoicePage(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
//		session.setAttribute("inv_id", "");
		session.setAttribute("inv_proj_no", "");
		session.setAttribute("inv_company_id", "");
		session.setAttribute("delivery_start", "");
		session.setAttribute("delivery_limit", "");
		session.setAttribute("inv_cus_id", "");
		session.setAttribute("inv_bill_type", "");
		
		UserDetailsApp user = UserLoginDetail.getUser();
		int type = user.getUserModel().getUsr_type();
		if (type == 0 || type == 1) {
			return new ModelAndView("Invoice");
		} else {
			return new ModelAndView("AccessDenied");
		}
	}
	
	@RequestMapping(value = "/searchInvoice")
	public ModelAndView searchInvoice(HttpServletRequest request, HttpServletResponse response){

		HttpSession session = request.getSession();
		List<Invoice> inv = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("inv_proj_no", (String)session.getAttribute("inv_proj_no"));
		map.put("inv_company_id", (String)session.getAttribute("inv_company_id"));
		map.put("delivery_start", (String)session.getAttribute("delivery_start"));
		map.put("delivery_limit", (String)session.getAttribute("delivery_limit"));
		map.put("inv_cus_id", (String)session.getAttribute("inv_cus_id"));
		map.put("inv_bill_type", (String)session.getAttribute("inv_bill_type"));
		map.put("first_inv", (String)session.getAttribute("first_inv"));
		
		
		if(map.get("delivery_start")!=null && !map.get("delivery_start").isEmpty()){
			String delivery_start = map.get("delivery_start");
			String[] parts = delivery_start.split("/");
			String month = parts[0];
			String year = parts[1];
			String myDate = "20"+year+"-"+month+"-01";
			map.put("delivery_start", myDate);
		}
		
		if(map.get("delivery_limit")!=null && !map.get("delivery_limit").isEmpty()){
			String delivery_limit = map.get("delivery_limit");
			String[] parts = delivery_limit.split("/");
			String month = parts[0];
			String year = parts[1];
			String myDate = "20"+year+"-"+month+"-01";
			map.put("delivery_limit", myDate);
		}
		
		inv = invoiceDao.searchInvoice(map);
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", inv);
		jobj.put("total", inv.size());

		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/searchInvoiceReference")
	public ModelAndView searchInvoiceReference(HttpServletRequest request, HttpServletResponse response){

//		HttpSession session = request.getSession();
//		int id = Integer.parseInt((String) session.getAttribute("inv_id"));
		
		List<InvoiceReference> inv_ref = null;
		int inv_id = Integer.parseInt(request.getParameter("inv_id"));
		
		inv_ref = invoiceDao.searchInvoiceReference(inv_id);
		
//		System.out.println("CURRENCY = "+inv_ref.get(0).getInv_currency());
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", inv_ref);
		jobj.put("total", inv_ref.size());

		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/searchInvoiceParam")
	public void searchInvoiceParam(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
//		session.setAttribute("inv_id", request.getParameter("inv_id"));
		session.setAttribute("inv_proj_no", request.getParameter("sinv_proj_no"));
		session.setAttribute("inv_company_id", request.getParameter("sinv_company_id"));
		session.setAttribute("delivery_start", request.getParameter("delivery_start"));
		session.setAttribute("delivery_limit", request.getParameter("delivery_limit"));
		session.setAttribute("inv_cus_id", request.getParameter("scus_id"));
		session.setAttribute("inv_bill_type", request.getParameter("sinv_bill_type"));
		session.setAttribute("first_inv", request.getParameter("first_inv"));
	}
	
	@RequestMapping(value = "/invoiceCurrencyParam")
	public void invoiceCurrencyParam(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		session.setAttribute("AUD", request.getParameter("AUD"));
		session.setAttribute("CHF", request.getParameter("CHF"));
		session.setAttribute("GBP", request.getParameter("GBP"));
		session.setAttribute("THB", request.getParameter("THB"));
		session.setAttribute("EUR", request.getParameter("EUR"));
		session.setAttribute("USD", request.getParameter("USD"));
		session.setAttribute("SGD", request.getParameter("SGD"));
	}
	
	@RequestMapping(value = "/showInvoiceCompany")
	public ModelAndView showInvoiceCompany(HttpServletRequest request, HttpServletResponse response) {
		
		List<InvoiceCompany> inv_company = null;
		
		inv_company = invoiceDao.showInvoiceCompany();
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", inv_company);
		jobj.put("total", inv_company.size());
		
		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/showInvoiceCustomer")
	public ModelAndView showInvoiceCustomer(HttpServletRequest request, HttpServletResponse response) {
		
		List<Invoice> invLs = null;
		int cus_id = Integer.parseInt(request.getParameter("cus_id"));
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		
		invLs = invoiceDao.showInvoiceCustomer(cus_id, month, year);
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", invLs);
		jobj.put("total", invLs.size());
		
		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/addInvoice")
	public ModelAndView addInvoice (HttpServletRequest request, HttpServletResponse response) {
		
		UserDetailsApp user = UserLoginDetail.getUser();
		int usr_id = user.getUserModel().getUsr_id();
		java.sql.Date inv_delivery_date_sql = null;
		java.sql.Date inv_bill_date_sql = null;
		
		String inv_name = request.getParameter("ainv_name");
		int inv_company_id = Integer.parseInt(request.getParameter("ainv_company_id"));
		String inv_proj_no = request.getParameter("ainv_proj_no");
		String inv_delivery_date = request.getParameter("ainv_delivery_date");
		int inv_payment_terms = Integer.parseInt(request.getParameter("ainv_payment_terms"));
		BigDecimal inv_vat = new BigDecimal(request.getParameter("ainv_vat"));
//		String inv_bill_type = request.getParameter("ainv_bill_type");
		int cus_id = Integer.parseInt(request.getParameter("acus_id"));
		String inv_bill_date = request.getParameter("ainv_bill_date");
		int inv_portal = Integer.parseInt(request.getParameter("ainv_portal"));
		String inv_bill_to = request.getParameter("ainv_bill_to");
		String inv_currency = request.getParameter("ainv_currency");
		BigDecimal inv_discount = new BigDecimal(request.getParameter("ainv_discount"));
		
//		System.out.println("delivery date = "+inv_delivery_date);
		
		Invoice inv = new Invoice();
		inv.setInv_name(inv_name);
		inv.setInv_company_id(inv_company_id);
		inv.setInv_payment_terms(inv_payment_terms);
		inv.setInv_vat(inv_vat);
		inv.setCus_id(cus_id);
		inv.setCretd_usr(usr_id);
		inv.setInv_bill_type("");
		inv.setInv_bill_to(inv_bill_to);
		inv.setInv_currency(inv_currency);
		inv.setInv_discount(inv_discount);
		
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
			Date parsed_delivery_date = dateFormat.parse(inv_delivery_date);
			inv_delivery_date_sql = new java.sql.Date(parsed_delivery_date.getTime());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		try{
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
			Date parsed_bill_date = dateFormat2.parse(inv_bill_date);
			inv_bill_date_sql = new java.sql.Date(parsed_bill_date.getTime());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		inv.setInv_delivery_date_sql(inv_delivery_date_sql);
		inv.setInv_bill_date_sql(inv_bill_date_sql);
//		System.out.println(inv_delivery_date_sql.toString());
		
		if(!inv_proj_no.equals("Project Number")){
			inv.setInv_proj_no(inv_proj_no);
		}else{
			inv.setInv_proj_no("");
		}
		
		int inv_id = invoiceDao.addInvoice(inv);
		
		if(inv_portal == 2){
			Map<String, Float> map = new HashMap<String, Float>();
			map = getCurrencyRate(request, response);
			int job_id = Integer.parseInt(request.getParameter("ainv_job_id"));
			String job_name = request.getParameter("ainv_job_name");
			BigDecimal inv_currency_rate = new BigDecimal(map.get(inv.getInv_currency()));
			List<InvoiceReference> inv_refLs = invoiceDao.getJobItemList(job_id);
			for(int i=0; i<inv_refLs.size(); i++){
				inv_refLs.get(i).setInv_id(inv_id);
				inv_refLs.get(i).setInv_ref_desc(job_name);
				String currency = inv_refLs.get(i).getInv_currency();
				BigDecimal currency_rate = new BigDecimal(map.get(currency));
				if(!inv.getInv_currency().equals(currency)){
					BigDecimal price = inv_refLs.get(i).getInv_ref_price().divide(currency_rate, 2, BigDecimal.ROUND_HALF_UP).multiply(inv_currency_rate);
					price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
					inv_refLs.get(i).setInv_ref_price(price);
				}
//				System.out.println("item name = "+inv_refLs.get(i).getInv_itm_name());
//				System.out.println("proj_ref_id = "+inv_refLs.get(i).getProj_ref_id());
//				System.out.println("price = "+inv_refLs.get(i).getInv_ref_price());
//				System.out.println("qty = "+inv_refLs.get(i).getInv_ref_qty());
//				System.out.println("currency = "+inv_refLs.get(i).getInv_itm_name());
				invoiceDao.addInvoiceReference(inv_refLs.get(i), map);
			}
			jobsDao.billedJobProjects(job_id);
		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value = "/addInvoiceReference")
	public ModelAndView addInvoiceReference(HttpServletRequest request, HttpServletResponse response) {
		
		UserDetailsApp user = UserLoginDetail.getUser();
		int usr_id = user.getUserModel().getUsr_id();
		
		int proj_ref_id = Integer.parseInt(request.getParameter("aproj_ref_id"));
		int inv_id = Integer.parseInt(request.getParameter("ainv_id"));
		String inv_itm_name = request.getParameter("ainv_itm_name");
		BigDecimal inv_ref_price = new BigDecimal(request.getParameter("ainv_ref_price"));
		BigDecimal inv_ref_qty = new BigDecimal(request.getParameter("ainv_ref_qty"));
		String inv_topix_id = request.getParameter("ainv_topix_id");
		String inv_ref_desc = request.getParameter("ainv_ref_desc");
		
		InvoiceReference inv_ref = new InvoiceReference();
		inv_ref.setInv_itm_name(inv_itm_name);
		inv_ref.setInv_id(inv_id);
		inv_ref.setProj_ref_id(proj_ref_id);
		inv_ref.setInv_ref_price(inv_ref_price);
		inv_ref.setInv_ref_qty(inv_ref_qty);
//		inv_ref.setInv_ref_currency(inv_ref_currency);
		inv_ref.setCretd_usr(usr_id);
		inv_ref.setInv_topix_id(inv_topix_id);
		
		if(!inv_ref_desc.equals("Remark")){
			inv_ref.setInv_ref_desc(inv_ref_desc);
		}else{
			inv_ref.setInv_ref_desc("");
		}
		
		Map<String, Float> map = new HashMap<String, Float>();
		map = getCurrencyRate(request, response);
		
		invoiceDao.addInvoiceReference(inv_ref,map);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value = "/addInvoiceReferenceFromJobs")
	public ModelAndView addInvoiceReferenceFromJobs(HttpServletRequest request, HttpServletResponse response) {
	
		Map<String, Float> map = new HashMap<String, Float>();
		map = getCurrencyRate(request, response);
		int job_id = Integer.parseInt(request.getParameter("aejob_id"));
		int inv_id = Integer.parseInt(request.getParameter("aejob_inv_id"));
		String job_name = request.getParameter("aeinv_job_name");
		Invoice inv = invoiceDao.getInvoiceById(inv_id);
		BigDecimal inv_currency_rate = new BigDecimal(map.get(inv.getInv_currency()));
		List<InvoiceReference> inv_refLs = invoiceDao.getJobItemList(job_id);
		for(int i=0; i<inv_refLs.size(); i++){
			inv_refLs.get(i).setInv_id(inv_id);
			inv_refLs.get(i).setInv_ref_desc(job_name);
			String currency = inv_refLs.get(i).getInv_currency();
			BigDecimal currency_rate = new BigDecimal(map.get(currency));
			if(!inv.getInv_currency().equals(currency)){
				BigDecimal price = inv_refLs.get(i).getInv_ref_price().divide(currency_rate, 2, BigDecimal.ROUND_HALF_UP).multiply(inv_currency_rate);
				price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
				inv_refLs.get(i).setInv_ref_price(price);
			}
			invoiceDao.addInvoiceReference(inv_refLs.get(i), map);
		}
		jobsDao.billedJobProjects(job_id);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value = "/updateInvoiceStatus")
	public ModelAndView updateInvoiceStatus(HttpServletRequest request, HttpServletResponse response){
	
		String inv_bill_type = request.getParameter("inv_bill_type");
		int inv_id = Integer.parseInt(request.getParameter("inv_id"));
		
//		invoiceDao.updateInvoiceStatus(inv_id, inv_bill_type);
		
		Invoice inv = invoiceDao.getInvoiceById(inv_id);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed_delivery_date = null;
		try {
			parsed_delivery_date = dateFormat.parse(inv.getInv_delivery_date());
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String delivery_year = sdf.format(parsed_delivery_date);
		InvoiceCompany inv_company = invoiceDao.getInvoiceCompanyById(inv.getInv_company_id());
		
		String inv_number = "";
		if(inv_bill_type.equals("Direct")){
			inv_number = inv_company.getInv_company_code()+delivery_year;
		}else if(inv_bill_type.equals("Credit Note")){
			inv_number = "CN"+inv_company.getInv_company_code()+delivery_year;
		}
		String last_inv_number = "";
		try{
			last_inv_number = invoiceDao.getLastInvoiceNumber(inv.getInv_company_id(),delivery_year,inv_bill_type);
		}catch(Exception e){
			System.out.println("First inv/cn of "+inv_company.getInv_company_name());
		}
		if(last_inv_number == ""){
			inv_number += "00001";
		}else{
			String inv_number_count = last_inv_number.substring(last_inv_number.length()-5);
			String myLast = "";
			for(int x=0; x<inv_number_count.length(); x++){
				String myChar = inv_number_count.substring(x,x+1);
				if(!myChar.equals("0")){
					myLast = inv_number_count.substring(x);
					break;
				}
			}
			int praseNumber = Integer.parseInt(myLast)+1;
			String new_inv_number = Integer.toString(praseNumber);
			int charCount = 5-new_inv_number.length();
			for(int i=0; i<charCount; i++){
				inv_number += "0";
			}
			inv_number += new_inv_number;
		}
		System.out.println("Invoice Number : "+inv_number);
		inv.setInv_number(inv_number);
		
		invoiceDao.updateInvoiceStatus(inv_id, inv_bill_type, inv_number);
		
		List<Invoice> invLs = new ArrayList<Invoice>();
		invLs.add(inv);
		JSONObject jobj = new JSONObject();
		jobj.put("records", invLs);

		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/updateInvoice")
	public ModelAndView updateInvoice(HttpServletRequest request, HttpServletResponse response){
		
		int inv_id = Integer.parseInt(request.getParameter("einv_id"));
		String inv_name = request.getParameter("einv_name");
		String inv_proj_no = request.getParameter("einv_proj_no");
		String inv_delivery_date = request.getParameter("einv_delivery_date");
		int inv_payment_terms = Integer.parseInt(request.getParameter("einv_payment_terms"));
		BigDecimal inv_vat = new BigDecimal(request.getParameter("einv_vat"));
//		String inv_bill_type = request.getParameter("einv_bill_type");
		int cus_id = Integer.parseInt(request.getParameter("ecus_id"));
		String inv_bill_date = request.getParameter("einv_bill_date");
		String inv_bill_to = request.getParameter("einv_bill_to");
		String inv_currency = request.getParameter("einv_currency");
		BigDecimal inv_discount = new BigDecimal(request.getParameter("einv_discount"));
		
		Map<String, Float> map = new HashMap<String, Float>();
		map = getCurrencyRate(request, response);
		
		Invoice inv = new Invoice();
		inv.setInv_id(inv_id);
		inv.setInv_name(inv_name);
		inv.setInv_payment_terms(inv_payment_terms);
		inv.setInv_vat(inv_vat);
		inv.setCus_id(cus_id);
//		inv.setInv_bill_type(inv_bill_type);
		inv.setInv_bill_to(inv_bill_to);
		inv.setInv_currency(inv_currency);
		inv.setInv_discount(inv_discount);
		
		java.sql.Date inv_delivery_date_sql = null;
		java.sql.Date inv_bill_date_sql = null;
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy");
			Date parsed_delivery_date = dateFormat.parse(inv_delivery_date);
			inv_delivery_date_sql = new java.sql.Date(parsed_delivery_date.getTime());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		try{
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yy");
			Date parsed_bill_date = dateFormat2.parse(inv_bill_date);
			inv_bill_date_sql = new java.sql.Date(parsed_bill_date.getTime());
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		inv.setInv_delivery_date_sql(inv_delivery_date_sql);
		inv.setInv_bill_date_sql(inv_bill_date_sql);
		if(!inv_proj_no.equals("Project Number")){
			inv.setInv_proj_no(inv_proj_no);
		}else{
			inv.setInv_proj_no("");
		}
		
		invoiceDao.updateInvoice(inv,map);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value = "/updateInvoiceReference")
	public ModelAndView updateInvoiceReference(HttpServletRequest request, HttpServletResponse response){
		
		int inv_ref_id = Integer.parseInt(request.getParameter("einv_ref_id"));
		int proj_ref_id = Integer.parseInt(request.getParameter("eproj_ref_id"));
		int inv_id = Integer.parseInt(request.getParameter("einv_id_ref"));
		String inv_itm_name = request.getParameter("einv_itm_name");
		BigDecimal inv_ref_price = new BigDecimal(request.getParameter("einv_ref_price"));
		BigDecimal inv_ref_qty = new BigDecimal(request.getParameter("einv_ref_qty"));
		String inv_topix_id = request.getParameter("einv_topix_id");
		String inv_ref_desc = request.getParameter("einv_ref_desc");
		
		Map<String, Float> map = new HashMap<String, Float>();
		map = getCurrencyRate(request, response);
		
		InvoiceReference inv_ref = new InvoiceReference();
		inv_ref.setInv_ref_id(inv_ref_id);
		inv_ref.setInv_itm_name(inv_itm_name);
		inv_ref.setInv_id(inv_id);
		inv_ref.setProj_ref_id(proj_ref_id);
		inv_ref.setInv_ref_price(inv_ref_price);
		inv_ref.setInv_ref_qty(inv_ref_qty);
		inv_ref.setInv_topix_id(inv_topix_id);
		
		if(!inv_ref_desc.equals("Remark")){
			inv_ref.setInv_ref_desc(inv_ref_desc);
		}else{
			inv_ref.setInv_ref_desc("");
		}
		
		invoiceDao.updateInvoicereference(inv_ref,map);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value = "/updateInvoiceReferenceBatch")
	public ModelAndView updateInvoiceReferenceBatch(HttpServletRequest request, HttpServletResponse response){
		
		Object data = request.getParameter("data");
		List<InvoiceReference> invRefLs = invoiceDao.getListDataFromRequest(data);
		
		Map<String, Float> map = new HashMap<String, Float>();
		map = getCurrencyRate(request, response);
		
		invoiceDao.updateInvoiceReferenceBatch(invRefLs,map);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value = "/deleteInvoice")
	public void deleteInvoice(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		try{
			invoiceDao.deleteInvoice(id);
		}catch(Exception e){
			logger.error("Cannot delete inv_id = "+id+"\n"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteInvoiceReference")
	public void deleteInvoiceReference(HttpServletRequest request, HttpServletResponse response){
		Map<String, Float> map = new HashMap<String, Float>();
		map = getCurrencyRate(request, response);
		int id = Integer.parseInt(request.getParameter("id"));
		try{
			invoiceDao.deleteInvoiceReference(id,map);
		}catch(Exception e){
			logger.error("Cannot delete inv_ref_id = "+id+"\n"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "/printInvoice")
	public void printInvoice(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		int id = Integer.parseInt(request.getParameter("inv_id"));
		Invoice inv = invoiceDao.getInvoiceById(id);
		InvoiceCompany inv_company = invoiceDao.getInvoiceCompanyById(inv.getInv_company_id());
		List<InvoiceReference> inv_ref = invoiceDao.searchInvoiceReference(id);
		
		try {
			new PrintInvoice_iText().createPdf(request,response,inv,inv_company,inv_ref);
		} catch (IOException | DocumentException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	@RequestMapping(value = "/invoiceMonthlyReport")
	public ModelAndView printInvoiceMonthlyReport(HttpServletRequest request, HttpServletResponse response) {
	
//		Calendar now = Calendar.getInstance();
//		int year = now.get(Calendar.YEAR);
//		String yearInString = String.valueOf(year);
		String inv_bill_type = "Direct";
		
		List<Invoice> gsd18 = invoiceDao.showInvoiceMonthlyReport("2018", 1, inv_bill_type);
		List<Invoice> jv18 = invoiceDao.showInvoiceMonthlyReport("2018", 2, inv_bill_type);
		List<Invoice> fgs18 = invoiceDao.showInvoiceMonthlyReport("2018", 3, inv_bill_type);
		List<Invoice> mm18 = invoiceDao.showInvoiceMonthlyReport("2018", 4, inv_bill_type);
		List<Invoice> gsdp18 = invoiceDao.showInvoiceMonthlyReport("2018", 5, inv_bill_type);
		List<Invoice> gps18 = invoiceDao.showInvoiceMonthlyReport("2018", 6, inv_bill_type);
		List<Invoice> tta18 = invoiceDao.showInvoiceMonthlyReport("2018", 7, inv_bill_type);
		List<Invoice> stu18 = invoiceDao.showInvoiceMonthlyReport("2018", 8, inv_bill_type);
		List<Invoice> gsda18 = invoiceDao.showInvoiceMonthlyReport("2018", 9, inv_bill_type);
		
		List<Invoice> gsd_angebote18 = invoiceDao.showInvoiceMonthlyReport("2018", 1, "Angebote");
		List<Invoice> tta_angebote18 = invoiceDao.showInvoiceMonthlyReport("2018", 7, "Angebote");
		List<Invoice> gsda_angebote18 = invoiceDao.showInvoiceMonthlyReport("2018", 9, "Angebote");
		
		List<Invoice> gsd19 = invoiceDao.showInvoiceMonthlyReport("2019", 1, inv_bill_type);
		List<Invoice> jv19 = invoiceDao.showInvoiceMonthlyReport("2019", 2, inv_bill_type);
		List<Invoice> fgs19 = invoiceDao.showInvoiceMonthlyReport("2019", 3, inv_bill_type);
		List<Invoice> mm19 = invoiceDao.showInvoiceMonthlyReport("2019", 4, inv_bill_type);
		List<Invoice> gsdp19 = invoiceDao.showInvoiceMonthlyReport("2019", 5, inv_bill_type);
		List<Invoice> gps19 = invoiceDao.showInvoiceMonthlyReport("2019", 6, inv_bill_type);
		List<Invoice> tta19 = invoiceDao.showInvoiceMonthlyReport("2019", 7, inv_bill_type);
		List<Invoice> stu19 = invoiceDao.showInvoiceMonthlyReport("2019", 8, inv_bill_type);
		List<Invoice> gsda19 = invoiceDao.showInvoiceMonthlyReport("2019", 9, inv_bill_type);
		
		List<Invoice> gsd_angebote19 = invoiceDao.showInvoiceMonthlyReport("2019", 1, "Angebote");
		List<Invoice> tta_angebote19 = invoiceDao.showInvoiceMonthlyReport("2019", 7, "Angebote");
		List<Invoice> gsda_angebote19 = invoiceDao.showInvoiceMonthlyReport("2019", 9, "Angebote");
		
		List<Invoice> gsd20 = invoiceDao.showInvoiceMonthlyReport("2020", 1, inv_bill_type);
		List<Invoice> jv20 = invoiceDao.showInvoiceMonthlyReport("2020", 2, inv_bill_type);
		List<Invoice> fgs20 = invoiceDao.showInvoiceMonthlyReport("2020", 3, inv_bill_type);
		List<Invoice> mm20 = invoiceDao.showInvoiceMonthlyReport("2020", 4, inv_bill_type);
		List<Invoice> gsdp20 = invoiceDao.showInvoiceMonthlyReport("2020", 5, inv_bill_type);
		List<Invoice> gps20 = invoiceDao.showInvoiceMonthlyReport("2020", 6, inv_bill_type);
		List<Invoice> tta20 = invoiceDao.showInvoiceMonthlyReport("2020", 7, inv_bill_type);
		List<Invoice> stu20 = invoiceDao.showInvoiceMonthlyReport("2020", 8, inv_bill_type);
		List<Invoice> gsda20 = invoiceDao.showInvoiceMonthlyReport("2020", 9, inv_bill_type);
		
		List<Invoice> gsd_angebote20 = invoiceDao.showInvoiceMonthlyReport("2020", 1, "Angebote");
		List<Invoice> tta_angebote20 = invoiceDao.showInvoiceMonthlyReport("2020", 7, "Angebote");
		List<Invoice> gsda_angebote20 = invoiceDao.showInvoiceMonthlyReport("2020", 9, "Angebote");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gsd18", gsd18);
		map.put("jv18", jv18);
		map.put("fgs18", fgs18);
		map.put("mm18", mm18);
		map.put("gsdp18", gsdp18);
		map.put("gps18", gps18);
		map.put("tta18", tta18);
		map.put("stu18", stu18);
		map.put("gsda18", gsda18);
		map.put("gsd_angebote18", gsd_angebote18);
		map.put("tta_angebote18", tta_angebote18);
		map.put("gsda_angebote18", gsda_angebote18);
		map.put("gsd19", gsd19);
		map.put("jv19", jv19);
		map.put("fgs19", fgs19);
		map.put("mm19", mm19);
		map.put("gsdp19", gsdp19);
		map.put("gps19", gps19);
		map.put("tta19", tta19);
		map.put("stu19", stu19);
		map.put("gsda19", gsda19);
		map.put("gsd_angebote19", gsd_angebote19);
		map.put("tta_angebote19", tta_angebote19);
		map.put("gsda_angebote19", gsda_angebote19);
		map.put("gsd20", gsd20);
		map.put("jv20", jv20);
		map.put("fgs20", fgs20);
		map.put("mm20", mm20);
		map.put("gsdp20", gsdp20);
		map.put("gps20", gps20);
		map.put("tta20", tta20);
		map.put("stu20", stu20);
		map.put("gsda20", gsda20);
		map.put("gsd_angebote20", gsd_angebote20);
		map.put("tta_angebote20", tta_angebote20);
		map.put("gsda_angebote20", gsda_angebote20);
		
		return new ModelAndView("invoice_monthly-print", map);
	}
	
	@RequestMapping(value = "/invoiceReport")
	public ModelAndView printInvoiceReport(HttpServletRequest request, HttpServletResponse response) {
	
		HttpSession session = request.getSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("inv_proj_no", (String)session.getAttribute("inv_proj_no"));
		map.put("inv_company_id", (String)session.getAttribute("inv_company_id"));
		map.put("delivery_start", (String)session.getAttribute("delivery_start"));
		map.put("delivery_limit", (String)session.getAttribute("delivery_limit"));
		map.put("inv_cus_id", (String)session.getAttribute("inv_cus_id"));
		map.put("inv_bill_type", (String)session.getAttribute("inv_bill_type"));
		
		if(map.get("delivery_start")!=null && !map.get("delivery_start").isEmpty()){
			String delivery_start = map.get("delivery_start");
			String[] parts = delivery_start.split("/");
			String month = parts[0];
			String year = parts[1];
			String myDate = "20"+year+"-"+month+"-01";
			map.put("delivery_start", myDate);
		}
		
		if(map.get("delivery_limit")!=null && !map.get("delivery_limit").isEmpty()){
			String delivery_limit = map.get("delivery_limit");
			String[] parts = delivery_limit.split("/");
			String month = parts[0];
			String year = parts[1];
			String myDate = "20"+year+"-"+month+"-01";
			map.put("delivery_limit", myDate);
		}
		
		List<Invoice> inv = invoiceDao.showInvoiceReport(map);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("list", inv);
		
		return new ModelAndView("invoice-print", map2);
	}
	
	@RequestMapping(value = "/topixSoap")
	public ModelAndView topixSoapConnection(HttpServletRequest request, HttpServletResponse response){
		
		// Test Server = 0 && Main Server = 1
		TopixConfig tpx_cfg = invoiceDao.getTopixConfig();
		
		int inv_id = Integer.parseInt(request.getParameter("id"));
		Invoice inv = invoiceDao.getInvoiceById(inv_id);
		List<InvoiceReference> inv_refLs = invoiceDao.searchInvoiceReference(inv_id);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date tpx_date_ts = null;
		try {
			Date parse_tpx_date = dateFormat.parse(inv.getInv_bill_date());
			tpx_date_ts = new java.sql.Date(parse_tpx_date.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.error(e1.getMessage());
		}
		Topix tpx = new Topix();
		tpx.setInv_id(inv.getInv_id());
		tpx.setTpx_cfg_id(tpx_cfg.getTpx_cfg_id());
		tpx.setTpx_name(inv.getInv_name());
		tpx.setTpx_cus_id(inv.getTopix_cus_id());
//		tpx.setTpx_res_nr("");
//		tpx.setTpx_res_msg("");
//		tpx.setTpx_inv_number("");
		tpx.setTpx_date(inv.getInv_bill_date());
		tpx.setInv_delivery_date(inv.getInv_delivery_date());
		tpx.setTpx_date_sql(tpx_date_ts);
		
		try {
	        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapConnection = soapConnectionFactory.createConnection();
	
	        // Send SOAP Message to SOAP Server
//	      	String url = "http://192.168.16.125:9090/4DSOAP";
	        String url = tpx_cfg.getTpx_cfg_ip();
	        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(tpx_cfg, tpx, inv_refLs), url);
	
	        // print SOAP Response
	        System.out.println("\nResponse SOAP Message:");
	        soapResponse.writeTo(System.out);
	        String err_num = soapResponse.getSOAPBody().getElementsByTagName("ERRORNUM").item(0).getFirstChild().getTextContent();
	        String err_text = soapResponse.getSOAPBody().getElementsByTagName("ERRORTEXT").item(0).getFirstChild().getTextContent();
	        
	        err_num = replaceLine(err_num);
	        err_num = err_num.trim();
	        err_text = replaceLine(err_text);
	        String inv_number = "";
	        try{
	        inv_number = soapResponse.getSOAPBody().getElementsByTagName("AUFTR_NR_AUSGABE").item(0).getFirstChild().getTextContent();
	        inv_number = replaceLine(inv_number);
	        inv_number = inv_number.trim();
	        } catch (Exception e) {
	        	inv_number = "";
	        }
	        System.out.println("\n----------------");
	        System.out.println(err_num);
	        System.out.println(err_text);
	        System.out.println(inv_number);
	        System.out.println("----------------");
	        
	        tpx.setTpx_res_nr(err_num);
	        tpx.setTpx_res_msg(err_text);
	        tpx.setTpx_inv_number(inv_number);
	        
	        if(err_num.equals("0")){
	        
	        int tpx_id = invoiceDao.addTopix(tpx);

			for(int i=0; i<inv_refLs.size(); i++){
				TopixReference tpx_ref = new TopixReference();
				tpx_ref.setTpx_id(tpx_id);
				tpx_ref.setTpx_article_id(inv_refLs.get(i).getInv_topix_id());
				tpx_ref.setTpx_ref_qty(inv_refLs.get(i).getInv_ref_qty());
				invoiceDao.addTopixReference(tpx_ref);
			}
			
	        }else{
	        	String status = "Angebote Error("+err_num+"): "+err_text;
	        	invoiceDao.updateInvoiceStatus(inv_id, status, inv_number);
	        	invoiceDao.updateTopixAuditLogging(tpx);
	        }
	        
			soapConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
			tpx.setTpx_res_nr("404");
			tpx.setTpx_res_msg("No Response From Topix Server");
			tpx.setTpx_inv_number("Please try again later");
			invoiceDao.updateTopixAuditLogging(tpx);
		}
		
		tpx.setTpx_date_sql(null);
		List<Topix> tpxLs = new ArrayList<Topix>();
		tpxLs.add(tpx);
		JSONObject jobj = new JSONObject();
		jobj.put("records", tpxLs);

		return new ModelAndView("jsonView", jobj);
		
	}
	
	public String replaceLine(String s){
		s = s.replace("\u2028", "");
        s = s.replace("\u2029", "");
        s = s.replace("\n", "");
		return s;
	}
	
	private static SOAPMessage createSOAPRequest(TopixConfig tpx_cfg, Topix tpx, List<InvoiceReference> inv_refLs) throws Exception {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        soapMessage.setProperty(SOAPMessage.WRITE_XML_DECLARATION, "true");
        soapMessage.setProperty(SOAPMessage.CHARACTER_SET_ENCODING, "UTF-8");
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.removeNamespaceDeclaration(envelope.getPrefix());
//        envelope.addNamespaceDeclaration("soap","http://schemas.xmlsoap.org/soap/envelope/");
        envelope.setPrefix("soapenv");              
        envelope.addNamespaceDeclaration("xsi","http://www.w3.org/2001/XMLSchema-instance");
        envelope.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema"); 
        envelope.addNamespaceDeclaration("soapenv","http://schemas.xmlsoap.org/soap/envelope/");
        envelope.addNamespaceDeclaration("def","http://www.4d.com/namespace/default");
        envelope.addNamespaceDeclaration("soapenc","http://schemas.xmlsoap.org/soap/encoding/");
        SOAPHeader header=soapMessage.getSOAPHeader();                      
        header.setPrefix("soapenv");       
        SOAPBody soapBody = envelope.getBody();
        soapBody.setPrefix("soapenv");
        SOAPElement root = soapBody.addChildElement("def:SOAP_SetAuftrag");
        root.setAttribute("soapenv:encodingStyle", "http://schemas.xmlsoap.org/soap/encoding/");
        SOAPElement mandant = root.addChildElement("MANDANT");
        mandant.setAttribute("xsi:type", "xsd:string");
//        mandant.setValue("nos01");
        mandant.setValue(tpx_cfg.getTpx_cfg_man());
        SOAPElement benutzername = root.addChildElement("BENUTZERNAME");
        benutzername.setAttribute("xsi:type", "xsd:string");
//        benutzername.setValue("Administrator");
        benutzername.setValue(tpx_cfg.getTpx_cfg_usr());
        SOAPElement kennwort = root.addChildElement("KENNWORT");
        kennwort.setAttribute("xsi:type", "xsd:string");
//        kennwort.setValue("nosAdmin");
        kennwort.setValue(tpx_cfg.getTpx_cfg_pw());
        SOAPElement auftr_art = root.addChildElement("AUFTR_ART");
        auftr_art.setAttribute("xsi:type", "xsd:string");
//        auftr_art.setValue("A");
        auftr_art.setValue(tpx_cfg.getTpx_cfg_art());
        SOAPElement auftr_datum = root.addChildElement("AUFTR_DATUM");
        auftr_datum.setAttribute("xsi:type", "xsd:date");
//        auftr_datum.setValue("2018-03-13");
        auftr_datum.setValue(tpx.getTpx_date());
        SOAPElement auftr_liefertermin = root.addChildElement("AUFTR_LIEFERTERMIN");
        auftr_liefertermin.setAttribute("xsi:type", "xsd:date");
//        String month = tpx.getTpx_date().substring(0, 7);
        auftr_liefertermin.setValue(tpx.getInv_delivery_date());
        SOAPElement auftr_text = root.addChildElement("AUFTR_TEXT");
        auftr_text.setAttribute("xsi:type", "xsd:string");
//        auftr_text.setValue("Testing2");
        auftr_text.setValue(tpx.getTpx_name());
        SOAPElement auftr_betrag = root.addChildElement("AUFTR_BETRAG");
        auftr_betrag.setAttribute("xsi:type", "xsd:float");
        auftr_betrag.setValue("0");
        SOAPElement steuer1 = root.addChildElement("STEUER1");
        steuer1.setAttribute("xsi:type", "xsd:float");
        steuer1.setValue("0");
        SOAPElement positionsdaten = root.addChildElement("POSITIONSDATEN");
        positionsdaten.setAttribute("xsi:type", "xsd:string");
        positionsdaten.setValue("POSDEF\tARTIKEL\tPOS_BETRAG\tPOS_MENGE");
        SOAPElement at_wiederkehrend = root.addChildElement("AT_WIEDERKEHREND");
        at_wiederkehrend.setAttribute("xsi:type", "xsd:boolean");
        at_wiederkehrend.setValue("False");
        SOAPElement preise_aus_artikelstamm = root.addChildElement("PREISE_AUS_ARTIKELSTAMM");
        preise_aus_artikelstamm.setAttribute("xsi:type", "xsd:boolean");
        preise_aus_artikelstamm.setValue("True");
        SOAPElement positionsdaten_array = root.addChildElement("POSITIONSDATEN_ARRAY");
        positionsdaten_array.setAttribute("xsi:arrayType", "xsd:string[2]");
//	    positionsdaten_array.addChildElement("item0").setValue("POS\t07-0241\t0\t12");
//	    positionsdaten_array.addChildElement("item1").setValue("POS\t07-0241\t0\t20");
        for(int i=0; i<inv_refLs.size(); i++){
        	positionsdaten_array.addChildElement("item"+i).setValue("POS\t"+inv_refLs.get(i).getInv_topix_id()+"\t0\t"+inv_refLs.get(i).getInv_ref_qty().toString());
        }
        SOAPElement kdlf_nr_als_id = root.addChildElement("KDLF_NR_ALS_ID");
        kdlf_nr_als_id.setAttribute("xsi:type", "xsd:string");
//        kdlf_nr_als_id.setValue("med0000081");
        kdlf_nr_als_id.setValue(tpx.getTpx_cus_id());
        
        soapMessage.saveChanges();

        System.out.println("Request SOAP Message:");
        soapMessage.writeTo(System.out);


        return soapMessage;
    }
	
	public Map<String, Float> getCurrencyRate(HttpServletRequest request, HttpServletResponse response){
		
		HttpSession session = request.getSession();
		Map<String, Float> map = new HashMap<String, Float>();
		map.put("AUD", Float.parseFloat((String)session.getAttribute("AUD")));
		map.put("CHF", Float.parseFloat((String)session.getAttribute("CHF")));
		map.put("GBP", Float.parseFloat((String)session.getAttribute("GBP")));
		map.put("THB", Float.parseFloat((String)session.getAttribute("THB")));
		map.put("USD", Float.parseFloat((String)session.getAttribute("USD")));
		map.put("EUR", Float.parseFloat((String)session.getAttribute("EUR")));
		map.put("SGD", Float.parseFloat((String)session.getAttribute("SGD")));
		
		return map;
	}
	
}
