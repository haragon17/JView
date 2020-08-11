package com.jview.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jview.dao.ProjectsDao;
import com.jview.model.FileModel;
import com.jview.model.FileUploadBean;
import com.jview.model.Projects;
import com.jview.model.ProjectsReference;
import com.jview.security.UserDetailsApp;
import com.jview.security.UserLoginDetail;

import net.sf.json.JSONObject;

@Controller
public class ProjectsController {

	private ApplicationContext context;
	private ProjectsDao projectsDao;
//	private String proj_name, itm_id, cus_id, timeStart, timeLimit, priceStart, priceLimit, key_acc_id, updateStart, updateLimit;
//	private String AUD, CHF, GBP, THB, EUR;

	private static final Logger logger = Logger.getLogger(ProjectsController.class);
	
	public ProjectsController() {
		this.context = new ClassPathXmlApplicationContext("META-INF/jview-context.xml");
		this.projectsDao = (ProjectsDao) this.context.getBean("ProjectsDao");
	}

	@RequestMapping(value = "/projects")
	public ModelAndView viewProjects(HttpServletRequest request, HttpServletResponse response) {

		UserDetailsApp user = UserLoginDetail.getUser();
		UserController uc = new UserController();
		int type = user.getUserModel().getUsr_type();
		
		if(request.getParameter("l7d") != null){
			uc.setChk(1);
		}
		
//		proj_name = "";
//		itm_id = "";
//		cus_id = "";
//		timeStart = "";
//		timeLimit = "";
//		priceStart = "";
//		priceLimit = "";
//		key_acc_id = "";
//		updateStart = "";
//		updateLimit = "";
//		AUD = "";
//		CHF = "";
//		GBP = "";
//		THB = "";
//		EUR = "";
		
		HttpSession session = request.getSession();
		session.setAttribute("proj_name", "");
		session.setAttribute("itm_id", "");
		session.setAttribute("cus_id", "");
		session.setAttribute("timeStart", "");
		session.setAttribute("timeLimit", "");
		session.setAttribute("priceStart", "");
		session.setAttribute("priceLimit", "");
		session.setAttribute("key_acc_id", "");
		session.setAttribute("updateStart", "");
		session.setAttribute("updateLimit", "");
//		session.setAttribute("AUD", "");
//		session.setAttribute("CHF", "");
//		session.setAttribute("GBP", "");
//		session.setAttribute("THB", "");
//		session.setAttribute("EUR", "");
		
		if(type == 0 || type == 1){
			return new ModelAndView("ProjectsAdmin");
		}else if(type == 3){
//			return new ModelAndView("redirect:jobs.htm");
			return new ModelAndView("AccessDenied");
		}else{
			return new ModelAndView("Projects");
		}
	}

