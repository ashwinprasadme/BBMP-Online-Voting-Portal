package com.aklc.dao;

import java.util.List;

import com.aklc.pojo.Election;
import com.aklc.pojo.Participants;

public interface ElectionDAO {

	public List<Election> read() throws Exception;

	public List<Participants> getparticipants(int eid) throws Exception;

	public List<String> getPartyNames() throws Exception;
	
	public void updateElectionStatus() throws Exception;
	
	public List<Integer> getEIDsForUser(String email) throws Exception;
	

}
