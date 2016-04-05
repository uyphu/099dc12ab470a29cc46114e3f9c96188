package com.ltu.yealtube.service;

import java.util.Calendar;

import com.google.api.client.http.HttpStatusCodes;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.dao.PlaylistDao;
import com.ltu.yealtube.domain.Playlist;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.exception.ErrorCode;
import com.ltu.yealtube.exception.ErrorCodeDetail;
import com.ltu.yealtube.utils.YoutubeUtils;

/**
 * The Class PlaylistService.
 * 
 * @author uyphu
 */
public class PlaylistService {

	/** The log. */
	// private final Logger log = Logger.getLogger(PlaylistService.class);

	/** The playlist dao. */
	private PlaylistDao playlistDao = PlaylistDao.getInstance();

	/** The instance. */
	private static PlaylistService instance = null;

	/**
	 * Instantiates a new playlist dao.
	 */
	public PlaylistService() {

	}

	/**
	 * Gets the single instance of PlaylistService.
	 * 
	 * @return single instance of PlaylistService
	 */
	public static PlaylistService getInstance() {
		if (instance == null) {
			instance = new PlaylistService();
		}
		return instance;
	}

	/**
	 * List playlist.
	 * 
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 */
	public CollectionResponse<Playlist> listPlaylist(String cursorString, Integer count) {
		return playlistDao.list(cursorString, count);
	}

	/**
	 * Insert playlist.
	 * 
	 * @param playlist
	 *            the playlist
	 * @return the playlist
	 * @throws CommonException
	 *             the common exception
	 */
	public Playlist insertPlaylist(Playlist playlist) throws CommonException {
		if (playlist != null && playlist.getId() != null) {
			if (containsPlaylist(playlist)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			return playlistDao.persist(playlist);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Contains playlist.
	 * 
	 * @param playlist
	 *            the playlist
	 * @return true, if successful
	 */
	private boolean containsPlaylist(Playlist playlist) {
		return containsPlaylist(playlist.getId());
	}

	/**
	 * Contains playlist.
	 * 
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	private boolean containsPlaylist(String id) {
		boolean contains = true;
		if (id != null) {
			Playlist item = playlistDao.find(id);
			if (item == null) {
				contains = false;
			}
		} else {
			return contains = false;
		}

		return contains;
	}

	/**
	 * Update playlist.
	 * 
	 * @param playlist
	 *            the playlist
	 * @return the playlist
	 * @throws CommonException
	 *             the common exception
	 */
	public Playlist updatePlaylist(Playlist playlist) throws CommonException {

		if (playlist != null && playlist.getId() != null) {
			if (!containsPlaylist(playlist)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_NOT_FOUND,
						ErrorCodeDetail.ERROR_RECORD_NOT_FOUND.getMsg());
			}
			return playlistDao.update(playlist);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

	/**
	 * Removes the playlist.
	 * 
	 * @param id
	 *            the id
	 * @throws CommonException
	 *             the common exception
	 */
	public void removePlaylist(String id) throws CommonException {
		Playlist record = findRecord(id);
		if (record == null) {
			throw new CommonException(ErrorCode.NOT_FOUND_EXCEPTION, ErrorCodeDetail.ERROR_RECORD_NOT_FOUND);
		}
		playlistDao.delete(record);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the playlist
	 */
	public Playlist findRecord(String id) {
		PlaylistDao dao = new PlaylistDao();
		return dao.find(id);
	}

	/**
	 * Inits the data.
	 */
	public void initData() {
		playlistDao.initData();
	}

	/**
	 * Clean data.
	 */
	public void cleanData() {
		playlistDao.cleanData();
	}

	/**
	 * Search playlist.
	 * 
	 * @param querySearch
	 *            the query search
	 * @param cursorString
	 *            the cursor string
	 * @param count
	 *            the count
	 * @return the collection response
	 * @throws CommonException
	 *             the common exception
	 */
	public CollectionResponse<Playlist> searchPlaylist(String querySearch,
			String cursorString, Integer count) throws CommonException {
		return playlistDao.searchPlaylist(querySearch, cursorString, count);
	}
	
	/**
	 * Insert.
	 *
	 * @param playlistId the playlist id
	 * @return the playlist
	 * @throws CommonException the common exception
	 */
	public Playlist insert(String playlistId, int viewCount) throws CommonException {
		if (playlistId != null) {
			if (containsPlaylist(playlistId)) {
				throw new CommonException(HttpStatusCodes.STATUS_CODE_FOUND, ErrorCodeDetail.ERROR_EXIST_OBJECT.getMsg());
			}
			Playlist playlist = YoutubeUtils.getPlayList(playlistId);
			playlist.setCreatedAt(Calendar.getInstance().getTime());
			playlist.setModifiedAt(Calendar.getInstance().getTime());
			playlist.setStatus(Constant.APPROVED_STATUS);
			playlist.setUserId(Constant.ADMIN_ID);
			playlist.setViewCount(viewCount);
			return playlistDao.persist(playlist);
		} else {
			throw new CommonException(HttpStatusCodes.STATUS_CODE_BAD_GATEWAY, ErrorCodeDetail.ERROR_INPUT_NOT_VALID.getMsg());
		}
	}

}
