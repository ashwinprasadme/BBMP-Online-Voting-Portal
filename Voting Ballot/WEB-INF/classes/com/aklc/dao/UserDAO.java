
package com.aklc.dao;

import java.io.File;
import java.io.InputStream;

import com.aklc.pojo.User;

public interface UserDAO {

	public void register(User user) throws Exception;

	public String getStatus(String email) throws Exception;

	public String login(String email, String password) throws Exception;

	void register2(String email, File file) throws Exception;

	public String getAssembly(String user) throws Exception;

}
