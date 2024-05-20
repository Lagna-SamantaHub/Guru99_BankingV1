package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig(){//constructor
		File src=new File("./Configuration/config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
			
		}catch(Exception e) {
			System.out.println("Exception Msg"+e.getMessage());
		}
	}
	
	public String getApplictionURL() {
		String url=pro.getProperty("BaseURL");
		return url;
	}
	public String getUsername() {
		String uname=pro.getProperty("Username");
		return uname;
	}
	public String getPassword() {
		String pass=pro.getProperty("Password");
		return pass;
	}



}
