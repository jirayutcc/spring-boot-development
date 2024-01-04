package com.spring.development.crytography;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Configuration
public class CipherConfiguration {
	public static final String RSA = "RSA";

	@Value("${certs.public}")
	private Resource publicKeyPath;

	@Value("${certs.private}")
	private Resource privateKeyPath;

	@Bean
	public PublicKey toPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		var toByteArray = StreamUtils.copyToByteArray(publicKeyPath.getInputStream());
		var keySpec = new X509EncodedKeySpec(toByteArray);
		return KeyFactory.getInstance(RSA).generatePublic(keySpec);
	}

	@Bean
	public PrivateKey toPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		var toByteArray = StreamUtils.copyToByteArray(privateKeyPath.getInputStream());
		var keySpec = new PKCS8EncodedKeySpec(toByteArray);
		return KeyFactory.getInstance(RSA).generatePrivate(keySpec);
	}
}