	@RequestMapping(value = "/chkProjName")
	public ModelAndView chkProjName(@RequestParam("records") String name, HttpServletRequest request,
			HttpServletResponse response){
		
		List<Projects> projLs = new ArrayList<Projects>();
		Projects projNull = new Projects();
		
		try{
			projLs.add(projectsDao.findByProjectName(name));
		} catch (Exception e){
			projLs.add(projNull);
		}
		
		JSONObject jobj = new JSONObject();
		jobj.put("records", projLs);

		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/searchProjectsParam")
	public void searchProjectsParam(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.setAttribute("proj_name", request.getParameter("sproj_name"));
		session.setAttribute("itm_id", request.getParameter("sitm_id"));
		session.setAttribute("cus_id", request.getParameter("cus_id"));
		session.setAttribute("timeStart", request.getParameter("time_start"));
		session.setAttribute("timeLimit", request.getParameter("time_limit"));
		session.setAttribute("priceStart", request.getParameter("price_start"));
		session.setAttribute("priceLimit", request.getParameter("price_limit"));
		session.setAttribute("key_acc_id", request.getParameter("skey_acc_mng"));
		session.setAttribute("updateStart", request.getParameter("update_start"));
		session.setAttribute("updateLimit", request.getParameter("update_limit"));
		session.setAttribute("AUD", request.getParameter("AUD"));
		session.setAttribute("CHF", request.getParameter("CHF"));
		session.setAttribute("GBP", request.getParameter("GBP"));
		session.setAttribute("THB", request.getParameter("THB"));
		session.setAttribute("USD", request.getParameter("USD"));
		session.setAttribute("SGD", request.getParameter("SGD"));
		session.setAttribute("EUR", request.getParameter("EUR"));
//		proj_name = request.getParameter("sproj_name");
//		itm_id = request.getParameter("sitm_id");
//		cus_id = request.getParameter("cus_id");
//		timeStart = request.getParameter("time_start");
//		timeLimit = request.getParameter("time_limit");
//		priceStart = request.getParameter("price_start");
//		priceLimit = request.getParameter("price_limit");
//		key_acc_id = request.getParameter("skey_acc_mng");
//		updateStart = request.getParameter("update_start");
//		updateLimit = request.getParameter("update_limit");
//		AUD = request.getParameter("AUD");
//		CHF = request.getParameter("CHF");
//		GBP = request.getParameter("GBP");
//		THB = request.getParameter("THB");
//		EUR = request.getParameter("EUR");
	}

	@RequestMapping(value = "/searchProjectsReference")
	public ModelAndView searchProjectsReference(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		List<ProjectsReference> projRef = null;
		Map<String, String> map = new HashMap<String, String>();

//		map.put("proj_name", proj_name);
//		map.put("itm_id", itm_id);
//		map.put("cus_id", cus_id);
//		map.put("timeStart", timeStart);
//		map.put("timeLimit", timeLimit);
//		map.put("priceStart", priceStart);
//		map.put("priceLimit", priceLimit);
//		map.put("key_acc_id", key_acc_id);
//		map.put("updateStart", updateStart);
//		map.put("updateLimit", updateLimit);
//		map.put("AUD", AUD);
//		map.put("CHF", CHF);
//		map.put("GBP", GBP);
//		map.put("THB", THB);
//		map.put("EUR", EUR);
		
		map.put("proj_name", (String)session.getAttribute("proj_name"));
		map.put("itm_id", (String)session.getAttribute("itm_id"));
		map.put("cus_id", (String)session.getAttribute("cus_id"));
		map.put("timeStart", (String)session.getAttribute("timeStart"));
		map.put("timeLimit", (String)session.getAttribute("timeLimit"));
		map.put("priceStart", (String)session.getAttribute("priceStart"));
		map.put("priceLimit", (String)session.getAttribute("priceLimit"));
		map.put("key_acc_id", (String)session.getAttribute("key_acc_id"));
		map.put("updateStart", (String)session.getAttribute("updateStart"));
		map.put("updateLimit", (String)session.getAttribute("updateLimit"));
		map.put("AUD", (String)session.getAttribute("AUD"));
		map.put("CHF", (String)session.getAttribute("CHF"));
		map.put("GBP", (String)session.getAttribute("GBP"));
		map.put("THB", (String)session.getAttribute("THB"));
		map.put("USD", (String)session.getAttribute("USD"));
		map.put("SGD", (String)session.getAttribute("SGD"));
		map.put("EUR", (String)session.getAttribute("EUR"));
		
		try {
			projRef = projectsDao.searchProjectsReferences(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		JSONObject jobj = new JSONObject();
		jobj.put("records", projRef);
		jobj.put("total", projRef.size());

		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/searchProjects")
	public ModelAndView searchProjects(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		List<Projects> proj = null;
		List<Projects> projLs = new ArrayList<Projects>();
		Map<String, String> map = new HashMap<String, String>();

//		map.put("proj_name", proj_name);
//		map.put("itm_id", itm_id);
//		map.put("cus_id", cus_id);
//		map.put("timeStart", timeStart);
//		map.put("timeLimit", timeLimit);
//		map.put("priceStart", priceStart);
//		map.put("priceLimit", priceLimit);
//		map.put("key_acc_id", key_acc_id);
//		map.put("updateStart", updateStart);
//		map.put("updateLimit", updateLimit);
//		map.put("AUD", AUD);
//		map.put("CHF", CHF);
//		map.put("GBP", GBP);
//		map.put("THB", THB);
//		map.put("EUR", EUR);

		map.put("proj_name", (String)session.getAttribute("proj_name"));
		map.put("itm_id", (String)session.getAttribute("itm_id"));
		map.put("cus_id", (String)session.getAttribute("cus_id"));
		map.put("timeStart", (String)session.getAttribute("timeStart"));
		map.put("timeLimit", (String)session.getAttribute("timeLimit"));
		map.put("priceStart", (String)session.getAttribute("priceStart"));
		map.put("priceLimit", (String)session.getAttribute("priceLimit"));
		map.put("key_acc_id", (String)session.getAttribute("key_acc_id"));
		map.put("updateStart", (String)session.getAttribute("updateStart"));
		map.put("updateLimit", (String)session.getAttribute("updateLimit"));
		map.put("AUD", (String)session.getAttribute("AUD"));
		map.put("CHF", (String)session.getAttribute("CHF"));
		map.put("GBP", (String)session.getAttribute("GBP"));
		map.put("THB", (String)session.getAttribute("THB"));
		map.put("USD", (String)session.getAttribute("USD"));
		map.put("SGD", (String)session.getAttribute("SGD"));
		map.put("EUR", (String)session.getAttribute("EUR"));
		
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));

		try {
			proj = projectsDao.searchProjects(map);

			if (limit + start > proj.size()) {
				limit = proj.size();
			} else {
				limit += start;
			}
			for (int i = start; i < (limit); i++) {
				projLs.add(proj.get(i));
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		JSONObject jobj = new JSONObject();
		jobj.put("records", projLs);
		jobj.put("total", proj.size());

		return new ModelAndView("jsonView", jobj);
	}

	@RequestMapping(value = "/showProjects")
	public ModelAndView showProjects(@RequestParam("id") int id, @RequestParam("type") String type, HttpServletRequest request, HttpServletResponse response) {

		List<Projects> proj = null;

		int cus_id = id;
		
		try {
			proj = projectsDao.showProjects(cus_id,type);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		JSONObject jobj = new JSONObject();
		jobj.put("records", proj);
		jobj.put("total", proj.size());

		return new ModelAndView("jsonView", jobj);
	}
	
	@RequestMapping(value = "/showProjectsReference")
	public ModelAndView showProjectReference(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response) {

		List<ProjectsReference> projRef = null;

		int proj_id = id;
		
		try {
			projRef = projectsDao.showProjectsReference(proj_id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		JSONObject jobj = new JSONObject();
		jobj.put("records", projRef);
		jobj.put("total", projRef.size());

		return new ModelAndView("jsonView", jobj);
	}

//	@RequestMapping(value = "/createProjectsReference")
//	public ModelAndView createProjectsReference(HttpServletRequest request, HttpServletResponse response,
//			FileUploadBean uploadItem, BindingResult result) {
//
//		UserDetailsApp user = UserLoginDetail.getUser();
//
//		String proj_name = request.getParameter("cproj_name");
//		int itm_id = Integer.parseInt(request.getParameter("citm_id"));
//		int cus_id = Integer.parseInt(request.getParameter("ccus_id"));
//		String file = request.getParameter("file");
//		String time = request.getParameter("ctime");
//		String price = request.getParameter("cprice");
//		String currency = request.getParameter("ccurrency");
//		String proj_ref_desc = request.getParameter("cproj_ref_desc");
//
//		Projects proj = new Projects();
//		FileModel fileModel = new FileModel();
//
//		proj.setProj_ref_id(projectsDao.getLastProjectId());
//		proj.setProj_name(proj_name);
//		proj.setItm_id(itm_id);
//		proj.setCus_id(cus_id);
//
//		if (!time.equals("Time in minutes")) {
//			proj.setTime(Integer.parseInt(time));
//		} else {
//			proj.setTime(0);
//		}
//		if (!price.equals("Project Price")) {
//			proj.setPrice(Float.parseFloat(price));
//		} else {
//			proj.setPrice(0);
//		}
//		if (!currency.equals("Price Currency")) {
//			proj.setCurrency(currency);
//		} else {
//			proj.setCurrency("");
//		}
//		if (!proj_ref_desc.equals("Project Details")) {
//			proj.setProj_ref_desc(proj_ref_desc);
//		} else {
//			proj.setProj_ref_desc("");
//		}
//
//		proj.setCretd_usr(user.getUserModel().getUsr_id());
//
//		System.out.println("name = " + proj.getProj_name());
//		System.out.println("cus_id = " + proj.getCus_id());
//		System.out.println("itm_id = " + proj.getItm_id());
//		System.out.println("time = " + proj.getTime());
//		System.out.println("price = " + proj.getPrice());
//		System.out.println("currency = " + proj.getCurrency());
//		System.out.println("detail = " + proj.getProj_ref_desc());
//
//		OutputStream outputStream = null;
//
//		// MultipartFile fileUpload = uploadItem.getFile();
//
//		if (result.hasErrors()) {
//			System.out.println("create projects error");
//			return new ModelAndView("projects");
//		} else {
//			if (uploadItem.getFile() != null && uploadItem.getFile().getSize() != 0) {
//				System.out.println("---------------------------------");
//				System.out.println("file name = " + uploadItem.getFile().getOriginalFilename());
//				System.out.println("file type = " + uploadItem.getFile().getContentType());
//				System.out.println("file size = " + uploadItem.getFile().getSize());
//				try {
//					// File createMain = new File("C:/files");
//					File createMain = new File("/Users/gsd/files");
//					if (!createMain.exists()) {
//						createMain.mkdir();
//					}
//					   String fileName = uploadItem.getFile().getOriginalFilename();  
//					   String filePath = "/Users/gsd/files/" + proj.getProj_ref_id();
//					   File newFile = new File(filePath);
//					   if(!newFile.exists()){
//						   newFile.mkdir();
//					   }
//					   
//					   File newFile2 = new File(filePath +"/"+ fileName);
//
//					outputStream = new FileOutputStream(newFile2);
//					outputStream.write(uploadItem.getFile().getFileItem().get());
//					outputStream.close();
//
//					fileModel.setFile_id(projectsDao.getLastFileId());
//					fileModel.setFile_path(filePath);
//					fileModel.setFile_name(uploadItem.getFile().getOriginalFilename());
//					fileModel.setFile_type(uploadItem.getFile().getContentType());
//					fileModel.setFile_size(uploadItem.getFile().getSize());
//					fileModel.setCretd_usr(user.getUserModel().getUsr_id());
//
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				projectsDao.createFile(fileModel);
//				proj.setFile_id(fileModel.getFile_id());
//				projectsDao.createProjectsReference(proj);
//			} else {
//				proj.setFile_id(0);
//				projectsDao.createProjectsReference(proj);
//
//			}
//			return new ModelAndView("redirect:projects.htm");
//		}
//	}
	
	@RequestMapping(value = "/createProjects")
	public ModelAndView createProjects(HttpServletRequest request, HttpServletResponse response,
			FileUploadBean uploadItem, BindingResult result) {

		UserDetailsApp user = UserLoginDetail.getUser();

		String proj_name = request.getParameter("cproj_name");
		String itm_id = request.getParameter("citm_id");
		int cus_id = Integer.parseInt(request.getParameter("ccus_id"));
		String file = request.getParameter("file");
		String time = request.getParameter("ctime");
		String actual_time = request.getParameter("cactual_time");
		String price = request.getParameter("cprice");
//		String currency = request.getParameter("ccurrency");
		String proj_ref_desc = request.getParameter("cproj_ref_desc");
		String proj_desc = request.getParameter("cproj_desc");
		String proj_currency = request.getParameter("cproj_currency");
		String topix_article_id = request.getParameter("ctopix_article_id");
		int activated = Integer.parseInt(request.getParameter("cproj_ref_activated"));

		ProjectsReference projRef = new ProjectsReference();
		Projects proj = new Projects();
		FileModel fileModel = new FileModel();
		
		proj.setProj_id(projectsDao.getLastProjectId());
		proj.setProj_name(proj_name);
		proj.setCus_id(cus_id);
		proj.setCretd_usr(user.getUserModel().getUsr_id());
		proj.setProj_currency(proj_currency);

//		if(!proj_currency.equals("Price Currency")){
//			proj.setProj_currency(proj_currency);
//		}else{
//			proj.setProj_currency("");
//		}
		
		if (!proj_desc.equals("Project Details")) {
			proj_desc = proj_desc.replace("\u2028", "\n");
			proj_desc = proj_desc.replace("\u2029", "\n");
			proj.setProj_desc(proj_desc);
		} else {
			proj.setProj_desc("");
		}
		
		System.out.println("proj_id = " + proj.getProj_id());
		System.out.println("name = " + proj.getProj_name());
		System.out.println("cus_id = " + proj.getCus_id());
		System.out.println("cretd_usr = " + proj.getCretd_usr());
		System.out.println("detail = " + proj.getProj_desc());
		
		OutputStream outputStream = null;

		// MultipartFile fileUpload = uploadItem.getFile();

		if (result.hasErrors()) {
			System.out.println("create projects error");
			return new ModelAndView("projects");
		} else {
			if (uploadItem.getFile() != null && uploadItem.getFile().getSize() != 0) {
				System.out.println("---------------------------------");
				System.out.println("file name = " + uploadItem.getFile().getOriginalFilename());
				System.out.println("file type = " + uploadItem.getFile().getContentType());
				System.out.println("file size = " + uploadItem.getFile().getSize());
				try {
//					File createMain = new File("/Users/gsd/files");
//					File createMain = new File("/mnt/eSTUDIO/_COMMON/.jview/files");
					File createMain = new File("/jview_storage/files");
					if (!createMain.exists()) {
						createMain.mkdir();
					}
					   String fileName = uploadItem.getFile().getOriginalFilename();  
//					   String filePath = "/Users/gsd/files/" + proj.getProj_id();
//					   String filePath = "/mnt/eSTUDIO/_COMMON/.jview/files/" + proj.getProj_id();
					   String filePath = "/jview_storage/files/" + proj.getProj_id();
					   File newFile = new File(filePath);
					   if(!newFile.exists()){
						   newFile.mkdir();
					   }
					   
					   File newFile2 = new File(filePath +"/"+ fileName);

					outputStream = new FileOutputStream(newFile2);
					outputStream.write(uploadItem.getFile().getFileItem().get());
					outputStream.close();

					fileModel.setFile_id(projectsDao.getLastFileId());
					fileModel.setFile_path(filePath);
					fileModel.setFile_name(uploadItem.getFile().getOriginalFilename());
					fileModel.setFile_type(uploadItem.getFile().getContentType());
					fileModel.setFile_size(uploadItem.getFile().getSize());
					fileModel.setCretd_usr(user.getUserModel().getUsr_id());

				} catch (IOException e) {
					logger.error(e.getMessage());
				}

				projectsDao.createFile(fileModel);
				proj.setFile_id(fileModel.getFile_id());
				projectsDao.createProjects(proj);
			} else {
				proj.setFile_id(0);
				projectsDao.createProjects(proj);

			}
			
			if(!itm_id.equals("Item Name")){
				projRef.setProj_ref_id(projectsDao.getLastProjectRefId());
				projRef.setItm_id(Integer.parseInt(itm_id));
				projRef.setProj_id(proj.getProj_id());
				projRef.setActivated(activated);
	
				if (!time.equals("Time in minutes")) {
					projRef.setTime(Float.parseFloat(time));
				} else {
					projRef.setTime(0);
				}
				if (!actual_time.equals("Time in minutes")) {
					projRef.setActual_time(Float.parseFloat(actual_time));
				} else {
					projRef.setActual_time(0);
				}
				if (!price.equals("Project Price")) {
					projRef.setPrice(new BigDecimal(price));
				} else {
					projRef.setPrice(new BigDecimal(0));
				}
//				if (!currency.equals("Price Currency")) {
//					projRef.setCurrency(currency);
//				} else {
//					projRef.setCurrency("");
//				}
				if (!proj_ref_desc.equals("Item Details")) {
					proj_ref_desc = proj_ref_desc.replace("\u2028", "\n");
					proj_ref_desc = proj_ref_desc.replace("\u2029", "\n");
					projRef.setProj_ref_desc(proj_ref_desc);
				} else {
					projRef.setProj_ref_desc("");
				}
				if (!topix_article_id.equals("Article ID(Topix)")) {
					projRef.setTopix_article_id(topix_article_id);
				} else {
					projRef.setTopix_article_id("");
				}
	
				projRef.setCretd_usr(user.getUserModel().getUsr_id());
				
				projectsDao.createProjectsReference(projRef);
				
				System.out.println("itm_id = " + projRef.getItm_id());
				System.out.println("proj_id = " + projRef.getProj_id());
				System.out.println("time = " + projRef.getTime());
				System.out.println("price = " + projRef.getPrice());
				System.out.println("currency = " + projRef.getCurrency());
				System.out.println("item detail = " + projRef.getProj_ref_desc());
				System.out.println("topix article id = " + projRef.getTopix_article_id());
			}
			
//			Map<String, Object> model = new HashMap<String, Object>();
//			model.put("success", true);
//			return new ModelAndView("jsonView", model);
			
			return new ModelAndView("redirect:projects.htm");
		}
	}
	
	@RequestMapping(value = "/updateProjects")
	public ModelAndView updateProjects(HttpServletRequest request, HttpServletResponse response,
			FileUploadBean uploadItem, BindingResult result) {

		UserDetailsApp user = UserLoginDetail.getUser();

		int proj_id = Integer.parseInt(request.getParameter("eproj_id"));
		String proj_name = request.getParameter("eproj_name");
		int cus_id = Integer.parseInt(request.getParameter("ecus_id"));
		String proj_desc = request.getParameter("eproj_desc");
		int file_id = Integer.parseInt(request.getParameter("efile_id"));
		String proj_currency = request.getParameter("eproj_currency");
		
		Projects proj = new Projects();
		FileModel fileModel = new FileModel();

		proj.setProj_id(proj_id);
		proj.setProj_name(proj_name);
		proj.setCus_id(cus_id);
		proj.setProj_currency(proj_currency);

//		if(!proj_currency.equals("Price Currency")){
//			proj.setProj_currency(proj_currency);
//		}else{
//			proj.setProj_currency("");
//		}
		
		if (!proj_desc.equals("Project Details")) {
			proj_desc = proj_desc.replace("\u2028", "\n");
			proj_desc = proj_desc.replace("\u2029", "\n");
			proj.setProj_desc(proj_desc);
		} else {
			proj.setProj_desc("");
		}

		System.out.println("name = " + proj.getProj_name());
		System.out.println("cus_id = " + proj.getCus_id());
		System.out.println("title = " + proj.getProj_currency());
		System.out.println("detail = " + proj.getProj_desc());

		OutputStream outputStream = null;

		// MultipartFile fileUpload = uploadItem.getFile();

		if (result.hasErrors()) {
			System.out.println("create projects error");
			// return new ModelAndView("projects");
		} else {
			if (file_id != 0) {
				System.out.println("file_id != 0");
				if (uploadItem.getFile() != null && uploadItem.getFile().getSize() != 0) {
					
					System.out.println("---------------------------------");
					System.out.println("file name = " + uploadItem.getFile().getOriginalFilename());
					System.out.println("file type = " + uploadItem.getFile().getContentType());
					System.out.println("file size = " + uploadItem.getFile().getSize());
					
					FileModel dfile = projectsDao.getFile(file_id);
					File dFile2 = new File(dfile.getFile_path()+"/"+dfile.getFile_name());
					
					if(dFile2.delete()){}
					else{
						logger.error("cannot delete file on Projects id="+proj_id);
					}

					try {
//						   File createMain = new File("/mnt/eSTUDIO/_COMMON/.jview/files");
						   File createMain = new File("/jview_storage/files");
						   if(!createMain.exists()){
							   createMain.mkdir();
						   }
						   String fileName = uploadItem.getFile().getOriginalFilename();  
//						   String filePath = "/mnt/eSTUDIO/_COMMON/.jview/files/" + proj.getProj_id();
						   String filePath = "/jview_storage/files/" + proj.getProj_id();
						   File newFile = new File(filePath);
						   if(!newFile.exists()){
							   newFile.mkdir();
						   }
						   
						   File newFile2 = new File(filePath +"/"+ fileName);
				
						   outputStream = new FileOutputStream(newFile2);
						   outputStream.write(uploadItem.getFile().getFileItem().get());
						   outputStream.close();
						   
						   fileModel.setFile_id(file_id);
						   fileModel.setFile_path(filePath);
						   fileModel.setFile_name(uploadItem.getFile().getOriginalFilename());
						   fileModel.setFile_type(uploadItem.getFile().getContentType());
						   fileModel.setFile_size(uploadItem.getFile().getSize());
						  
						  } catch (IOException e) {
						   // TODO Auto-generated catch block
						   e.printStackTrace();
						  }
					
					FileModel file_audit = new FileModel();
					file_audit = projectsDao.getFile(file_id);
					proj.setFile_name(file_audit.getFile_name());
					projectsDao.updateFile(fileModel);
					proj.setFile_id(fileModel.getFile_id());
					projectsDao.updateProjects(proj);
				} else {
					FileModel file_audit = new FileModel();
					file_audit = projectsDao.getFile(file_id);
					proj.setFile_name(file_audit.getFile_name());
					proj.setFile_id(file_id);
					projectsDao.updateProjects(proj);
				}
			}else{
				System.out.println("file_id == 0");
				if (uploadItem.getFile() != null && uploadItem.getFile().getSize() != 0) {
					System.out.println("---------------------------------");
					System.out.println("file name = " + uploadItem.getFile().getOriginalFilename());
					System.out.println("file type = " + uploadItem.getFile().getContentType());
					System.out.println("file size = " + uploadItem.getFile().getSize());
					try {
//						File createMain = new File("/mnt/eSTUDIO/_COMMON/.jview/files");
						File createMain = new File("/jview_storage/files");
						if (!createMain.exists()) {
							createMain.mkdir();
						}
						   String fileName = uploadItem.getFile().getOriginalFilename();  
//						   String filePath = "/mnt/eSTUDIO/_COMMON/.jview/files/" + proj.getProj_id();
						   String filePath = "/jview_storage/files/" + proj.getProj_id();
						   File newFile = new File(filePath);
						   if(!newFile.exists()){
							   newFile.mkdir();
						   }
						   
						   File newFile2 = new File(filePath +"/"+ fileName);

						outputStream = new FileOutputStream(newFile2);
						outputStream.write(uploadItem.getFile().getFileItem().get());
						outputStream.close();

						fileModel.setFile_id(projectsDao.getLastFileId());
						fileModel.setFile_path(filePath);
						fileModel.setFile_name(uploadItem.getFile().getOriginalFilename());
						fileModel.setFile_type(uploadItem.getFile().getContentType());
						fileModel.setFile_size(uploadItem.getFile().getSize());
						fileModel.setCretd_usr(user.getUserModel().getUsr_id());

					} catch (IOException e) {
						logger.error(e.getMessage());
					}

					proj.setFile_name("");
					projectsDao.createFile(fileModel);
					proj.setFile_id(fileModel.getFile_id());
					projectsDao.updateProjects(proj);
				} else {
					proj.setFile_name("");
					proj.setFile_id(0);
					projectsDao.updateProjects(proj);
				}
			}
		}
		return new ModelAndView("redirect:projects.htm");
	}
	
	@RequestMapping(value="/createProjectsReference")
	public ModelAndView createProjectsReference(HttpServletRequest request, HttpServletResponse response){
		
		int proj_id = Integer.parseInt(request.getParameter("aproj_id"));
		int itm_id = Integer.parseInt(request.getParameter("aitm_id"));
		String time = request.getParameter("atime");
		String actual_time = request.getParameter("aactual_time");
		String price = request.getParameter("aprice");
//		String currency = request.getParameter("acurrency");
		String proj_ref_desc = request.getParameter("aproj_ref_desc");
		String topix_article_id = request.getParameter("atopix_article_id");
		int activated = Integer.parseInt(request.getParameter("aproj_ref_activated"));
		
		ProjectsReference projRef = new ProjectsReference();
		projRef.setProj_ref_id(projectsDao.getLastProjectRefId());
		projRef.setProj_id(proj_id);
		projRef.setItm_id(itm_id);
		projRef.setCurrency("");
		projRef.setActivated(activated);
		
		if (!time.equals("Time in minutes")) {
			projRef.setTime(Float.parseFloat(time));
		} else {
			projRef.setTime(0);
		}
		if (!actual_time.equals("Time in minutes")) {
			projRef.setActual_time(Float.parseFloat(actual_time));
		} else {
			projRef.setActual_time(0);
		}
		if (!price.equals("Project Price")) {
			projRef.setPrice(new BigDecimal(price));
		} else {
			projRef.setPrice(new BigDecimal(0));
		}
//		if (!currency.equals("Price Currency")) {
//			projRef.setCurrency(currency);
//		} else {
//			projRef.setCurrency("");
//		}
		if (!proj_ref_desc.equals("Item Details")) {
			proj_ref_desc = proj_ref_desc.replace("\u2028", "\n");
			proj_ref_desc = proj_ref_desc.replace("\u2029", "\n");
			projRef.setProj_ref_desc(proj_ref_desc);
		} else {
			projRef.setProj_ref_desc("");
		}
		if (!topix_article_id.equals("Article ID(Topix)")) {
			projRef.setTopix_article_id(topix_article_id);
		} else {
			projRef.setTopix_article_id("");
		}
		
		projectsDao.createProjectsReference(projRef);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value="/updateProjectsReference")
	public ModelAndView updateProjectsReference(HttpServletRequest request, HttpServletResponse response){
		
		int proj_ref_id = Integer.parseInt(request.getParameter("eproj_ref_id"));
		int itm_id = Integer.parseInt(request.getParameter("eitm_id"));
		String time = request.getParameter("etime");
		String actual_time = request.getParameter("eactual_time");
		String price = request.getParameter("eprice");
//		String currency = request.getParameter("ecurrency");
		String proj_ref_desc = request.getParameter("eproj_ref_desc");
		String topix_article_id = request.getParameter("etopix_article_id");
		int activated = Integer.parseInt(request.getParameter("eproj_ref_activated"));
		
		ProjectsReference projRef = new ProjectsReference();
		projRef.setProj_ref_id(proj_ref_id);
		projRef.setItm_id(itm_id);
		projRef.setCurrency("");
		projRef.setActivated(activated);
		
		if (!time.equals("Time in minutes")) {
			projRef.setTime(Float.parseFloat(time));
		} else {
			projRef.setTime(0);
		}
		if (!actual_time.equals("Time in minutes")) {
			projRef.setActual_time(Float.parseFloat(actual_time));
		} else {
			projRef.setActual_time(0);
		}
		if (!price.equals("Project Price")) {
			projRef.setPrice(new BigDecimal(price));
		} else {
			projRef.setPrice(new BigDecimal(0));
		}
//		if (!currency.equals("Price Currency")) {
//			projRef.setCurrency(currency);
//		} else {
//			projRef.setCurrency("");
//		}
		if (!proj_ref_desc.equals("Item Details")) {
			proj_ref_desc = proj_ref_desc.replace("\u2028", "\n");
			proj_ref_desc = proj_ref_desc.replace("\u2029", "\n");
			projRef.setProj_ref_desc(proj_ref_desc);
		} else {
			projRef.setProj_ref_desc("");
		}
		if (!topix_article_id.equals("Article ID(Topix)")) {
			projRef.setTopix_article_id(topix_article_id);
		} else {
			projRef.setTopix_article_id("");
		}
		
		projectsDao.updateProjectsReference(projRef);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("success", true);
		return new ModelAndView("jsonView", model);
	}
	
	@RequestMapping(value="download")
	public void doDownload(HttpServletRequest request,HttpServletResponse response) throws Exception{

		try{
			int id=Integer.valueOf(request.getParameter("file"));
			FileModel fm=new FileModel();
			fm = projectsDao.getFile(id);
			
			File file = new File(fm.getFile_path() + "/" +fm.getFile_name());
			InputStream in =new BufferedInputStream(new FileInputStream(file));
			response.setContentType(fm.getFile_type());
	        response.setHeader("Content-Disposition","attachment; filename=\"" + fm.getFile_name() +"\"");
			ServletOutputStream out = response.getOutputStream();
			IOUtils.copy(in, out);
			response.flushBuffer();
			in.close();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deleteProjectsReference")
	public void deleteProjectsReference(HttpServletRequest request,
			HttpServletResponse response){

		int id = Integer.parseInt(request.getParameter("id"));
		
			projectsDao.deleteProjectsReference(id);
		
	}
	
	@RequestMapping(value = "/deleteProjects")
	public void deleteProjects(HttpServletRequest request,
			HttpServletResponse response){

		int id = Integer.parseInt(request.getParameter("id"));
		int fid = Integer.parseInt(request.getParameter("fid"));
		
		if(fid != 0){
			FileModel dfile = projectsDao.getFile(fid);
			
			File dFile2 = new File(dfile.getFile_path()+"/"+dfile.getFile_name());
			File dFile3 = new File(dfile.getFile_path());
			
			if(dFile2.delete()){
				projectsDao.deleteProjects(id);
				projectsDao.deleteFile(fid);
				dFile3.delete();
				logger.info("delete file and Projects id="+id);
				System.out.println("delete file and Projects id="+id);
			}else{
				logger.error("can't delete file on Projects id="+id);
				System.out.println("can't delete file on Projects id="+id);
			}
		}else{
			projectsDao.deleteProjects(id);
			logger.info("delete Projects id="+id);
			System.out.println("delete Projects id="+id);
		}

	}
	
	@RequestMapping(value = "/translate")
	public ModelAndView showTranslate(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("TranslatePage");
	}
	
}
