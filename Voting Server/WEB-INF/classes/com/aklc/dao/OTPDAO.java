
package com.aklc.dao;

public interface OTPDAO {

	public void insertOTP(String email, String otp) throws Exception;
	public boolean verifyOTP(String email, String otp) throws Exception;
}
