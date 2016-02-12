package com.ltu.yealtube;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GeolocationSearchUtil search = GeolocationSearchUtil.getInstance();
		
		//VideoListResponse list = search.getVideos("b3SpBNEbxG0");
		
		//testAPI();
		
		String test = "category:2@@status:2";
		String[] fields = test.split("@@");
		System.out.println(fields.toString());
		
//		System.out.println(list.getItems().toString());
	}
	
	public static void testAPI() {
//		try {

//			URL url = new URL("https://www.googleapis.com/youtube/v3/videos?part=snippet&id=6MRWj4vdOSA&key=AIzaSyBrrKgaw1iD9ETQIF5CRyXWKrriQZT-yQU");
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : "
//						+ conn.getResponseCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//				(conn.getInputStream())));
//			
//
//			String output;
//			System.out.println("Output from Server .... \n");
//			StringBuilder builder = new StringBuilder();
//			while ((output = br.readLine()) != null) {
//				builder.append(output);
//			}
//			//InputStream stream = conn.getInputStream();
//			
//			ObjectMapper mapper = new ObjectMapper();
//			//VideoListResponse response =  mapper.readValue(stream, VideoListResponse.class);
//			//response.getPageInfo();
//			try {
//				JSONObject object = new JSONObject(builder.toString());
//				JSONArray jsonArray = (JSONArray)object.get("items");
//				
//				if (jsonArray != null) {
//					for (int i = 0; i < jsonArray.length(); i++) {
//						//Video video = mapper.readValue(jsonArray.get(0).toString().getBytes(), Video.class);
//						Video video = mapper.readValue(new ByteArrayInputStream(jsonArray.get(0).toString().getBytes()), Video.class);
//						System.out.println(video.getId());
//					}
//				}
//				System.out.println(jsonArray.get(0).toString());
//			} catch (JSONException e) {
//				
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			conn.disconnect();
//
//		  } catch (MalformedURLException e) {
//
//			e.printStackTrace();
//
//		  } catch (IOException e) {
//
//			e.printStackTrace();
//
//		  }
		
		
	}

}
