package org.krams.tutorial.json;

import java.util.List;
import com.mongodb.DBObject;

public class CustomUserResponse {
	private String page;
	private String total;
	private String records;

	private List<DBObject> rows;

	public CustomUserResponse() {
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getRecords() {
		return records;
	}

	public void setRecords(String records) {
		this.records = records;
	}

	public List<DBObject> getRows() {
		return rows;
	}

	public void setRows(List<DBObject> rows) {
		this.rows = rows;
	}
}
