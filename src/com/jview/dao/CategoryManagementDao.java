package com.jview.dao;

import java.util.List;

import com.jview.model.Category;

public interface CategoryManagementDao {

	public List<Category> showCate1();
	
	public List<Category> showCate2(String ref);
	
	public List<Category> showCate3(String ref);
	
	public int getLastCateId();
	
	public void createCate(Category cate);
	
	public List<Category> getListDataFromRequest(Object data);

	public boolean deleteCate1(List<Category> cate);

	public boolean updateCate1(List<Category> cate);

	public boolean deleteCateRef(List<Category> cate);
	
}
