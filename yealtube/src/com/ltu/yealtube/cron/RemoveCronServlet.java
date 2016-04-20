package com.ltu.yealtube.cron;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.TopTube;
import com.ltu.yealtube.service.TopTubeService;

@SuppressWarnings("serial")
public class RemoveCronServlet extends HttpServlet {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(RemoveCronServlet.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			logger.info("Cron Job has been executed to delete top tube");
			
			TopTubeService service = TopTubeService.getInstance();
			CollectionResponse<TopTube> tubes = service.getDeletedTubes(null, null);
			if (tubes != null) {
				for (TopTube tube : tubes.getItems()) {
					service.delete(tube);
				}
			}
			logger.info("End Cron Job.");
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex.getCause());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
