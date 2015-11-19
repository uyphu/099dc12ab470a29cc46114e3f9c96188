package com.ltu.yealtube.dao;

import org.datanucleus.util.Base64;

import com.ltu.yealtube.utils.RandomUtil;


public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// encode data on your side using BASE64
		String str =  "uyphu";
		char[]   bytesEncoded = Base64.encode(str.getBytes(), 5);
		System.out.println("ecncoded value is " + new String(bytesEncoded ));

		// Decode data on other side, by processing encoded data
		byte[] valueDecoded= Base64.decode(bytesEncoded);
		System.out.println("Decoded value is " + new String(valueDecoded));
		
		for (int i = 0; i < 1000; i++) {
			System.out.println(RandomUtil.generateActivationKey());
		}
	}

}
