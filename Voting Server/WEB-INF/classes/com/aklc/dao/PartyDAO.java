package com.aklc.dao;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface PartyDAO {

	public void uploadParty(String name, File logo) throws Exception;

	public List<String> getpartyNames() throws Exception;

	public InputStream getPartyLogo(String name) throws Exception;

	public void deleteParty(String name) throws Exception;

}
