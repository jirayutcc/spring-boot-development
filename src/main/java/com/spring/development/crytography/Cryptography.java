package com.spring.development.crytography;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class Cryptography {
	public static final String RSA_ECB_PKCS_1_PADDING = "RSA_ECB_PKCS_1_PADDING";

	private final PrivateKey toPrivateKey;
	private final PublicKey toPublicKey;

	@SneakyThrows
	public String encryptRSA(String plaintext) {
		if (StringUtils.isAllBlank(plaintext)) {
			log.warn("plaintext is empty");
			return plaintext;
		}
		Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS_1_PADDING);
		cipher.init(Cipher.ENCRYPT_MODE, toPrivateKey);
		return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8)));
	}

	@SneakyThrows
	public String decryptRSA(String ciphertext) {
		if (StringUtils.isAllBlank(ciphertext)) {
			log.warn("ciphertext is empty");
			return ciphertext;
		}
		Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS_1_PADDING);
		cipher.init(Cipher.DECRYPT_MODE, toPrivateKey);
		return new String(cipher.doFinal(Base64.getDecoder().decode(ciphertext)));
	}
}
