package com.ltu.yealtube.cron;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Playlist;
import com.ltu.yealtube.domain.TopTube;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.service.PlaylistService;
import com.ltu.yealtube.service.TopTubeService;
import com.ltu.yealtube.utils.YoutubeUtils;

@SuppressWarnings("serial")
public class TopTubeCronServlet extends HttpServlet {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(TopTubeCronServlet.class);
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		try {
			logger.info("Cron Job has been executed to delete top tube");
			//Cursor cursor = null;
//			TopTubeService service = TopTubeService.getInstance();
//			CollectionResponse<Tube> tubes = service.searchTubes("status = ", (Integer)Constant.APPROVED_STATUS, cursor, (Integer)10);
//			if (tubes != null) {
//				for (TopTube tube : tubes.getItems()) {
//					service.delete(tube);
//				}
//			}
			PlaylistService playlistService = PlaylistService.getInstance();
			CollectionResponse<Playlist> playlists = playlistService.searchPlaylist(null, null, null);
			for (Playlist playlist : playlists.getItems()) {
				Playlist temp = YoutubeUtils.getPlayList(playlist.getId());
				playlist.setThumbnail(temp.getThumbnail());
				logger.info("Playlist Id: "+ playlist.toString());
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
