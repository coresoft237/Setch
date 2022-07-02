package setch.Dao;

import java.util.List;

public interface idao<T> {
	public boolean add(T obj);
	public boolean update(int a,T obj );
	public boolean delete(T obj);
	public  T getByid(int a);
	public List<T> getAll();

}
