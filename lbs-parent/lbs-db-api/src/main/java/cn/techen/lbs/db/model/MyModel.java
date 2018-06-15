package cn.techen.lbs.db.model;

import java.io.Serializable;

/**
 * MyModel
 * @author ZY
 * @since 2018-03-14 16:55
 */
public class MyModel implements Serializable {
	private static final long serialVersionUID = 4293988404199513191L;

    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
