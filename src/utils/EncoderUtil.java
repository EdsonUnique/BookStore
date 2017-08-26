package utils;

import java.util.UUID;

public class EncoderUtil {

	public static String generateId() {
		
		return UUID.randomUUID().toString();
	}

}
