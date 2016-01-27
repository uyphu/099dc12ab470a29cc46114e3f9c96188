package com.ltu.yealtube.utils;

import org.json.JSONException;
import org.json.JSONObject;

import com.ltu.yealtube.domain.Tube;

public class ConvertUtil {
	
	public static Tube converToTube(JSONObject json) {
		Tube tube = new Tube();
		try {
			tube.setId(json.getString("items"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tube;
	}

}
