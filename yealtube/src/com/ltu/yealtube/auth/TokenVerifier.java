package com.ltu.yealtube.auth;

//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.Collections;

//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.apache.ApacheHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;

public class TokenVerifier {

//	// Bearer Tokens from Gmail Actions will always specify this issuee.
//	static String GMAIL_ISSUEE = "letruonguyphu@gmail.com";
//
//	// Get this value from the Google APIs Console.
//	// This is the "Email address" field for a given Client ID in the APIs
//	// Console.
//	static String GOOGLE_API_CLIENT_EMAIL_ADDRESS = "letruonguyphu@gmail.com";
//
//	// Get this value from the request's Authorization HTTP header.
//	// For example, for "Authorization: Bearer AbCdEf123456" use "AbCdEf123456"
//	static String BEARER_TOKEN = "ya29.PAJVrhlYhpA4_NluGFVvVeWOJrFFtp54comf3wW93-lsvjC_BtWppFqJISbuMkfgQj12pg";
//
//	public static void main(String[] args) throws GeneralSecurityException, IOException {
//		JsonFactory factory = new JacksonFactory();
//		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier(
//		// Http Transport is needed to fetch Google's latest public key
//				new ApacheHttpTransport(), factory);
//		GoogleIdToken idToken = GoogleIdToken.parse(factory, BEARER_TOKEN);// GoogleIdToken.parse(factory,
//																			// BEARER_TOKEN);
//		if (idToken == null) {
//			System.out.println("Token cannot be parsed");
//			System.exit(-1);
//		}
//
//		System.out.println("Token details:");
//		System.out.println(idToken.getPayload().toPrettyString());
//
//		// Verify valid token, signed by google.com, intended for a third party.
//		if (!verifier.verify(idToken)
//				|| !idToken.verifyAudience(Collections.singletonList(GOOGLE_API_CLIENT_EMAIL_ADDRESS))
//				|| !idToken.getPayload().getAuthorizedParty().equals(GMAIL_ISSUEE)) {
//			System.out.println("Invalid token");
//			System.exit(-1);
//		}
//
//		// Token originates from Google and is targeted to a specific client.
//		System.out.println("The token is valid");
//	}
}
