package com.ltu.yealtube;

import java.io.IOException;

import javax.servlet.http.*;

import com.ltu.yealtube.helper.ConfigHelper;

@SuppressWarnings("serial")
public class YealtubeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		ConfigHelper helper = ConfigHelper.createConfig();
		System.out.println(helper.getSecretKey()); 
	}
	
}
