
package com.aklc.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.aklc.dao.UserDAO;
import com.aklc.dao.UserDAOImpl;

public class Register2Servlet extends HttpServlet {

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
			resp.sendRedirect("register2.jsp?res=fail");
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(System.getenv("OPENSHIFT_DATA_DIR")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(maxFileSize);
		String fileName = null;
		try {
			List<FileItem> fileItems = upload.parseRequest(req);

			String flag = "";
			for (FileItem fi : fileItems) {
				if (!fi.isFormField()) {
					fileName = ((String) req.getSession().getAttribute("email"))
							.split("@")[0];

					fileName += fi.getName().substring(
							fi.getName().lastIndexOf("."));
					if (fileName == null || fileName.trim().length() == 0) {
						flag = "nofile";
						break;
					}
					String contentType = fi.getContentType();
					if (!contentType.contains("image")) {
						flag = "invalid";
						break;

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
					UserDAO dao = new UserDAOImpl();
					dao.register2(
							(String) req.getSession().getAttribute("email"),
							file);

				}

			}

			resp.sendRedirect("register2.jsp?res=done");

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("register2.jsp?res=fail");
		}

	}

}
