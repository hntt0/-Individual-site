package jp.co.internous.sampleweb.model.form;

import java.io.Serializable;

public class SearchForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String productName;
	private int categoryId;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}	
}
