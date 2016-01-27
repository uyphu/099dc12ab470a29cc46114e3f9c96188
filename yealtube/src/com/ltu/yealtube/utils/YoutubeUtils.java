package com.ltu.yealtube.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ltu.yealtube.constants.Constants;
import com.ltu.yealtube.domain.Tube;

public class YoutubeUtils {
	
	/** The Constant log. */
	private static final Logger log = Logger.getLogger(YoutubeUtils.class);

	/**
	 * Gets the video.
	 *
	 * @param id the id
	 * @param part the part
	 * @return the video
	 */
	public static JSONObject getVideo(String id, String part) {
		try {

			URL url = new URL(
					"https://www.googleapis.com/youtube/v3/videos?part="+part+"&id="+id+"&key="+Constants.API_KEY);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				log.error("Failed : HTTP error code : " + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constants.UTF_8));

			String output;
			StringBuilder builder = new StringBuilder();
			while ((output = br.readLine()) != null) {
				builder.append(output);
			}

			JSONObject json = new JSONObject(builder.toString());

			conn.disconnect();
			
			return json;
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e.getCause());
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		} catch (JSONException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}
	
	public static Tube getTube(String id) {
		try {
			Tube tube = new Tube();
			JSONObject json = YoutubeUtils.getVideo(id, "statistics");
			if (json != null) {
				JSONArray jsonArray = (JSONArray)json.get("items");
				if (jsonArray != null) {
					JSONObject item = new JSONObject(jsonArray.get(0).toString());
					item = new JSONObject(item.get("statistics").toString());
					tube.setId(id);
					tube.setViewCount(Integer.parseInt(item.get("viewCount").toString()));
					tube.setLikeCount(Integer.parseInt(item.get("likeCount").toString()));
					tube.setDislikeCount(Integer.parseInt(item.get("dislikeCount").toString()));
					tube.setFavoriteCount(Integer.parseInt(item.get("favoriteCount").toString()));
					tube.setCommentCount(Integer.parseInt(item.get("commentCount").toString()));
					json = YoutubeUtils.getVideo(id, "snippet");
					if (json != null) {
						jsonArray = (JSONArray)json.get("items");
						if (jsonArray != null) {
							item = new JSONObject(jsonArray.get(0).toString());
							item = new JSONObject(item.get("snippet").toString());
							tube.setName(item.getString("title"));
							tube.setDescription(item.getString("description"));
							return tube;
						}
					}
				}
			}
			
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Tube tube = getTube("DQkrfti22mo");
		System.out.println(tube.toString());
	}

}
