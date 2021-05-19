package com.zpwq.db;
/**
 * 
 * H2数据库服务启动类
 * 
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.h2.tools.Server;
import org.springframework.stereotype.Component;

@Component
public class H2Server {
	private Server server;
	private String H2_IS_SERVER;
	private String H2_PATH = "H2.properties";
	private String H2_DRIVER = "org.h2.Driver";
	private String H2_TCP_START;
	private String H2_WEB_START;
	private String url = "jdbc:h2:mem:test";
	private String user = "root" ;
	private String password = "root";
	private String tcpPort = "8082";
	private String webPort = "8083";
	private Properties h2;
	private Connection conn;
	public H2Server(){
		System.out.println("H2Server初始化.......");
	}
	
	@PostConstruct
	public void init(){
		try{
			initProperties();
			if(H2_IS_SERVER!=null && "true".equalsIgnoreCase(H2_IS_SERVER)){
				Class.forName(H2_DRIVER);
				//使用内存模式时,需要保持一个连接
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("H2数据库启动成功,"+conn.toString());
				if(H2_TCP_START !=null && "true".equalsIgnoreCase(H2_TCP_START)){
					server = Server.createTcpServer(new String[] { "-tcpPort", tcpPort }).start();
					System.out.println("H2数据库启动TCP服务成功,"+server.getURL());
				}
				if(H2_WEB_START !=null && "true".equalsIgnoreCase(H2_WEB_START)){
					server = Server.createWebServer(new String[] { "-webPort", webPort }).start();
					System.out.println("H2数据库启动web服务成功,"+server.getURL());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@PreDestroy
	public void destory(){
		try{
			if(server != null){
				server.stop();
			}
			if(conn != null){
				conn.close();
			}
			System.out.println("H2数据库服务停止成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initProperties() throws Exception{
		System.out.println("开始读取H2配置文件....");
		InputStream in = null;
		try{
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(H2_PATH);
			h2 =  new Properties();
			h2.load(in);
			H2_IS_SERVER = h2.getProperty("is_db_server");
			H2_TCP_START =  h2.getProperty("tcp.start");
			H2_WEB_START = h2.getProperty("web.start");
			H2_DRIVER = h2.getProperty("db.driver")==null?H2_DRIVER:h2.getProperty("db.driver");
			url = h2.getProperty("db.url")==null?url:h2.getProperty("db.url");
			user = h2.getProperty("db.user")==null?user:h2.getProperty("db.user");
			password = h2.getProperty("db.password")==null?password:h2.getProperty("db.password");
			tcpPort = h2.getProperty("tcp.port")==null?tcpPort:h2.getProperty("tcp.port");
			webPort = h2.getProperty("web.port")==null?webPort:h2.getProperty("web.port");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(in != null){
				in.close();
			}
		}
	}
}
