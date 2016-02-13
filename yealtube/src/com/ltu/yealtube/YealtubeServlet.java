package com.ltu.yealtube;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class YealtubeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
//		ConfigHelper helper = ConfigHelper.createConfig();
//		System.out.println(helper.getSecretKey()); 
		//Category category = YoutubeUtils.getCategory("LxUm5sml15k");
		//resp.getWriter().println(category.toString());
	}
	
}
