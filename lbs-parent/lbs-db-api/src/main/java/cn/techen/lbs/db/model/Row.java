package cn.techen.lbs.db.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Table
 * 
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class Row implements Serializable {
	
	private static final long serialVersionUID = -3443488497683691632L;
	
	private int no;
	private List<Column> columns = new ArrayList<Column>();
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public List<Column> columns() {
		return columns;
	}
	public void addColumn(Column column) {
		columns.add(column);
	}
	public void addColumn(Column... column) {
		columns.addAll(Arrays.asList(column));
	}
	
}
