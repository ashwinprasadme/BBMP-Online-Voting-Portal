package com.aklc.dao;

import java.util.List;

import com.aklc.pojo.Election;
import com.aklc.pojo.Participants;

public interface ElectionDAO {

	public List<Election> read() throws Exception;

	public void write(Election e) throws Exception;

	public void delete(int eid) throws Exception;

	public List<Participants> getparticipants(int eid) throws Exception;

	public void addParticipant(Participants p) throws Exception;

	public List<String> getPartyNames() throws Exception;

	public void removeParticipant(Participants p) throws Exception;

}
