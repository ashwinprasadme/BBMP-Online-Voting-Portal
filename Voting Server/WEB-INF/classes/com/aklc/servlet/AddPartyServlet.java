package com.aklc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aklc.dao.PartyDAO;
import com.aklc.dao.PartyDAOImpl;

public class AddPartyServlet extends HttpServlet {
	private String filePath;
	private int maxFileSize;
	private int maxMemSize;

	public void init() {
		filePath = System.getenv("OPENSHIFT_DATA_DIR");
		maxFileSize = 4000 * 4000;
		maxMemSize = 40000 * 1024;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (!isMultipart) {
			resp.sendRedirect("parties.jsp?res=fail");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(System.getenv("OPENSHIFT_DATA_DIR")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		String fileName = null;
		try {
			List<FileItem> fileItems = upload.parseRequest(req);
			String name = "";
			String flag = "";
			for (FileItem fi : fileItems) {
				if (!fi.isFormField()) {
					fileName += fi.getName().substring(
							fi.getName().lastIndexOf("."));
					if (fileName == null || fileName.trim().length() == 0) {
						flag = "nofile";
						continue;
					}
					String contentType = fi.getContentType();
					if (!contentType.contains("image")) {
						flag = "invalid";
						continue;

					}

					File file;
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\")));
					} else {
						file = new File(
								filePath
										+ fileName.substring(fileName
												.lastIndexOf("\\") + 1));
					}

					fi.write(file);
					PartyDAO dao = new PartyDAOImpl();
					dao.uploadParty(name, file);

				} else {
					name = fi.getString();

				}

			}
			resp.sendRedirect("parties");

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("parties.jsp?res=fail");
		}

	}

}
