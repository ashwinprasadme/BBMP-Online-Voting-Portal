package com.aklc.update;

import java.util.TimerTask;

import com.aklc.dao.ElectionDAO;
import com.aklc.dao.ElectionDAOImpl;

public class UpdateElectionState extends TimerTask {
	ElectionDAO dao;

	public UpdateElectionState() {
		dao = new ElectionDAOImpl();

	}

	@Override
	public void run() {

		try {
			dao.updateElectionStatus();
			System.out.println("Done updating the election statuses");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
