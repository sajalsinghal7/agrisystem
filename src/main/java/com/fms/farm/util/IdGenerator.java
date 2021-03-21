package com.fms.farm.util;

import java.sql.Timestamp;
import java.time.Instant;

public class IdGenerator {

	public static String generateId() {
		Timestamp curr = Timestamp.from(Instant.now());
		String currString = curr.toString();
		String result = "";
		for(int i=0;i<currString.length();i++) {
			char c = currString.charAt(i);
			int ci = (int)c;
			if(ci >= 48 && ci<=57) result += c;
		}
		return result;
	}
}
