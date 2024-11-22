package com.ps.comp;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet extends HttpServlet {

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ServletFileUpload sfu =new ServletFileUpload(new DiskFileItemFactory());
	try {
		List<FileItem> l = sfu.parseRequest(request);
		for (FileItem fileItem : l) {
			fileItem.write(new File("D:\\FileUpload/" +fileItem.getName()));
			PrintWriter out = response.getWriter();
			out.println("Uploaded "+ fileItem.getName());
		}
		
	} catch (FileUploadException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
