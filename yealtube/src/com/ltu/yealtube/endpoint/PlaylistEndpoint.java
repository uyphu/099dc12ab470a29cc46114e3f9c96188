package com.ltu.yealtube.endpoint;

import javax.annotation.Nullable;
import javax.inject.Named;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.ltu.yealtube.domain.Playlist;
import com.ltu.yealtube.exception.CommonException;
import com.ltu.yealtube.service.PlaylistService;

@Api(name = "playlistendpoint", namespace = @ApiNamespace(ownerDomain = "ltu.com", ownerName = "ltu.com", packagePath = "yealtube.domain"))
public class PlaylistEndpoint {

	/**
	 * Return a collection of playlists
	 * 
	 * @param count
	 *            The number of playlists
	 * @return a list of Playlists
	 */
	@ApiMethod(name = "listPlaylist")
	public CollectionResponse<Playlist> listPlaylist(@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("count") Integer count) {
		PlaylistService service = PlaylistService.getInstance();
		return service.listPlaylist(cursorString, count);
	}

	/**
	 * This inserts a new <code>Playlist</code> object.
	 * 
	 * @param playlist
	 *            The object to be added.
	 * @return The object to be added.
	 */
	@ApiMethod(name = "insertPlaylist")
	public Playlist insertPlaylist(Playlist playlist) throws CommonException {
		PlaylistService service = PlaylistService.getInstance();
		return service.insertPlaylist(playlist);
	}

	/**
	 * This updates an existing <code>Playlist</code> object.
	 * 
	 * @param playlist
	 *            The object to be added.
	 * @return The object to be updated.
	 */
	@ApiMethod(name = "updatePlaylist")
	public Playlist updatePlaylist(Playlist playlist) throws CommonException {
		PlaylistService service = PlaylistService.getInstance();
		return service.updatePlaylist(playlist);
	}

	/**
	 * This deletes an existing <code>Playlist</code> object.
	 * 
	 * @param id
	 *            The id of the object to be deleted.
	 * @throws CommonException
	 *             the proconco exception
	 */
	@ApiMethod(name = "removePlaylist")
	public void removePlaylist(@Named("id") Long id) throws CommonException {
		PlaylistService service = PlaylistService.getInstance();
		service.removePlaylist(id);
	}

	/**
	 * Gets the playlist.
	 * 
	 * @param id
	 *            the id
	 * @return the playlist
	 */
	@ApiMethod(name = "getPlaylist")
	public Playlist getPlaylist(@Named("id") Long id) {
		return findRecord(id);
	}

	/**
	 * Find record.
	 * 
	 * @param id
	 *            the id
	 * @return the user main
	 */
	private Playlist findRecord(Long id) {
		PlaylistService service = PlaylistService.getInstance();
		return service.findRecord(id);
	}

	/**
	 * Inits the data.
	 */
	@ApiMethod(name = "initData", httpMethod = HttpMethod.POST, path = "initData")
	public void initData() {
		PlaylistService service = PlaylistService.getInstance();
		service.initData();
	}

	/**
	 * Clean data.
	 */
	@ApiMethod(name = "cleanData", httpMethod = HttpMethod.POST, path = "cleanData")
	public void cleanData() {
		PlaylistService service = PlaylistService.getInstance();
		service.cleanData();
	}

	@ApiMethod(name = "searchPlaylist", httpMethod = HttpMethod.GET, path = "search_playlist")
	public CollectionResponse<Playlist> searchPlaylist(@Nullable @Named("querySearch") String querySearch,
			@Nullable @Named("cursor") String cursorString, @Nullable @Named("count") Integer count) throws CommonException {
		PlaylistService service = PlaylistService.getInstance();
		return service.searchPlaylist(querySearch, cursorString, count);
	}

}
