package cn.techen.lbs.db.api;

import java.util.List;

/**
 * IService
 * @author ZY
 * @since 2018-03-14 16:55
 * @param <T>
 */
public interface IService<T> {
	T selectByKey(Object key);
		
	List<T> selectAll();

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);
    
}
