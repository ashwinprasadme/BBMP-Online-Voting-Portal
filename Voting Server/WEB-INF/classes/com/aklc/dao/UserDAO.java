
package com.aklc.dao;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.aklc.pojo.User;

public interface UserDAO {

	public String getStatus(String email) throws Exception;

	public List<User> getPendingRequest() throws Exception;

	public User getDetails(String email) throws Exception;

	public InputStream getIdProof(String email) throws Exception;

	public void approve(String email) throws Exception;

	public void reject(String email) throws Exception;

}
