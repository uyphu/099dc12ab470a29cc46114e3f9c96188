package com.ltu.yealtube.utils;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivateKey;

import com.google.api.client.util.SecurityUtils;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.tools.remoteapi.RemoteApiInstaller;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;

public class RemoteApiExample {

	public static void main(String[] args) throws IOException {
        
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("yealtubetest-8eac9a49b0a0.p12");
			PrivateKey privateKey = SecurityUtils.loadPrivateKeyFromKeyStore(
				      SecurityUtils.getPkcs12KeyStore(), inputStream, "notasecret",
				      "privatekey", "notasecret");
			RemoteApiOptions options = new RemoteApiOptions()
            .server("yealtubetest.appspot.com", 443)
            //.credentials("yealtubetest@appspot.gserviceaccount.com", "3b2f222f667007a7c330d5242d7bfb7a5a2be9ce");
            //.useServiceAccountCredential("f48dc52aef82b620ac1b5c87d437e348f272c766", "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYRjkgexVp3uyD\nwRYqNguBTeSp1W8/ly9kHtlyGpeoEALaMrrXmSWU8GclSlV23HpoIpWchGncCxC8\nc5W5n0V+lFUYkMYsL4uK5KxUtvhq2tD2YcyUWDxUcnLsWup/g1lr26ayfNB1ltfr\nqtya6FA7tC7wbTCfEYtFgoDVsyPOD13gTBH74ycRFZDVD7t7ythyxZQytkGRc44c\nvrF3CcJsQxCMMiCEfZ2joRPSi4qax52heySZb2bDLAyeoBz08ibDwOhrcV0+UaBl\nJ1DZfLVbtu4gJF40w/UV3YqccSFEv5H2SKkp4vpXoorXiuFTH+6tOfqKT5TL3cmR\nTpyUM2y5AgMBAAECggEAUxqoHNDvy9rs668aYKkkOEqBjCq+z068f4DSnZJjyEYM\nuHbNP9MGDrXxJQnfgMItT93oFwaxG3Uyihbu1OJWtBFSK9j/PqgoGQJaBtXg17ha\nKM0RMLjDHDdl/xZTOoo96D51SJ4TyskrSCP0x9oXx627nF2LFpUllCb4FyjwVol2\n1wtLpg/8ltgTX0q/aFmePfOh//4XDZF9HM/mtm0KjsyzK/lteSmZchl7kl50oQF1\nP5PNYKelXzJi2ptyGE681X0skbv11SslnsqOgoY3HoJMRBakTNX0f+K7TE555Dqe\n3dC0wj0E9V7cDGOM/MsqYK8uzH8Vl5gKkthFW/OO/QKBgQDKjO79Tl5TF1GdPsbm\nk3akInLmifF16TpRZiIghovEGF1Y8we6Aqe9z3LBl+aaQQOcsEx564j/50Xe+XeX\nThB7ugMUkGH2+Iz94bLVu+tMdS5UfxSnRiU5KZkGFnfs6qa3sj8rDU4NovDHj8jp\nIYP3Wf9oB0hKNaDs1cQ38R9tmwKBgQDAdPArE/kWuE5RSEvVMXEjO2cVpSkmTSQu\nqT+hWV1bDa5Y82F87Vxv2lgQ01OWZIeg/xa9XwoJ2Wcl1EuGKW1b2Lhds4/HQxO5\n2rXE1iVbRIAmF09eukTMai/PVSPPsR7kjhdJvbf698LHuv7Ka2W0UXp2maHrgoHf\nfwS4KaceOwKBgQCofrDYu3av/HgCLRTc/tL1G0smCJ57Dfdbe3DsScC+8d3FfvZw\ngID13zqBXVGSBH9yux4DVjJzXvq60B/yGBiSNF0XoDw+bOks0bIpJthIlDpkRAr0\nU9/nO3l2nP5KcbEaowb1U24Y3fEasbvaY+0ZuR5EdbpnMVGbcwB14PnZ/QKBgQCp\niJlkKTT8OaDr4zbB98rFEXfuTk5gagXcgMUHmhQtuHCD9OlExkbuVTXMpIv2aFo9\njVf3u3e5U2SUo11gMrAd/1vxVtLp4gHHb409drLzofUNGO2W8ZjXRRcWmdZxB4LC\nLRUApXojPwu7K9N0doTiAgBR52YGczF6soZywGZxrwKBgE/53fu8mc7E86KgsLnv\nEumY6pIxuzB0QbAJKVYXmH9JwqmwKmOKxyjonDZdL8bmpINqVsi7nz2cewRl/pXT\nv4ya89IbaAwM1cISfwQ5JCCDRAxzvbe/J8MmMMparb0zvWS2P354lsCW91I0HUt8\na4tiMukoG6rIq8URQFTucWRE");
            //.useServiceAccountCredential("3b2f222f667007a7c330d5242d7bfb7a5a2be9ce", "/yealtubetest-3b2f222f6670.p12");
//			RemoteApiOptions options = new RemoteApiOptions()
//		    .server("localhost", 9393) // server name must equal "localhost"
		    .useServiceAccountCredential("yealtubetest@appspot.gserviceaccount.com", privateKey);
		    
	        RemoteApiInstaller installer = new RemoteApiInstaller();
	        installer.install(options);
	        try {
	            DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	            System.out.println("Key of new entity is " +
	                ds.put(new Entity("Hello")));
	        } finally {
	            installer.uninstall();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
}
