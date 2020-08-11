package com.jview.dao;

import java.util.List;
import java.util.Map;

import com.jview.model.Invoice;
import com.jview.model.InvoiceCompany;
import com.jview.model.InvoiceReference;
import com.jview.model.Topix;
import com.jview.model.TopixConfig;
import com.jview.model.TopixReference;

public interface InvoiceDao {

	public List<Invoice> searchInvoice(Map<String, String> data);
	
	public List<InvoiceReference> searchInvoiceReference(int id);
	
	public List<InvoiceCompany> showInvoiceCompany();
	
	public List<Invoice> showInvoiceCustomer(int cus_id, String month, String year);
	
	public int addInvoice(Invoice inv);
	
	public void addInvoiceReference(InvoiceReference inv_ref, Map<String, Float> map);
	
	public void updateInvoice(Invoice inv, Map<String, Float> map);
	
	public void updateInvoicereference(InvoiceReference inv_ref, Map<String, Float> map);
	
	public void updateInvoiceReferenceBatch(List<InvoiceReference> invRefLs, Map<String, Float> map);
	
	public void deleteInvoice(int id);
	
	public void deleteInvoiceReference(int id, Map<String, Float> map);
	
	public Invoice getInvoiceById(int id);
	
	public InvoiceCompany getInvoiceCompanyById(int inv_company_id);
	
	public List<InvoiceReference> getJobItemList(int job_id);
	
	public List<InvoiceReference> getListDataFromRequest(Object data);
	
	public List<Invoice> showInvoiceMonthlyReport(String year, int inv_company_id, String inv_bill_type);

	public List<Invoice> showInvoiceReport(Map<String, String> data);
	
	public TopixConfig getTopixConfig();
	
	public int addTopix(Topix tpx);
	
	public void addTopixReference(TopixReference tpx_ref);
	
	public void updateInvoiceStatus(int inv_id, String status, String inv_number);
	
	public void updateTopixAuditLogging(Topix tpx);
	
	public String getLastInvoiceNumber(int inv_company_id, String delivery_date, String inv_bill_type);
}
