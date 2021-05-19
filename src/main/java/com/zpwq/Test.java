package com.zpwq;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zpwq.bean.Bean;
import com.zpwq.bean.User;
import com.zpwq.dao.DAO;
import com.zpwq.dao.UserDAO;





public class Test {
	


	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml");
        //BasicDataSource dbcp = (BasicDataSource)ctx.getBean("h2");
        try { 
        	
//			DAO userDAO = (DAO)ctx.getBean("userDAO");
//			
//			User bean = new User();
//			bean.setId(1);
//			bean.setName("zpwq");
//			bean.setPassword("121212");
//			userDAO.addBean(bean);
//			
//			User user = (User)userDAO.getBeanById(1);
//	        System.out.println(user.toStirng());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

					
		
	}
	
	
}
