package org.mule.modules.excel.entities;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonRequest implements Serializable{

	private static final long serialVersionUID = -2471759801631614792L;
	
	@NotNull
	@JsonProperty(required = true)
	private List<JsonRequestSheet> sheets;

	public List<JsonRequestSheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<JsonRequestSheet> sheets) {
		this.sheets = sheets;
	}
	
}
