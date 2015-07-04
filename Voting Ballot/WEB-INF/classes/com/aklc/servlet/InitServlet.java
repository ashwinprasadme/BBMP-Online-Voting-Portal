package com.aklc.servlet;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.aklc.update.UpdateElectionState;

public class InitServlet extends GenericServlet {

	
	@Override
	public void init() throws ServletException {
		TimerTask task = new UpdateElectionState();
		Timer timer = new Timer();
		timer.schedule(task, 10000, 500000);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {

	}

}
