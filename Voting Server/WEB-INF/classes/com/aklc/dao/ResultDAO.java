package com.aklc.dao;

import java.util.List;

import com.aklc.pojo.Result;

public interface ResultDAO {

	void vote(String assembly, String pname, String name, String eid,
			String email) throws Exception;

	public List<Result> getResults(int eid) throws Exception;
}
