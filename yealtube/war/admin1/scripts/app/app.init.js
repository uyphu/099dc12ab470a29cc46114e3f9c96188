"use strict";
/**
 * Load google api
 */
function OnLoadCallback() {
	if (AppConstant.API_LOAD_TYPE == 0) {
		AppConstant.API_LOAD_TYPE = 1;
	} else {
		window.init();
	}
}