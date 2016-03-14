package com.ltu.yealtube.dao;

import java.io.InputStream;
import java.security.PrivateKey;

import com.google.api.client.util.SecurityUtils;
import com.google.appengine.api.datastore.AsyncDatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceConfig;
import com.google.appengine.tools.remoteapi.RemoteApiOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.remotely.RemoteCheck;
import com.googlecode.objectify.remotely.Remotely;
import com.ltu.yealtube.domain.Authority;
import com.ltu.yealtube.domain.Category;
import com.ltu.yealtube.domain.Comment;
import com.ltu.yealtube.domain.ParamValue;
import com.ltu.yealtube.domain.Thing;
import com.ltu.yealtube.domain.TopTube;
import com.ltu.yealtube.domain.Tube;
import com.ltu.yealtube.domain.User;
import com.ltu.yealtube.domain.UserGroup;

/**
 * A factory for creating RemoteObjectify objects.
 * @author uyphu
 */
public class RemoteObjectifyFactory extends ObjectifyFactory {

	/** The remotely. */
	private final Remotely remotely;

	/**
	 * Instantiates a new remote objectify factory.
	 */
	public RemoteObjectifyFactory() {
		register(Category.class);
		register(Comment.class);
		register(Tube.class);
		register(User.class);
		register(UserGroup.class);
		register(Category.class);
		register(Authority.class);
		register(ParamValue.class);
		register(TopTube.class);
		register(Thing.class);

		RemoteApiOptions options = new RemoteApiOptions().server("yealtubetest.appspot.com", 443).useServiceAccountCredential(
				"yealtubetest@appspot.gserviceaccount.com", gePrivateKey());
		remotely = new Remotely(options, new RemoteCheck() {
			@Override
			public boolean isRemote(String namespace) {
				return true;
			}
		});
	}

	@Override
	protected AsyncDatastoreService createRawAsyncDatastoreService(DatastoreServiceConfig cfg) {
		return remotely.intercept(super.createRawAsyncDatastoreService(cfg));
	}
	
	/**
	 * Ge private key.
	 *
	 * @return the private key
	 */
	private PrivateKey gePrivateKey() {
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("yealtubetest-8eac9a49b0a0.p12");
			PrivateKey privateKey = SecurityUtils.loadPrivateKeyFromKeyStore(
				      SecurityUtils.getPkcs12KeyStore(), inputStream, "notasecret",
				      "privatekey", "notasecret");
			return privateKey;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

}
