package cn.techen.lbs.store;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.api.StoreService;
import cn.techen.lbs.db.model.Column;
import cn.techen.lbs.db.model.Row;
import cn.techen.lbs.db.model.Table;
import cn.techen.lbs.mm.api.MTableService;
import cn.techen.lbs.store.common.Local;

public class Redis2Mysql implements Runnable {
	private static final Logger log = (Logger) LoggerFactory  
            .getLogger(Local.PROJECT);
	
	private StoreService StoreService;
	private MTableService mTableService;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(Local.INTERVALMILLIS);
				
				start();
			} catch (InterruptedException e) {
				log.error(e.getMessage());
			} catch (Exception e) {			
				log.error(e.getMessage());
			}
		}
	}
	
	public void start() throws Exception {
		Table table = mTableService.rpop();
		if (table != null) {
			if (StoreService.save(insert(table)) <= 0) {
				StoreService.save(update(table));
			}
		}
	}
	
	private String insert(Table table) {
		StringBuffer cols = new StringBuffer();
		StringBuffer vals = new StringBuffer();
		String format = "insert into %s(%s) values(%s)";
		for (Row row : table.rows()) {
			for (Column col : row.columns()) {
				if (cols.length() > 0) {
					cols.append(",");
					vals.append(",");
				}
				cols.append(col.getName());
				vals.append(valByType(col));
			}
		}
		return String.format(format, table.getName(), cols.toString(), vals.toString());
	}
	
	private String update(Table table) {
		StringBuffer sets = new StringBuffer();
		StringBuffer cons = new StringBuffer();
		String format = "update %s set %s where %s";
		for (Row row : table.rows()) {
			for (Column col : row.columns()) {
				if (sets.length() > 0) sets.append(",");
				sets.append(col.getName() + "=" + valByType(col));
				
				if (col.isPrimary()) {
					if(cons.length() > 0) cons.append(" and ");
					cons.append(col.getName() + "=" + valByType(col));
				}
			}
		}
		return String.format(format, table.getName(), sets.toString(), cons.toString());
	}
	
	private String valByType(Column column) {
		String value = "null";
		String type = column.getType();
		Object content = column.getContent();
		if (type != null && content != null) {
			switch (type.trim().toLowerCase()) {
			case "char":
			case "varchar":
				value = "'" + column.getContent().toString() + "'";
				break;
			case "int":
			case "float":
			case "double":
				value = column.getContent().toString();
				break;
			case "datetime":
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
				value = "'" + dateFormat.format(column.getContent().toString()) + "'";
				break;
			default:
				break;
			}		
		}
		return value;
	}

	public void setStoreService(StoreService storeService) {
		StoreService = storeService;
	}

	public void setmTableService(MTableService mTableService) {
		this.mTableService = mTableService;
	}
}
