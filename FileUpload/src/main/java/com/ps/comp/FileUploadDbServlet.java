package com.ps.comp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadDbServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
			List<FileItem> items = sfu.parseRequest(request);
			for (FileItem fileItem : items) {
				InputStream fileContent = fileItem.getInputStream();
				String file_name = fileItem.getName();
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
				String sql = "INSERT INTO uploaded_files (file_name, file_data) VALUES (?, ?)";
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, file_name);
				pstmt.setBlob(2, fileContent);
				pstmt.executeUpdate();
				PrintWriter out = response.getWriter();
				out.println("Uploaded "+ fileItem.getName());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
