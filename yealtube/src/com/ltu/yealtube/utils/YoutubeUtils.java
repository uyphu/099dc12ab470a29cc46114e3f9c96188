package com.ltu.yealtube.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ltu.yealtube.constants.Constant;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.service.CategoryService;

// TODO: Auto-generated Javadoc
/**
 * The Class YoutubeUtils.
 * 
 * @author uyphu
 */
public class YoutubeUtils {

	/** The Constant log. */
	private static final Logger log = Logger.getLogger(YoutubeUtils.class);

	/**
	 * Gets the video.
	 * 
	 * @param id
	 *            the id
	 * @param part
	 *            the part
	 * @return the video
	 */
	public static JSONObject getVideo(String id, String part) {
		try {

			URL url = new URL("https://www.googleapis.com/youtube/v3/videos?part=" + part + "&id=" + id + "&key="
					+ Constant.API_KEY);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				log.error("Failed : HTTP error code : " + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			// Set UTF8 encode
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constant.UTF_8));

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

	/**
	 * Gets the tube from youtube.
	 * 
	 * @param id
	 *            the id
	 * @return the tube
	 */
	public static Tube getTube(String id) {
		try {
			Tube tube = new Tube();
			JSONObject json = YoutubeUtils.getVideo(id, "statistics");
			if (json != null) {
				JSONArray jsonArray = (JSONArray) json.get("items");
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
						jsonArray = (JSONArray) json.get("items");
						if (jsonArray != null) {
							item = new JSONObject(jsonArray.get(0).toString());
							item = new JSONObject(item.get("snippet").toString());
							tube.setTitle(item.getString("title"));
							tube.setDescription(item.getString("description"));
							tube.setEmbedHtml(YoutubeUtils.getEmbedHtml(id));
							tube.setDuration(YoutubeUtils.getDuration(id));
							try {
								tube.setTags(item.getString("tags"));
							} catch (Exception e) {
								log.error(e.getMessage(), e.getCause());
							}

							tube.setAuthor("admin");
							tube.setUserId(Constant.ADMIN_ID);
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

	/**
	 * Call youtube.
	 * 
	 * @param url
	 *            the url
	 * @return the JSON object
	 */
	public static JSONObject callYoutube(URL url) {
		try {

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				log.error("Failed : HTTP error code : " + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constant.UTF_8));

			String output;
			StringBuilder builder = new StringBuilder();
			while ((output = br.readLine()) != null) {
				builder.append(output);
			}

			JSONObject json = new JSONObject(builder.toString());
			conn.disconnect();

			return json;
		} catch (IOException e) {
			log.error(e.getMessage(), e.getCause());
		} catch (JSONException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * Gets the player.
	 * 
	 * @param id
	 *            the id
	 * @return the player
	 */
	public static JSONObject getPlayer(String id) {
		String urlString = "https://www.googleapis.com/youtube/v3/videos?part=player&id=" + id + "&key=" + Constant.API_KEY;
		try {
			URL url = new URL(urlString);
			return callYoutube(url);
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * Gets the category by id.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return the category by id
	 */
	public static JSONObject getCategoryById(String categoryId) {
		try {
			URL url = new URL("https://www.googleapis.com/youtube/v3/videoCategories?part=snippet&id=" + categoryId + "&key="
					+ Constant.API_KEY);
			return callYoutube(url);
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * Gets the embed html.
	 * 
	 * @param id
	 *            the id
	 * @return the embed html
	 */
	public static String getEmbedHtml(String id) {
		String urlString = "https://www.googleapis.com/youtube/v3/videos?part=player&id=" + id + "&key=" + Constant.API_KEY;
		try {
			URL url = new URL(urlString);
			JSONObject json = callYoutube(url);
			JSONArray jsonArray = (JSONArray) json.get("items");
			json = new JSONObject(jsonArray.get(0).toString());
			json = new JSONObject(json.get("player").toString());
			return json.getString("embedHtml");
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e.getCause());
		} catch (JSONException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * Gets the category.
	 * 
	 * @param videoId
	 *            the video id
	 * @return the category
	 */
	public static Category getCategory(String videoId) {
		try {
			JSONObject json = YoutubeUtils.getVideo(videoId, "snippet");
			if (json != null) {
				JSONArray jsonArray = (JSONArray) json.get("items");
				if (jsonArray != null) {
					json = new JSONObject(jsonArray.get(0).toString());
					json = new JSONObject(json.get("snippet").toString());
					String categoryId = json.getString("categoryId");
					Category category = findCategory(categoryId);
					if (category != null) {
						return category;
					} else {
						try {
							String tags = json.getString("tags");
							return findCategoryByTags(tags);
						} catch (Exception e) {
							log.error(e.getMessage(), e.getCause());
						}
					}
				}
			}
		} catch (JSONException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * Retrieve category.
	 * 
	 * @param categoryId
	 *            the category id
	 * @return the category
	 */
	private static Category findCategory(String categoryId) {
		try {
			JSONObject categoryJson = getCategoryById(categoryId);
			if (categoryJson != null) {
				JSONArray jsonArray = (JSONArray) categoryJson.get("items");
				if (jsonArray != null) {
					JSONObject item = new JSONObject(jsonArray.get(0).toString());
					item = new JSONObject(item.getString("snippet"));
					return CategoryService.getInstance().findOneByKeword(item.getString("title"));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * Find category by tags.
	 * 
	 * @param tags
	 *            the tags
	 * @return the category
	 */
	private static Category findCategoryByTags(String tags) {
		return CategoryService.getInstance().findOneByTags(tags);
	}

	/**
	 * Gets the all categories from youtube.
	 * 
	 * @return the all categories from youtube
	 */
	public static List<Category> getAllCategories() {
		URL url;
		List<Category> categories = new ArrayList<Category>();
		for (int i = 0; i < 100; i++) {
			try {
				url = new URL("https://www.googleapis.com/youtube/v3/videoCategories?part=snippet&id=" + i + "&key="
						+ Constant.API_KEY);
				JSONObject jsonObject = callYoutube(url);
				JSONArray jsonArray = (JSONArray) jsonObject.get("items");
				if (jsonArray != null && jsonArray.length() > 0) {
					jsonObject = new JSONObject(jsonArray.get(0).toString());
					Category category = new Category();
					category.setId(Long.parseLong(jsonObject.getString("id")));
					jsonObject = new JSONObject(jsonObject.getString("snippet"));
					category.setName(jsonObject.getString("title"));
					categories.add(category);
				}
			} catch (MalformedURLException e) {
				log.error(e.getMessage(), e.getCause());
			} catch (JSONException e) {
				log.error(e.getMessage(), e.getCause());
			}
		}
		return categories;
	}

	/**
	 * Convert you tube duration.
	 *
	 * @param duration the duration
	 * @return the string
	 */
	public static String convertYouTubeDuration(String duration) {
		String youtubeDuration = duration; // "PT1H2M30S"; // "PT1M13S";
		Calendar c = new GregorianCalendar();
		try {
			DateFormat df = new SimpleDateFormat("'PT'mm'M'ss'S'");
			Date d = df.parse(youtubeDuration);
			c.setTime(d);
		} catch (ParseException e) {
			try {
				DateFormat df = new SimpleDateFormat("'PT'hh'H'mm'M'ss'S'");
				Date d = df.parse(youtubeDuration);
				c.setTime(d);
			} catch (ParseException e1) {
				try {
					DateFormat df = new SimpleDateFormat("'PT'ss'S'");
					Date d = df.parse(youtubeDuration);
					c.setTime(d);
				} catch (ParseException e2) {
				}
			}
		}
		c.setTimeZone(TimeZone.getDefault());

		String time = "";
		if (c.get(Calendar.HOUR) > 0) {
			if (String.valueOf(c.get(Calendar.HOUR)).length() == 1) {
				time += "0" + c.get(Calendar.HOUR);
			} else {
				time += c.get(Calendar.HOUR);
			}
			time += ":";
		}
		// test minute
		if (String.valueOf(c.get(Calendar.MINUTE)).length() == 1) {
			time += "0" + c.get(Calendar.MINUTE);
		} else {
			time += c.get(Calendar.MINUTE);
		}
		time += ":";
		// test second
		if (String.valueOf(c.get(Calendar.SECOND)).length() == 1) {
			time += "0" + c.get(Calendar.SECOND);
		} else {
			time += c.get(Calendar.SECOND);
		}
		return time;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @param id the id
	 * @return the duration
	 */
	public static String getDuration(String id) {
		try {
			URL url = new URL(" https://www.googleapis.com/youtube/v3/videos?part=contentDetails&id="+id+"&key="+Constant.API_KEY);
			JSONObject json = callYoutube(url);
			JSONArray jsonArray;
			jsonArray = (JSONArray) json.get("items");
			json = new JSONObject(jsonArray.get(0).toString());
			json = new JSONObject(json.get("contentDetails").toString());
			return convertYouTubeDuration(json.getString("duration"));
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e.getCause());
		} catch (JSONException e) {
			log.error(e.getMessage(), e.getCause());
		}
		return null;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// Tube tube = getTube("AY7GXcmXLE0");
		// System.out.println(tube.toString());
		// sendTube("2xk7ZiN7A6s");
		// System.out.println(getEmbedHtml("AY7GXcmXLE0"));
		// Category category = getCategory("LxUm5sml15k");
		// System.out.println(category.toString());
//		String str = "[\"av\",\"sora\",\"aoi\",\"sexy\",\"hot\",\"kiss\",\"video\",\"clip\",\"youtube\"]";
//		int index = str.indexOf("sexy");
//		System.out.println(index);
		System.out.println(convertYouTubeDuration(getDuration("tpHu67Zq5Kk")));
		
	}

}
