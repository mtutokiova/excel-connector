package org.mule.modules.excel.client;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequestSheet implements Serializable {

	private static final long serialVersionUID = -8155614680841839189L;

	@JsonProperty
	private String sheetName;
	
	@JsonProperty
	private List<Map<String, String>> sheetBody;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<Map<String, String>> getSheetBody() {
		return sheetBody;
	}

	public void setSheetBody(List<Map<String, String>> sheetBody) {
		this.sheetBody = sheetBody;
	}
	
}
