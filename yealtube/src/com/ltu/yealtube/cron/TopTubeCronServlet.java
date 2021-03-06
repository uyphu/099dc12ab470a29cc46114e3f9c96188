package com.ltu.yealtube.cron;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.Playlist;
import com.ltu.yealtube.service.PlaylistService;
import com.ltu.yealtube.utils.YoutubeUtils;

@SuppressWarnings("serial")
public class TopTubeCronServlet extends HttpServlet {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TopTubeCronServlet.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			logger.info("Cron Job has been executed to update thumbnail");
			PlaylistService playlistService = PlaylistService.getInstance();
			CollectionResponse<Playlist> playlists = playlistService.searchPlaylist(null, null, null);
			for (Playlist playlist : playlists.getItems()) {
				Playlist temp = YoutubeUtils.getPlayList(playlist.getId());
				playlist.setThumbnail(temp.getThumbnail());
				playlistService.updatePlaylist(playlist);
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
