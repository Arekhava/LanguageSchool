package com.arekhava.languageschool.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.arekhava.languageschool.controller.command.Command;
import com.arekhava.languageschool.controller.command.CommandProvider;
import com.arekhava.languageschool.controller.command.PagePath;
import com.arekhava.languageschool.controller.command.ParameterAndAttribute;
import com.arekhava.languageschool.controller.command.Router;

/**
 * File uploading servlet receive multipart/form-data request from student
 * 
 * @author N
 * @see HttpServlet
 */
@WebServlet(name = "upload", urlPatterns = { "/upload" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadingServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();
	private static final String BUNDLE_NAME = "path";
	private static final String PATH_IMG = "path.img";
	private static final String FORMAT_FILE_NAME = "jpg";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imageName = request.getParameter(ParameterAndAttribute.IMAGE_NAME);
		String path = ResourceBundle.getBundle(BUNDLE_NAME).getString(PATH_IMG);
		File file = new File(path + imageName);
		BufferedImage bufferedImage = ImageIO.read(file);
		try (OutputStream outputStream = response.getOutputStream()) {
			ImageIO.write(bufferedImage, FORMAT_FILE_NAME, outputStream);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(ParameterAndAttribute.COMMAND);
		Command command = CommandProvider.defineCommand(commandName);
		Router router = command.execute(request);
		switch (router.getRouteType()) {
		case REDIRECT:
			response.sendRedirect(router.getPagePath());
			break;
		case FORWARD:
			RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
			dispatcher.forward(request, response);
			break;
		default:
			logger.error("incorrect route type " + router.getRouteType());
			response.sendRedirect(PagePath.ERROR);
		}
	}
}
