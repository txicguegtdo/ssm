package com.zpwq.dao;

import com.zpwq.bean.Bean;

public interface DAO {
	public boolean addBean(Bean bean);
	public boolean delBean(int id);
	public boolean updateBean(Bean bean);
	public Bean getBeanById(int id);
}
