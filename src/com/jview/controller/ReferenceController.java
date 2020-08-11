package com.jview.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jview.dao.ReferenceDao;
import com.jview.model.Reference;

import common.Logger;
import net.sf.json.JSONObject;

@Controller
public class ReferenceController {

	private ApplicationContext context;
	private ReferenceDao referenceDao;
	private static final Logger logger = Logger.getLogger(ReferenceController.class);
	
	public ReferenceController() {
		this.context = new ClassPathXmlApplicationContext("META-INF/jview-context.xml");
		this.referenceDao = (ReferenceDao) this.context.getBean("ReferenceDao");
	}
	
	@RequestMapping(value = "/showDBReference")
	public ModelAndView showDBReference(HttpServletRequest request, HttpServletResponse response) {

		List<Reference> dbRef = null;
		String db_ref_kind = request.getParameter("kind");
		String db_ref_dept = request.getParameter("dept");
		
		try{
			dbRef = referenceDao.showDBReference(db_ref_kind, db_ref_dept);
		} catch(Exception e){
			logger.error(e.getMessage());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", dbRef);
		jobj.put("total", dbRef.size());
		
		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/showDepartment")
	public ModelAndView showDepartmentReference(HttpServletRequest request, HttpServletResponse response) {
		
		List<Reference> refLs = null;
		int level = 0;
		try{
			level = Integer.parseInt(request.getParameter("level"));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		try{
			refLs = referenceDao.showDepartmentReference(level);
		} catch (Exception e){
			logger.error(e.getMessage());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", refLs);
		jobj.put("total", refLs.size());
		
		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/showJobStatus")
	public ModelAndView showJobStatus(HttpServletRequest request, HttpServletResponse response) {
		
		List<Reference> refLs = null;
		
		try{
			refLs = referenceDao.showJobStatus();
		} catch (Exception e){
			logger.error(e.getMessage());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", refLs);
		jobj.put("total", refLs.size());
		
		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/showJobReference")
	public ModelAndView showJobReference(HttpServletRequest request, HttpServletResponse response) {
		
		List<Reference> refLs = null;
		String db_ref_kind = request.getParameter("kind");
		String db_ref_dept = request.getParameter("dept");
		
		try{
			refLs = referenceDao.showJobReference(db_ref_kind,db_ref_dept);
		} catch (Exception e){
			logger.error(e.getMessage());
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", refLs);
		jobj.put("total", refLs.size());
		
		return new ModelAndView("jsonView", jobj);
	}
	
}
