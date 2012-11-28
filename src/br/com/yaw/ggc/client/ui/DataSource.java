package br.com.yaw.ggc.client.ui;

import java.io.Serializable;

public interface DataSource<T extends Serializable> {

	int getRowCount();
	
	T getRow(int index);
	
	void add(T t);
	
	void remove(T t);
	
}
