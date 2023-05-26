package com.spring.development.utils;

import java.util.Random;
import java.util.UUID;

public class Utils {

	public static final Random random = new Random();

	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}
}
