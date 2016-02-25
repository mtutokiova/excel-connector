package org.mule.modules.excel.client;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequest implements Serializable{

	private static final long serialVersionUID = -2471759801631614792L;

	@JsonProperty
	private String excelTitle;
	
	@JsonProperty
	private List<JsonRequestSheet> sheets;

	public String getExcelTitle() {
		return excelTitle;
	}

	public void setExcelTitle(String excelTitle) {
		this.excelTitle = excelTitle;
	}

	public List<JsonRequestSheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<JsonRequestSheet> sheets) {
		this.sheets = sheets;
	}
	
}
