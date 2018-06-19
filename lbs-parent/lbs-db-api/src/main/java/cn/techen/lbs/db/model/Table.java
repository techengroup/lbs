package cn.techen.lbs.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Table
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Table implements Serializable {
	
	private static final long serialVersionUID = 2019361049885942141L;
	
	private String name;
	private List<Row> rows = new ArrayList<Row>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Row> rows() {
		return rows;
	}
	public void addRow(Row row) {
		rows.add(row);
	}
	public void addRow(Column... column) {
		Row row = new Row();
		row.addColumn(column);
		rows.add(row);
	}
}
