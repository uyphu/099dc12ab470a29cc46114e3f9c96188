package com.ltu.yealtube.auth;

import com.ltu.yealtube.domain.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.geronimo.mail.util.Hex;


/**
 * The Class TokenProvider.
 */
public class TokenProvider {

    /** The secret key. */
    private final String secretKey;
    
    /** The token validity. */
    private final int tokenValidity;

    /**
     * Instantiates a new token provider.
     *
     * @param secretKey the secret key
     * @param tokenValidity the token validity
     */
    public TokenProvider(String secretKey, int tokenValidity) {
        this.secretKey = secretKey;
        this.tokenValidity = tokenValidity;
    }

    /**
     * Creates the token.
     *
     * @param user the user
     * @return the token
     */
    public Token createToken(User user) {
        long expires = System.currentTimeMillis() + 1000L * tokenValidity;
        String token = user.getLogin() + ":" + expires + ":" + computeSignature(user, expires);
        return new Token(token, expires, user.getType());
    }

    /**
     * Compute signature.
     *
     * @param user the user
     * @param expires the expires
     * @return the string
     */
    public String computeSignature(User user, long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(user.getLogin()).append(":");
        signatureBuilder.append(expires).append(":");
        signatureBuilder.append(user.getPassword()).append(":");
        signatureBuilder.append(secretKey);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    /**
     * Gets the user name from token.
     *
     * @param authToken the auth token
     * @return the user name from token
     */
    public String getUserNameFromToken(String authToken) {
        if (null == authToken) {
            return null;
        }
        String[] parts = authToken.split(":");
        return parts[0];
    }

    /**
     * Validate token.
     *
     * @param authToken the auth token
     * @param user the user
     * @return true, if successful
     */
    public boolean validateToken(String authToken, User user) {
        String[] parts = authToken.split(":");
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];
        String signatureToMatch = computeSignature(user, expires);
        return expires >= System.currentTimeMillis() && signature.equals(signatureToMatch);
    }
    
//    public static void main(String[] args) {    	
//    	System.out.println("test...");
//    	TokenProvider tokenProvider = new TokenProvider("mySecretKy", 1);
//    	User user = new User("uyphu", "12346", "uyphu@yahoo.com");
//    	Token token = tokenProvider.createToken(user);
//    	System.out.println(token);
//    	
//    	boolean valid = tokenProvider.validateToken(token.getToken(), user);
//    	System.out.println(valid);
//    }
}
