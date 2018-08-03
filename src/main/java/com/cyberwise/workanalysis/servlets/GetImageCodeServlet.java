package com.cyberwise.workanalysis.servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyberwise.workanalysis.util.GetRandomImageCode;
public class GetImageCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object[] imageAndCode = GetRandomImageCode.getImageAndCode();
		request.getSession().setAttribute("rand", imageAndCode[1]);
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			ImageIO.write((BufferedImage) imageAndCode[0], "png", outputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
