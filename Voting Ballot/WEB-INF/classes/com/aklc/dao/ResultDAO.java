package com.aklc.dao;

import java.util.List;

import com.aklc.pojo.Election;
import com.aklc.pojo.Result;

public interface ResultDAO {

	public List<Election> getElectioNList() throws Exception;

	public List<Result> getResults(int eid) throws Exception;

}
