
package com.aklc.util;

import java.util.UUID;

public class OTP {

	public static final String get() {
		return UUID.randomUUID().toString();
	}

}
