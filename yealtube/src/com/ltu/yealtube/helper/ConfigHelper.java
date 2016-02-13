package com.ltu.yealtube.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.ltu.yealtube.constants.Constant;

/**
 * The Class ConfigHelper.
 * @author uyphu
 */
public class ConfigHelper {

	/** The config. */
	private Properties config;

	/** The secret key. */
	private String secretKey;

	/**
	 * Instantiates a new config helper.
	 *
	 * @param propertiesFile the properties file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private ConfigHelper(File propertiesFile) throws IOException {
		loadProperties(propertiesFile);
	}

	/**
	 * Load properties.
	 *
	 * @param propertiesFile the properties file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void loadProperties(File propertiesFile) throws IOException {

		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		config = new Properties();
		config.load(inputStream);

		this.secretKey = config.getProperty("secret.key");

	}

	/**
	 * Creates the config.
	 *
	 * @return the config helper
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public static ConfigHelper createConfig() throws IOException, IllegalArgumentException {

		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.DEBUG);

		ConfigHelper configHelper = null;

		File accessProperties = new File(Constant.HOME_DIRECTORY_PROPERTY + Constant.HOME_DIRECTORY_FILENAME);

		configHelper = new ConfigHelper(accessProperties);

		return configHelper;
	}

	/**
	 * Gets the secret key.
	 *
	 * @return the secret key
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * Sets the secret key.
	 *
	 * @param secretKey the new secret key
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// try {
		// createConfig();
		// } catch (IllegalArgumentException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
	
}
